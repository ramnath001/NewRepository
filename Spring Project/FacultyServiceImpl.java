package com.hiet.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crescendo.utils.DateUtils;
import com.hiet.admin.controller.form.FacultyForm;
import com.hiet.admin.controller.form.SubjectForm;
import com.hiet.admin.dao.FacultyDao;
import com.hiet.admin.dao.LoginInfoDao;
import com.hiet.admin.dao.jdbc.model.FacultyEntity;
import com.hiet.admin.dao.jdbc.model.LoginInfoEntity;
import com.hiet.admin.dao.jdbc.model.SubjectEntity;
import com.hiet.admin.service.FacultyService;

/**
 * 
 * @author nky
 *
 */
@Service("FacultyServiceImpl")
@Transactional(propagation=Propagation.REQUIRED)
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	@Qualifier("FacultyDaoImpl")
	private FacultyDao facultyDao;
	
	@Autowired
	@Qualifier("LoginInfoDaoImpl")
	LoginInfoDao loginInfoDao;
	


	@Override
	public String saveOrUpdateFaculty(FacultyForm facultyForm) {
	  	 FacultyEntity entity=new FacultyEntity();
	  	 
	     BeanUtils.copyProperties(facultyForm, entity);
	     
	     System.out.println(entity);
	     String success= facultyDao.persistFaculty(entity);
	     LoginInfoEntity loginEntity=new LoginInfoEntity();
		 loginEntity.setUserid(entity.getEmpid());
		 loginEntity.setPassword("test");
		 loginEntity.setRoleid(1);
		 loginEntity.setStatus(1);
		 loginEntity.setDesc("new");
		 loginEntity.setCreation_date(DateUtils.getCurrentJavaDate());
	     loginInfoDao.persistLoginInfo(loginEntity);
		return success;
	}

	@Override
	public String deleteFaculty(FacultyForm facultyForm) {
		FacultyEntity entity=new FacultyEntity();
	    BeanUtils.copyProperties(facultyForm, entity)	;
		String success=facultyDao.deleteFaculty(entity);
		return success;
	}

	@Override
	public List<FacultyForm> findAllFacultys() {
		 List<FacultyEntity> facultyEntities=facultyDao.findAllFacultys();
		 List<FacultyForm> facultyForms=new ArrayList<FacultyForm>();
		 for(FacultyEntity entity:facultyEntities){
			 FacultyForm facultyForm=new FacultyForm();
			 BeanUtils.copyProperties(entity, facultyForm);
			 facultyForms.add(facultyForm);
		 }
		return facultyForms;
	}



	@Override
	public FacultyForm findFacultyByCode(String id) {
		FacultyEntity entity=facultyDao.findFacultyByCode(id);
		FacultyForm facultyForm=new FacultyForm();
		BeanUtils.copyProperties(entity, facultyForm);
		return facultyForm;
	}

	
}

	

	
