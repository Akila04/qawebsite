package com.example.demo;

public class answerdetails {
	
	private String answered_by;
	String answer;
	
	public answerdetails() {
	
	}

	public answerdetails(String answered_by, String answer) {
		super();
		this.answered_by = answered_by;
		this.answer = answer;
	}
	
	public String getAnswered_by() {
		return answered_by;
	}
	
	public void setAnswered_by(String answered_by) {
		this.answered_by = answered_by;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
