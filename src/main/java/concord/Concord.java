package concord;

import java.util.ArrayList;

public class Concord {
	ArrayList<DirectConversation> d;
	ArrayList<ServerManager> s;
	ArrayList<UserManager> u;

	public Concord() {
		this.d = new DirectConversationManager();
		this.s = new ServerManager();
		this.u = new UserManager();
	}

}
