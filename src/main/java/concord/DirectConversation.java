package concord;

import java.util.ArrayList;

public class DirectConversation {
	
	ArrayList<User> users;
	ArrayList<Message> messages;

	public DirectConversation() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendMessage(Message msg, User a, User b) {
		//how to make it unique to users
		messages.add(msg);
	}
	
	public void getLastTimestamp(){
		
	}
}
