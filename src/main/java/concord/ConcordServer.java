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
		c = new Concord();
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
	public void invite(User user, Server s, DirectConversation dc) throws RemoteException {
		// notify user of the server invite
		Message m = new Message();
		m.setMessage("Invite sent for:" + s);
		
		
	}

	@Override
	public void accept() throws RemoteException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void kick(User user, Server s) throws RemoteException {
		// TODO Auto-generated method stub
		Role r = s.getRole(u1);
		s.removeMember(r, user);
	}

	@Override
	public void changeServerName(Server s, String serverName) throws RemoteException {
		s.setName(serverName);
	}

	@Override
	public void changeChannelName(Channel c, Server s, String channelName) throws RemoteException {
		// TODO Auto-generated method stub
		// need to make getters and setters
		// prob a better way
		Channel channel = s.getChannel(c.getName());
		channel.setName(channelName);
	}

	@Override
	public void addChannel(Server s, Channel channel) throws RemoteException {
		Role r = s.getRole(u1);
		s.addChannel(r, channel);
	}

	@Override
	public void deleteChannel(Server s, Channel channel) throws RemoteException {
		Role r = s.getRole(u1);
		s.deleteChannel(r, channel);
	}

	@Override
	public void changeRole(User user, String newRole, Server s) throws RemoteException {
		Role r = s.getRole(u1);
		s.changeUser(r, user, newRole);	
	}

	@Override
	public void addBlock(User user) throws RemoteException {
		// TODO Auto-generated method stub
		u1.addBlock(user);
		
	}

	@Override
	public void removeBlock(User user) throws RemoteException {
		// TODO Auto-generated method stub
		u1.removeBlock(user);
		
	}

	@Override
	public void setProfileData(String d) throws RemoteException {
		// TODO Auto-generated method stub
		u1.setProfileData(d);
		
	}

	@Override
	public void setUsername(String newUsername) throws RemoteException {
		// TODO Auto-generated method stub
		u1.setUserName(newUsername);
	}

	@Override
	public void setProfilePic(String newPicURL) throws RemoteException {
		// TODO Auto-generated method stub
		u1.setUrlPic(newPicURL);
	}

	@Override
	public void sendPrivateMessage(Message m, User user, DirectConversation dc) throws RemoteException {
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
