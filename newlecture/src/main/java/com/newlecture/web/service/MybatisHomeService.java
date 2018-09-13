package com.newlecture.web.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;

@Service
public class MybatisHomeService {
	
	@Autowired
	private MemberRoleDao memberRoleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	public String getDefaultRoleName(String memberId) {
		
		List<MemberRole> list = memberRoleDao.getList(memberId);
		
		String roleName = "ROLE_STUDENT";
		for(MemberRole role : list)
			if(role.getDefaultRole())
				roleName = role.getRoleName();
		
		
		return roleName;
		
	}

	public boolean isEmailDuplicated(String email) {
		
		//컨트롤 단에 원하는 결과물을 돌려주는 역할을 한다. 
		//비서역할 = service
		
		//구현방법 
		//getByEmail을 memberDao에 추가 -> mybatismemberdao 에 getByEmail추가 -> memberDao.xml 에 쿼리문 작성 
		//select id 지정 , return memberDao.getByEmail(email) 지정 
		Member member = memberDao.getByEmail(email);
		
		if(member != null)
			return true;
		
		return false;
	}

	public boolean isIdDuplicated(String id) {
		
		Member member = memberDao.getById(id);
		
		if(member != null)
			return true;
		
		return false;
	}

	public int insertMember(Member member) {
		
		
		int result = memberDao.insert(member);
		memberRoleDao.insert(new MemberRole(member.getId(), "ROLE_TEACHER", true));
		
		return result;
		
	}
	
	public Member getMember(String id) {
		return memberDao.get(id);
	}
	
	

}
