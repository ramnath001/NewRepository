package com.hiet.admin.dao;

import java.util.List;

import com.hiet.admin.dao.jdbc.model.FacultyEntity;

/**
 * 
 * @author nky
 *
 */
public interface FacultyDao {
	public String persistFaculty(FacultyEntity entity);
	public String updateFaculty(FacultyEntity entity);
	public FacultyEntity findFacultyByCode(String scode);
	public String deleteFaculty(FacultyEntity entity);
	public List<FacultyEntity> findAllFacultys();
	
}
