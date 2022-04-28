package concord;

import java.io.Serializable;
//import java.time.LocalDateTime;
import java.util.ArrayList;

public class DirectConversation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2586078589438628049L;
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Message> messages = new ArrayList<Message>();

	public DirectConversation() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendMessage(Message msg) {
		//how to make it unique to users
		messages.add(msg);
	}
	
	public Message getLastMessage(){

		int last = this.messages.size();
		return this.messages.get(last - 1);
		//return best;
	}
	
	public void addUser(User a) {
		users.add(a);
	}
	
	public void removeUser(User a) {
		users.remove(a);
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * @return the messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	
	
	public boolean contains(User u) {
		for(User one: users) {
			if(one.equals(u)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(Message m) {
		for(Message one: messages) {
			if(one.equals(m)) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean equals(DirectConversation that) {
		for(User one: users) {
			if(!that.contains(one)) {
				return false;
			}
		}
		for(Message m: messages) {
			if(!that.contains(m)) {
				return false;
			}
		}
		return true;
	}

	public String getName() {
		String name = "";
		for (User u:users){
			String user = u.getUserName();
			name = name + " " + user;
		}
		return name;
	}

	/*public void setName(String name) {
		this.name = name;
	}*/
}
