package concord;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import concord.ConcordServer;

public class ConcordServer 
extends UnicastRemoteObject
implements ConcordServerInterface{
	
	private Concord c;
	private User u1;
	
	private static final long serialVersionUID = -2818842844782357528L;

	protected ConcordServer() throws RemoteException{
		super();
		if(c != null) {
			c = Concord.readXML();
		}
		else {
			c = new Concord();
		}
	}
	
	public static void main(String[] args) {
		try {
			ConcordServer C = new ConcordServer();
			Naming.rebind("CONCORDS", C);
		}
		catch (RemoteException | MalformedURLException e){
			e.printStackTrace();
		}
	}
	
	public Concord getConcord() {
		return c;
	}
	
	public boolean verify(String username, String pw) throws RemoteException {
		ArrayList <User> u = c.getUm().getUsers();
		for (User user : u) {
			if (user.getUserName() == username) {
				if (user.getPassword() == pw) {
					u1 = user;
					return true;
				}
			}
		}
		return false;
	}
	
	
	public User findUserById(int id) throws RemoteException {
		return c.getUm().getUser(id);
	}
	
	public void notify(User u) throws RemoteException {
		
	}

	@Override
	public void invite(int id, User in, Server s) throws RemoteException {
		// notify user of the server invite
		u1 = c.getUm().getUser(id);
		Message m = new Message();
		m.setUser(u1);
		m.setMessage("Invite sent for:" + s);	
	}

	@Override
	public void accept(User member, Server s) throws RemoteException {
		// TODO Auto-generated method stub
		s.addMember(member);
	}

	@Override
	public void kick(int id, User user, Server s) throws RemoteException {
		// TODO Auto-generated method stub
		Role r = s.getRole(u1);
		s.removeMember(r, user);
	}

	@Override
	public void changeServerName(int id, Server s, String serverName) throws RemoteException {
		s.setName(serverName);
	}

	@Override
	public void changeChannelName(int id, Channel chan, Server s, String channelName) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Channel channel = s.getChannel(chan.getName());
		channel.setName(channelName);
	}

	@Override
	public void addChannel(int id, Server s, Channel channel) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.addChannel(r, channel);
	}

	@Override
	public void deleteChannel(int id, Server s, Channel channel) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.deleteChannel(r, channel);
	}

	@Override
	public void changeRole(int id, User user, String newRole, Server s) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.changeUser(r, user, newRole);	
	}

	@Override
	public void addBlock(int id, User user) throws RemoteException {
		u1 = c.getUm().getUser(id);
		u1.addBlock(user);
	}

	@Override
	public void removeBlock(int id, User user) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.removeBlock(user);
		
	}

	@Override
	public void setProfileData(int id, String profD) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setProfileData(profD);
		
	}

	@Override
	public void setUsername(int id, String newUsername) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setUserName(newUsername);
	}

	@Override
	public void setProfilePic(int id, String newPicURL) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setUrlPic(newPicURL);
	}

	@Override
	public void sendPrivateMessage(Message m, DirectConversation dc) throws RemoteException {
		// TODO Auto-generated method stub
		dc.sendMessage(m);
		//dc.notify();
		
	}

	@Override
	public void sendChannelMessage(Message m, Server s, Channel c) throws RemoteException {
		// TODO Auto-generated method stub
		// getChannel and then sendMessage
		c.sendMessage(m);
	}

	@Override
	public void addPin(Server s, Message message) throws RemoteException {
		s.addPin(message);
	}

	@Override
	public void unPin(Server s, Message message) throws RemoteException {
		s.removePin(message);
	}

	/**
	 * @return the c
	 */
	public Concord getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Concord c) {
		this.c = c;
	}

	/**
	 * @return the u1
	 */
	public User getU1() {
		return u1;
	}

	/**
	 * @param u1 the u1 to set
	 */
	public void setU1(User u1) {
		this.u1 = u1;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
