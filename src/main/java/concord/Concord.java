package concord;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Concord {
	private DirectConversationManager dcm;
	private ServerManager sm;
	private UserManager um;
	private static final String SERIALIZED_FILE_NAME="concord.xml";

	public Concord() {
		this.dcm = new DirectConversationManager();
		this.sm = new ServerManager();
		this.um = new UserManager();
	}
	
	public void serializeToXML() {
		XMLEncoder encoder=null;
		
		try {
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		} catch(FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File concord.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	public static Concord readXML() {
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File concord.xml not found");
		}
		Concord c = (Concord)decoder.readObject();
		return c;
	}
	
	public boolean equals(Concord that) {
		ArrayList<DirectConversation> thisDcs = this.getDcm().getDcs();
		ArrayList<DirectConversation> thatDcs = that.getDcm().getDcs();
		
		ArrayList<Server> thisServers = this.getSm().getServers();
		ArrayList<Server> thatServers = that.getSm().getServers();
		
		ArrayList<User> thisUsers = this.getUm().getUsers();
		ArrayList<User> thatUsers = that.getUm().getUsers();
		if(thisDcs.size() != thatDcs.size()) {
			return false;
		}
		
		if(thisServers.size() != thatServers.size()) {
			return false;
		}
		
		if(thisUsers.size() != thatUsers.size()) {
			return false;
		}
		
		for(DirectConversation dc:thisDcs) {
			if(!that.getDcm().contains(dc)) {
				return false;
			}
		}
		
		for(Server s:thisServers) {
			if(!that.getSm().contains(s)) {
				return false;
			}
		}
		
		for(User u:thisUsers) {
			if(!that.getUm().contains(u)) {
				return false;
			}
		}
		
		return true;
	}
	
	public DirectConversationManager getDcm() {
		return dcm;
	}
	
	public ServerManager getSm() {
		return sm;
	}
	
	public UserManager getUm() {
		return um;
	}

	/**
	 * @param d the d to set
	 */
	public void setDcm(DirectConversationManager d) {
		this.dcm = d;
	}

	/**
	 * @param s the s to set
	 */
	public void setSm(ServerManager s) {
		this.sm = s;
	}

	/**
	 * @param u the u to set
	 */
	public void setUm(UserManager u) {
		this.um = u;
	}

}
