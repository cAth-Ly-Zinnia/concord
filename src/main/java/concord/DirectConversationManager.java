package concord;

import java.util.ArrayList;

public class DirectConversationManager {
	
	private ArrayList<DirectConversation> dcs;

	public DirectConversationManager() {
		this.dcs = new ArrayList<DirectConversation>();
	}
	
	public ArrayList<DirectConversation> getPastConversations(User a){		
		ArrayList<DirectConversation> gpc = new ArrayList<DirectConversation>();
		for (DirectConversation d : dcs) {
			if (d.getUsers().contains(a)) {
				gpc.add(d);
			}
		}
		return gpc;
	}
	
	public ArrayList<DirectConversation> getDcs(){
		return dcs;
	}
	
	public DirectConversation createDC(ArrayList<User> users) {
		DirectConversation dc = new DirectConversation();
		for (User u : users) {
			dc.addUser(u);
		}
		dcs.add(dc);
		return dc;
	}
	
	/**
	 * @param dcm the dcm to set
	 */
	public void setDcs(ArrayList<DirectConversation> dcs) {
		this.dcs = dcs;
	}
	
	public boolean contains(DirectConversation dc) {
		for (DirectConversation d: dcs) {
			if(d.equals(dc)) {
				return true;
			}
		}
		return false;
	}
}
