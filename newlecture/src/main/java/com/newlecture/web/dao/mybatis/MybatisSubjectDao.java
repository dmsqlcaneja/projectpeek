package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.LevelDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Subject;

@Repository
public class MybatisSubjectDao implements SubjectDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(Subject subject) {

		SubjectDao subjectDao = sqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.insert(subject);
		
	}

	@Override
	public int delete(long id) {

		SubjectDao subjectDao = sqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.delete(id);
		
	}

	@Override
	public int update(Subject subject) {

		SubjectDao subjectDao = sqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.update(subject);
		
	}

	@Override
	public Subject get(long id) {

		SubjectDao subjectDao = sqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.get(id);
		
	}

	@Override
	public List<Subject> getList() {

		SubjectDao subjectDao = sqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.getList();
		
	}

}
