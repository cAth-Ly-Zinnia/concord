package concord;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
	private User user;
	private String content;
	private LocalDateTime timeStamp;
	
	public Message() {
		// TODO Auto-generated constructor stub
		this.timeStamp = LocalDateTime.now();
	}
	
	public boolean equals(Message that) {
		if(content.equals(that.getContent())) {
			return true;
		}
		return false;
	}
	
	public void notify(ArrayList<User> user) {
		System.out.print("Message is sent...");
	}
	
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setMessage(String c) {
		this.content = c;
	}
	
	public String getMessage() {
		return content;
	}

	public User getUser() {
		return user;
	}

	/**
	 * @param u the u to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	
}
