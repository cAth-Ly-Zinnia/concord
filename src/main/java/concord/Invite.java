package concord;

import java.io.Serializable;

public class Invite extends Message implements Serializable {
	Server server;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6783688677670012653L;

	
	public Invite() {
		
	}
	
	public Invite(Server s1) {
		server = s1;
		String s = "An invite has been sent to join a server. Click here to join!";
		this.setContent(s);
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	
	
	

}
