package concord;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Message {
	User u;
	String content;
	Timestamp timeStamp;
	public Message() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void notify(ArrayList<User> user) {
		//subscribe look into observer
	}
	
	public void setMessage(String c) {
		this.content = c;
	}
	
	public String getMessage() {
		return content;
	}

}
