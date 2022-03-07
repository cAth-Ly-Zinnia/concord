package concord;

import java.util.ArrayList;

public class DirectConversationManager {
	
	ArrayList<DirectConversation> dcm;

	public DirectConversationManager() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<DirectConversation> getPastConversations(User a){
		int pos;
		DirectConversation dc;
		pos = dcm.indexOf(a);
		dc = dcm.get(pos);
		
		return null;
	}

}
