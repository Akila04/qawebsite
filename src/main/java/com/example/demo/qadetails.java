package com.example.demo;

import java.util.List;

import org.springframework.data.annotation.Id;

public class qadetails {
	
	@Id
	private String question;
	private String question_asked_by;
	private List<answerdetails> ans;
	
	
	public qadetails() {
			
			
	}

	
	public qadetails(String question, List<answerdetails> answers, String question_asked_by) {
		super();
		this.question = question;
		this.ans = answers;
		this.question_asked_by = question_asked_by;
	}

	public String getQuestion_asked_by() {
		return question_asked_by;
	}

	public void setQuestion_asked_by(String question_asked_by) {
		this.question_asked_by = question_asked_by;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<answerdetails> getAnswers() {
		return ans;
	}
	public void setAnswers(List<answerdetails> answers) {
		this.ans = answers;
	}
	
	
	
}
