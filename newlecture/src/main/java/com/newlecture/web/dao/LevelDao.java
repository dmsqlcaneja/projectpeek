package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Level;
import com.newlecture.web.entity.Subject;

public interface LevelDao {
	
	int insert(Level level);
	int delete(long id);
	int update(Level level);
	
	Subject get(long id);
	List<Level> getList();
}
