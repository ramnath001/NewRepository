package com.hiet.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.crescendo.utils.DateUtils;
import com.hiet.admin.controller.constant.CMSApplicationConstant;
import com.hiet.admin.controller.constant.NavigationConstant;
import com.hiet.admin.controller.form.FacultyForm;
import com.hiet.admin.controller.form.SubjectForm;
import com.hiet.admin.service.FacultyService;
import com.hiet.cms.dao.base.service.CMSMailService;


@Controller
@Scope("request")
public class FacultyController {
	
	@Autowired
	@Qualifier("FacultyServiceImpl")
	private FacultyService facultyService;
	
	@Autowired
	@Qualifier("CMSMailServiceImpl")
	private CMSMailService cmsMailService;
	
	
	@RequestMapping(value="addFaculity",method=RequestMethod.GET)
	public String showAddfaculty(Model model) {
		FacultyForm form = new FacultyForm();
		System.out.println("In controller");
		model.addAttribute("facultyform",form);
		return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.ADD_FACULITY;
	}
	
	@RequestMapping(value="addFaculity",method=RequestMethod.POST)
	public String submitAddFaculty(@ModelAttribute("facultyForm") FacultyForm facultyForm) {
		System.out.println(" facultyForm = "+facultyForm);
		facultyForm.setDoe(DateUtils.getCurrentJavaDate());
		facultyForm.setDom(DateUtils.getCurrentJavaDate());
		facultyForm.setEuserid("admin");
		facultyService.saveOrUpdateFaculty(facultyForm);
		cmsMailService.sendMail(CMSApplicationConstant.FROM_EMAIL,facultyForm.getEmail(),CMSApplicationConstant.EMAIL_SUBJECT,"Userid = "+facultyForm.getEuserid()+" ,password = test");
		return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.ADD_FACULITY;
	}
	
	@RequestMapping(value = "displayFaculty",method = RequestMethod.GET)
	public String displayAllFaculty(Model model)
	{
		List<FacultyForm>  facultyForm = facultyService.findAllFacultys();
		model.addAttribute("facultyForms",facultyForm);
		return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.DISPLAY_FACULTY;
	}
	
	
	@RequestMapping(value="/editFaculty", method=RequestMethod.GET)
	public String EditSubject(@RequestParam("empid") String empid, Model model){
		FacultyForm facultyForm=new FacultyForm();
		facultyForm = facultyService.findFacultyByCode(empid);
		model.addAttribute("edit_key", facultyForm);
		return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.EDIT_FACULTY;
	}
	
	
	@RequestMapping(value = "/updateFaculty", method=RequestMethod.POST)
	public String updateSubject(@ModelAttribute("edit_key") FacultyForm facultyForm, Model model){
	
		facultyForm.setDoe(DateUtils.getCurrentJavaDate());
		facultyForm.setDom(DateUtils.getCurrentJavaDate());
		facultyForm.setEuserid("admin");
		
		facultyService.saveOrUpdateFaculty(facultyForm);
	
	List<FacultyForm> facultyForms = facultyService.findAllFacultys();
	model.addAttribute("facultyForms",facultyForms);
	return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.DISPLAY_FACULTY;
	}
	
	
	@RequestMapping(value="/deleteFaculty", method=RequestMethod.GET)
	public String deleteSubject(@RequestParam("empid") String empid, Model model ){
		FacultyForm facultyForm = facultyService.findFacultyByCode(empid);
		facultyService.deleteFaculty(facultyForm);
		List<FacultyForm> facultyForms = facultyService.findAllFacultys();
		model.addAttribute("facultyForms", facultyForms);
		return NavigationConstant.ADMIN_VIEW_PREFIX+NavigationConstant.DISPLAY_FACULTY;
	}
	
	
	
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        //Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

}
