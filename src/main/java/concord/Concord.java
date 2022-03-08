package concord;

public class Concord {
	DirectConversationManager d;
	ServerManager s;
	UserManager u;

	public Concord() {
		this.d = new DirectConversationManager();
		this.s = new ServerManager();
		this.u = new UserManager();
	}
	
	public DirectConversationManager getDCM() {
		return d;
	}
	
	public ServerManager getSM() {
		return s;
	}
	
	public UserManager getUM() {
		return u;
	}

}
