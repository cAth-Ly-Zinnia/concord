package concord;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ConcordClient {
	private ConcordServer cs;
	private User u;
	private ConcordServerInterface csi;
	
	public ConcordClient() {
		try {
			csi = (ConcordServerInterface) Naming.lookup("rmi://127.0.0.1/CONCORDS");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verify(String userName, String pw) {
		try {
			System.out.println("Submitting info");
			return csi.verify(userName, pw);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User findUserById(int id) {
		try {
			return csi.findUserById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void invite(User user, Server s, DirectConversation dc) {
		try {
			csi.invite(user, s, dc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void accept() {
		try {
			csi.accept();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void kick(User user, Server s) {
		try {
			csi.kick(user, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeServerName(Server s, String serverName) {
		try {
			csi.changeServerName(s, serverName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeChannelName(Channel c, Server s, String channelName) {
		try {
			csi.changeChannelName(c, s, channelName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addChannel(Server s, Channel channel) {
		try {
			csi.addChannel(s, channel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteChannel(Server s, Channel channel) {
		try {
			csi.deleteChannel(s, channel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addPin(Server s, Message message) {
		try {
			csi.addPin(s, message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void unPin(Server s, Message message) {
		try {
			csi.unPin(s, message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeRole(User user, String newRole, Server s) {
		try {
			csi.changeRole(user, newRole, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addBlock(User user) {
		try {
			csi.addBlock(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void removeBlock(User user) {
		try {
			csi.removeBlock(user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfileData(String s) {
		try {
			csi.setProfileData(s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsername(String newUsername) {
		try {
			csi.setUsername(newUsername);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfilePic(String newUrlPic) {
		try {
			csi.setProfileData(newUrlPic);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPrivateMessage(Message m, User user, DirectConversation dc) {
		try {
			csi.sendPrivateMessage(m, user, dc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendChannelMessage(Message m, User user, Server s, Channel c) {
		try {
			csi.sendChannelMessage(m, s, c);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the cs
	 */
	public ConcordServer getCs() {
		return cs;
	}

	/**
	 * @param cs the cs to set
	 */
	public void setCs(ConcordServer cs) {
		this.cs = cs;
	}

	/**
	 * @return the u
	 */
	public User getU() {
		return u;
	}

	/**
	 * @param u the u to set
	 */
	public void setU(User u) {
		this.u = u;
	}

	/**
	 * @return the csi
	 */
	public ConcordServerInterface getCsi() {
		return csi;
	}

	/**
	 * @param csi the csi to set
	 */
	public void setCsi(ConcordServerInterface csi) {
		this.csi = csi;
	}
}
