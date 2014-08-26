package com.hiet.admin.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crescendo.utils.DateUtils;
import com.hiet.admin.controller.form.StudentForm;
import com.hiet.admin.dao.FacultyDao;
import com.hiet.admin.dao.jdbc.model.FacultyEntity;
import com.hiet.admin.dao.jdbc.model.LoginInfoEntity;
import com.hiet.cms.dao.base.AbstractDaoImpl;

@Repository("FacultyDaoImpl")
@Scope("singleton")
public class FacultyDaoImpl extends AbstractDaoImpl<FacultyEntity, Serializable> implements FacultyDao{

	protected FacultyDaoImpl() {
		super(FacultyEntity.class);
	}

	@Override
	public String persistFaculty(FacultyEntity entity) {
				saveOrUpdate(entity);
				return "success";
	}

	@Override
	public String updateFaculty(FacultyEntity entity) {
		getCurrentSession().update(entity);
		return "success";
	}

	@Override
	public FacultyEntity findFacultyByCode(String empid) {
		return super.findById(empid);
	}

	@Override
	public String deleteFaculty(FacultyEntity entity) {
		
		super.delete(entity);
		return "success";
	}

	@Override
	public List<FacultyEntity> findAllFacultys() {
		return super.findAll();
	}


	

}
