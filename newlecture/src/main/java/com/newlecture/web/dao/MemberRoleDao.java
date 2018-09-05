package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.MemberRole;

public interface MemberRoleDao {
	int insert(MemberRole memberRole);
	int update(MemberRole memberRole);
	int delete(MemberRole memberRole);
	
	MemberRole get(String id);
	List<MemberRole> getList();
	List<MemberRole> getList(String memberId);
}
