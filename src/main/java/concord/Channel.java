package concord;

import java.io.Serializable;
import java.util.ArrayList;

public class Channel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3171061004617184614L;
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
