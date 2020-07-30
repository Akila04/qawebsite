package com.example.demo;

import org.springframework.data.annotation.Id;

public class questiondetails {
	
	@Id
	private String question;
	private String questioned_by;
	
	
	public questiondetails() {
		
	}

	public questiondetails(String question, String questioned_by) {
		super();
		this.question = question;
		this.questioned_by = questioned_by;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestioned_by() {
		return questioned_by;
	}
	
	public void setQuestioned_by(String questioned_by) {
		this.questioned_by = questioned_by;
	}

}
