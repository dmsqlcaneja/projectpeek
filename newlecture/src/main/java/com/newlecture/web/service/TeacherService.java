package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Level;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

public interface TeacherService {

	List<Subject> getSubjectList();
	List<Level> getLevelList();
	List<Question> getQuestionList(); //검색어, 내것, 정렬필드, 페이지 인자 필요
	List<Question> getQuestionList(String query); //자주 사용하는 기본 서비스들을 지정
	List<Question> getQuestionList(String query, int page);
	List<Question> getQuestionList(String query, boolean all, int page);
	List<Question> getQuestionList(String query, boolean all, String sortField, int page);
	
}
