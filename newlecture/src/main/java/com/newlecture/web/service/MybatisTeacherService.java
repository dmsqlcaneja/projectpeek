package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.LevelDao;
import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Level;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

//@Component-> 특화된 어노테이션 @Repository, @Controller, @Service
@Service
public class MybatisTeacherService implements TeacherService {
	//서비스를 만들기 위해서는 DAO가 필요하다
	//쿼리문은 여기서 작성하지 않는다.
	//언제, 어디서부터 객체화 할까요??
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private LevelDao levelDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public List<Subject> getSubjectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> getLevelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, String sortField, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
