package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

@Repository
public class MybatisQuestionDao implements QuestionDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Question question) {

		QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
		
		return questionDao.insert(question);
		
	}

	@Override
	public int delete(long id) {

		QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
		
		return questionDao.delete(id);
		
	}

	@Override
	public int update(Question question) {

		QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
		
		return questionDao.update(question);
		
	}

	@Override
	public Subject get(long id) {
		
		QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
		
		return questionDao.get(id);

	}

	@Override
	public List<Question> getList() {

		QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
		
		return questionDao.getList();
		
	}

}
