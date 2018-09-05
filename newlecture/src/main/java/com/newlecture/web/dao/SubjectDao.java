package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Subject;

public interface SubjectDao {
	
	int insert(Subject subject);
	int delete(long id);
	int update(Subject subject);
	
	Subject get(long id);
	List<Subject> getList();
	
}
