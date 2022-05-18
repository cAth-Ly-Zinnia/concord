package concord;

import java.io.Serializable;
import java.util.ArrayList;

public class DirectConversationManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8275515918893546610L;
	private ArrayList<DirectConversation> dcs;

	public DirectConversationManager() {
		this.dcs = new ArrayList<DirectConversation>();
	}
	public ArrayList<DirectConversation> getDcListByUserId(int id){
		ArrayList<DirectConversation> list = new ArrayList<DirectConversation>();
		for (DirectConversation d : dcs) {
			for (User a : d.getUsers()){
				if(a.getId() == id) {
					list.add(d);
				}
			}
		}
		return list;
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
	public DirectConversation findDc(String dc) {
		for (DirectConversation d : dcs) {
			if (dc.equals(d.getName())){
				return d;
			}
		}
		return null;
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
