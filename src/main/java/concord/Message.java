package concord;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
	private User u;
	private String content;
	private LocalDateTime timeStamp;
	
	public Message() {
		// TODO Auto-generated constructor stub
		this.timeStamp = LocalDateTime.now();
	}
	
	public void notify(ArrayList<User> user) {
		//subscribe look into observer
	}
	
	public void setTS() {
		this.timeStamp = LocalDateTime.now();
	}
	
	public LocalDateTime getTS() {
		return timeStamp;
	}
	
	public void setMessage(String c) {
		this.content = c;
	}
	
	public String getMessage() {
		return content;
	}

	public User getUser() {
		return u;
	}
}
