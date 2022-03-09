package concord;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DirectConversation {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Message> messages = new ArrayList<Message>();

	public DirectConversation() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendMessage(Message msg) {
		//how to make it unique to users
		messages.add(msg);
	}
	
	public LocalDateTime getLastTimestamp(){
		LocalDateTime best = LocalDateTime.of(2000, 1, 1, 0, 0);
		//not able to grab the last timestamps
		/*for (Message m : messages) {
			m.setTS();
			if (m.getTS().isAfter(best)) {
				best = m.getTS();
			}
		}*/
		//int last = this.messages.size();
		//return this.messages.get(last).getTS();
		return best;
	}
	
	public void addUser(User a) {
		users.add(a);
	}
	
	public void removeUser(User a) {
		users.remove(a);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}
}
