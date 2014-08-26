package com.hiet.admin.service;

import java.io.Serializable;
import java.util.List;

import com.hiet.admin.controller.form.FacultyForm;

public interface FacultyService {
	public FacultyForm findFacultyByCode(String id);
    public String saveOrUpdateFaculty(FacultyForm e);
    public String deleteFaculty(FacultyForm e);
    public List<FacultyForm> findAllFacultys();
	
}
