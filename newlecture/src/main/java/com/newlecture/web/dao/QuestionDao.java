package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

public interface QuestionDao {
	int insert(Question question);
	int delete(long id);
	int update(Question question);
	
	Subject get(long id);
	List<Question> getList();
}
