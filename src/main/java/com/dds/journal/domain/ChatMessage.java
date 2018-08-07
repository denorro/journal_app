package com.dds.journal.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatMessage {
	
	private String body;	
	private String author;
	private List<String> recipients;
	private String created;	
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a z");
	
	public ChatMessage() {
		this.created = sdf.format(new Date());
		
	}

	public ChatMessage(String text, String author, List<String> recipients) {
		this.body = text;
		this.author = author;
		this.recipients = recipients;
		this.created = sdf.format(new Date());
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	@Override
	public String toString() {
		return "ChatMessage [text=" + body + ", author=" + author + ", created=" + created + ", party=" + recipients.size() + "]";
	}
}
