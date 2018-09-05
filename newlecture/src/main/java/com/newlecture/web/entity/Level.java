package com.newlecture.web.entity;

import java.util.Date;

public class Level {
	private long id;
	private String title;
	private String regUserId; //등록자
	private Date regDate;
	private String description;
	
	public Level() {
		// TODO Auto-generated constructor stub
	}
	
	//for inserting
	public Level(String title, String regUserId, String description) {
		this.title = title;
		this.regUserId = regUserId;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + ", regUserId=" + regUserId + ", regDate=" + regDate
				+ ", description=" + description + "]";
	}
	
}
