package concord;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import concord.ConcordServer;

public class ConcordServer 
extends UnicastRemoteObject
implements ConcordServerInterface{
	
	private Concord c;
	private User u1;
	
	private static final long serialVersionUID = -2818842844782357528L;

	public ConcordServer() throws RemoteException {
		super();
		c = Concord.readXML();
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
	
	private ArrayList<ConcordClientInterface> clients = new ArrayList<ConcordClientInterface>();
	
	@Override
	public void addObserver(ConcordClientInterface o) throws RemoteException {
		clients.add(o);
	}

	@Override
	public void removeObserver(ConcordClientInterface o) throws RemoteException {
		clients.remove(o);
	}
	
	public void notifyClients() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyClient();
		}
	}
	
	public void makeChange() throws RemoteException {
		notifyClients();
	}
	
	public Concord getConcord() {
		return c;
	}
	
	public User verify(String username, String pw) throws RemoteException {
		u1 = c.getUm().verify(username, pw);
		return u1;
	}
	

	@Override
	public void createUser(String username, String realname, String pw) throws RemoteException {
		c.getUm().createUser(username, realname, pw);
		this.makeChange();
		
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
		this.makeChange();
	}

	@Override
	public void kick(int id, User user, Server s) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.removeMember(r, user);
		this.makeChange();
	}

	@Override
	public void changeServerName(int id, Server s, String serverName) throws RemoteException {
		s.setName(serverName);
		this.makeChange();
	}

	@Override
	public void changeChannelName(int id, Channel chan, Server s, String channelName) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Channel channel = s.getChannel(chan.getName());
		channel.setName(channelName);
		this.makeChange();
	}

	@Override
	public void addChannel(int id, Server s, String name) throws RemoteException {
		Server server = null;
		u1 = s.findEquivalentUser(id);
		Role r = s.getRole(u1);
		Channel channel = new Channel();
		channel.setName(name);
		for(Server s1 : c.getSm().getServers()) {
			if(s1.getName().equals(s.getName())) {
				server = s1;
			}
		}
		System.out.println(server);
		server.addChannel(r, channel);
		this.makeChange();
	}
	

	@Override
	public void deleteChannel(int id, Server s, Channel channel) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.deleteChannel(r, channel);
		this.makeChange();
	}

	@Override
	public void changeRole(int id, User user, String newRole, Server s) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		s.changeUser(r, user, newRole);	
		this.makeChange();
	}

	@Override
	public void addBlock(int id, User user) throws RemoteException {
		u1 = c.getUm().getUser(id);
		u1.addBlock(user);
		this.makeChange();
	}

	@Override
	public void removeBlock(int id, User user) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.removeBlock(user);
		this.makeChange();
		
	}

	@Override
	public void setProfileData(int id, String profD) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setProfileData(profD);
		this.makeChange();
	}

	@Override
	public void setUsername(int id, String newUsername) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setUserName(newUsername);
		this.makeChange();
	}

	@Override
	public void setProfilePic(int id, String newPicURL) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		u1.setUrlPic(newPicURL);
		this.makeChange();
	}

	@Override
	public void sendPrivateMessage(Message m, DirectConversation dc) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(dc);
		dc.sendMessage(m);
		this.makeChange();	
	}

	@Override
	public void sendChannelMessage(Message m, int id, Server s, Channel channel) throws RemoteException {
		// getChannel and then sendMessage
		u1 = c.getUm().getUser(id);
		m.setUser(u1);
		Server s1 = c.getSm().getServer(s.getName());
		Channel c1 = s1.getChannel(channel.getName());
		c1.sendMessage(m);
		System.out.println("sending");
		this.makeChange();
	}

	@Override
	public void addPin(Server s, Message message) throws RemoteException {
		s.addPin(message);
		this.makeChange();
	}

	@Override
	public void unPin(Server s, Message message) throws RemoteException {
		s.removePin(message);
		this.makeChange();
	}
	
	@Override
	public ArrayList<DirectConversation> getDcById(int uid) throws RemoteException {
		User a = this.findUserById(uid);
		return c.getDcm().getPastConversations(a);
	}
	
	public ArrayList<Server> getServerByUserId (int uid) throws RemoteException{
		User a = this.findUserById(uid);
		return c.getSm().getUserServers(a);
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

	@Override
	public void addServer(int id, String name) throws RemoteException {
		u1 = c.getUm().getUser(id);
		try {
			c.getSm().createServer(name, u1);
			this.makeChange();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteServer(int id, Server s) throws RemoteException {
		u1 = c.getUm().getUser(id);
		c.getSm().deleteServer(s, u1);
	}

}
