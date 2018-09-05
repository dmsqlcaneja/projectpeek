package com.newlecture.web.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.MemberRole;

@Service
public class MybatisHomeService {
	
	@Autowired
	private MemberRoleDao memberRoleDao;
	
	public String getDefaultRoleName(String memberId) {
		
		List<MemberRole> list = memberRoleDao.getList(memberId);
		
		String roleName = "ROLE_STUDENT";
		for(MemberRole role : list)
			if(role.getDefaultRole())
				roleName = role.getRoleName();
		
		
		return " ";
		
	}

}
