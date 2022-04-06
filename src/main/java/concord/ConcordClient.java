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
	
	public void invite(int id, User user, Server s) {
		try {
			csi.invite(id, user, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void accept(User member, Server s) {
		try {
			csi.accept(member, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void kick(int id, User user, Server s) {
		try {
			csi.kick(id, user, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeServerName(int id, Server s, String serverName) {
		try {
			csi.changeServerName(id, s, serverName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeChannelName(int id, Channel c, Server s, String channelName) {
		try {
			csi.changeChannelName(id, c, s, channelName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addChannel(int id, Server s, Channel channel) {
		try {
			csi.addChannel(id, s, channel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteChannel(int id, Server s, Channel channel) {
		try {
			csi.deleteChannel(id, s, channel);
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
	
	public void changeRole(int id, User user, String newRole, Server s) {
		try {
			csi.changeRole(id, user, newRole, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addBlock(int id, User user) {
		try {
			csi.addBlock(id, user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void removeBlock(int id, User user) {
		try {
			csi.removeBlock(id, user);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfileData(int id, String s) {
		try {
			csi.setProfileData(id, s);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsername(int id, String newUsername) {
		try {
			csi.setUsername(id, newUsername);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfilePic(int id, String newUrlPic) {
		try {
			csi.setProfileData(id, newUrlPic);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPrivateMessage(Message m, DirectConversation dc) {
		try {
			csi.sendPrivateMessage(m, dc);
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
