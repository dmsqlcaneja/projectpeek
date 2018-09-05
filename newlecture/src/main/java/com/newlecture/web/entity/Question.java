package com.newlecture.web.entity;

import java.util.Date;

public class Question {
	
	private long id;
	private String content;
	private String figure;
	private Date regDate;
	private String regUsrId;
	
	public Question(String content, String figure, String regUsrId) {
		
		this.content = content;
		this.figure = figure;
		this.regUsrId = regUsrId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFigure() {
		return figure;
	}
	
	public void setFigure(String figure) {
		this.figure = figure;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getRegUsrId() {
		return regUsrId;
	}
	
	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", figure=" + figure + ", regDate=" + regDate
				+ ", regUsrId=" + regUsrId + "]";
	}
	
}
