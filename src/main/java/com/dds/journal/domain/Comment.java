package com.dds.journal.domain;

public class Comment {	

	private Long id;	

	private String comment;
	
	private User user;
	
	private Journal journal;
	
	Comment(){}
	
	Comment(User user, String comment){
		this.user = user;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + "]";
	}	

}
