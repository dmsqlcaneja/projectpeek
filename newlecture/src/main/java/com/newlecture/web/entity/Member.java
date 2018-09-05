package com.newlecture.web.entity;

import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String email;
	private String pwd;
	private Date regDate;
	
	
	public Member() {
		
	}
	
	public Member(String id, String name, String email, String pwd, Date regDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.regDate = regDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", regDate=" + regDate
				+ "]";
	}
	
	
	
}
