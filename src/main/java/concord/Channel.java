package concord;

import java.util.ArrayList;

public class Channel {
	String name;
	ArrayList<Message> messages;

	public Channel() {
		// TODO Auto-generated constructor stub
	}
	
	public void changeName(String n) {
		this.name = n;
	}
	
	public String getChannelName() {
		return name;
	}

}
