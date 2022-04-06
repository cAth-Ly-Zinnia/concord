package concord;

import java.util.ArrayList;

public class Channel {
	private String name;
	private ArrayList<Message> messages = new ArrayList<Message>();

	public Channel() {
		// TODO Auto-generated constructor stub
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void sendMessage(Message m) {
		messages.add(m);
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}


	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public boolean equals(Channel that) {
		for(Message m: messages) {
			if(!that.contains(m)) {
				return false;
			}
		}
		
		if(!name.equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	public boolean contains(Message that) {
		for(Message m: messages) {
			if(m.equals(that)) {
				return true;
			}
		}
		return false;
	}

}
