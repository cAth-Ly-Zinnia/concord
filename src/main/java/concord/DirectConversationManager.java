package concord;

import java.util.ArrayList;

public class DirectConversationManager {
	
	private ArrayList<DirectConversation> dcm = new ArrayList<DirectConversation>();

	public DirectConversationManager() {
		
	}
	
	public ArrayList<DirectConversation> getPastConversations(User a){		
		ArrayList<DirectConversation> gpc = new ArrayList<DirectConversation>();
		for (DirectConversation d : dcm) {
			if (d.getUsers().contains(a)) {
				gpc.add(d);
			}
		}
		return gpc;
	}
	
	public ArrayList<DirectConversation> getDCM(){
		return dcm;
	}
	
	public DirectConversation createDC(ArrayList<User> users) {
		DirectConversation dc = new DirectConversation();
		for (User u : users) {
			dc.addUser(u);
		}
		dcm.add(dc);
		return dc;
	}
}
