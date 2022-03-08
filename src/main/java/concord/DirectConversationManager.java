package concord;

import java.util.ArrayList;

public class DirectConversationManager {
	
	ArrayList<DirectConversation> dcm;

	public DirectConversationManager() {
		
	}
	
	public ArrayList<DirectConversation> getPastConversations(User a){		
		ArrayList<DirectConversation> gpc = new ArrayList<DirectConversation>();
		if (dcm.contains(a)){
			for (int i = 0; i < dcm.size(); i++) {
				gpc.add(dcm.get(i));
			}
		}
		return gpc;
	}
	
	public ArrayList<DirectConversation> getDCM(){
		return dcm;
	}
}
