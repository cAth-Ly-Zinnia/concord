package concord;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.application.Platform;
import models.ConcordModel;

public class ConcordClient extends UnicastRemoteObject 
						   implements ConcordClientInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9010417063478819564L;
	private ConcordServer cs;
	private User u;
	private int uid;
	private ConcordServerInterface csi;
	private ConcordModel model;
	
	public ConcordClient() throws RemoteException{
		this(new ConcordModel());
	}
	
	public ConcordClient(ConcordModel cModel) throws RemoteException{
		super();
		try {
			csi = (ConcordServerInterface) Naming.lookup("rmi://127.0.0.1:2099/CONCORDS");
			model = cModel;
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * server updates
	 * then client update models
	 * 
	 */
	public void notifyClient() throws RemoteException{
		System.out.println("There have been changes.");
		Platform.runLater(()->{
			try {
				model.reset(csi.getConcord());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});

	}
	
	@Override
	public void notifyChannels() throws RemoteException {
		System.out.println("There have been changes.");
		Platform.runLater(()->{
			try {
				//grab server and channel
				model.reset(csi.getConcord());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void notifyChannelMsg() throws RemoteException {
		System.out.println("There have been changes.");
		Platform.runLater(()->{
			try {
				//grab server and channel and msgs
				model.reset(csi.getConcord());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void notifyDcMsg() throws RemoteException {
		System.out.println("There have been changes.");
		Platform.runLater(()->{
			try {
				//grab user and their msgs
				model.reset(csi.getConcord());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	public void verify(String userName, String pw) {
		try {
			System.out.println("Submitting info");
			u = csi.verify(userName, pw);
			uid = u.getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUser(String uName, String rName, String pw){
		try {
			csi.addObserver(this);
			csi.createUser(uName, rName, pw);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public User findUserById(int id) {
		try {
			return csi.findUserById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void invite(User user, Server s) {
		try {
			csi.invite(uid, user, s);
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
	
	public void kick(User user, Server s) {
		try {
			csi.kick(uid, user, s);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeServerName(Server ser, String serverName) {
		try {
			csi.changeServerName(uid, ser, serverName);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeChannelName(Channel c, Server s, String channelName) {
		try {
			csi.changeChannelName(uid, c, s, channelName);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addChannel(Server s, String name) {
		try {
			csi.addChannel(uid, s, name);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteChannel(Server s, Channel channel) {
		try {
			csi.deleteChannel(uid, s, channel);
			 
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
			csi.changeRole(uid, user, newRole, s);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addBlock(User user) {
		try {
			csi.addBlock(uid, user);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void removeBlock(User user) {
		try {
			csi.removeBlock(uid, user);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfileData(String s) {
		try {
			csi.setProfileData(uid, s);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsername(String newUsername) {
		try {
			csi.setUsername(uid, newUsername);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setProfilePic(String newUrlPic) {
		try {
			csi.setProfilePic(uid, newUrlPic);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addServer(String name) throws RemoteException {
		try {
			csi.addServer(uid, name);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteServer(Server s) throws RemoteException {
		try {
			csi.deleteServer(uid, s);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDCMessage(Message m, DirectConversation dc) throws RemoteException{
		try {
			csi.sendPrivateMessage(m, dc);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void sendChannelMessage(Message m, Server s, Channel c) throws RemoteException{
		try {
			csi.sendChannelMessage(m, uid, s, c);
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<DirectConversation> getDcById() throws RemoteException {
		return csi.getDcById(uid);
	}

	@Override
	public ArrayList<Server> getServerByUserId() throws RemoteException {
		return csi.getServerByUserId(uid);
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
