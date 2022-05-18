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
	
	public void notifyChannels() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyChannels();
		}
	}
	public void notifyChannelMsg() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyChannelMsg();
		}
	}
	public void notifyDcMsg() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyDcMsg();
		}
	}
	public void notifyServers() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyServers();
		}
	}
	private void notifyServer() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyServer();
		}
		
	}
	
	private void notifyPins() throws RemoteException {
		for (ConcordClientInterface c : clients) {
			c.notifyPins();
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
		Invite m = new Invite(s);
		m.setUser(u1);
		ArrayList<User> users = new ArrayList<User>();
		users.add(u1); users.add(in);
		DirectConversation dc = c.getDcm().findDc(users);
		if(dc == null) {
			dc = new DirectConversation();
			dc.addUser(u1);
			dc.addUser(in);
		}
		this.sendPrivateMessage(m, dc);
	}

	@Override
	public void accept(int id, Server s) throws RemoteException {
		// TODO Auto-generated method stub
		u1 = c.getUm().getUser(id);
		Server s1 = c.getSm().getServer(s.getName());
		s1.addMember(u1);
		this.notifyServers();
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
		this.notifyChannels();
	}

	@Override
	public Channel addChannel(int id, Server s, String name) throws RemoteException {
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
		this.notifyChannels();
		return channel;
	}
	

	@Override
	public void deleteChannel(int id, Server s, Channel channel) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Role r = s.getRole(u1);
		Server s1 = c.getSm().getServer(s.getName());
		s1.deleteChannel(r, channel);
		this.notifyChannels();
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
		DirectConversation dc1 = c.getDcm().findDc(dc.getName());
		if (dc1 != null) {
			dc1.sendMessage(m);
			this.notifyDcMsg();
		}
	}

	@Override
	public void sendChannelMessage(Message m, int id, Server s, Channel channel) throws RemoteException {
		// getChannel and then sendMessage
		Server s1 = c.getSm().getServer(s.getName());
		//System.out.println(channel);
		Channel c1 = s1.getChannel(channel.getName());
		u1 = s1.findEquivalentUser(id);
		m.setUser(u1);
		Level l = s1.findLevel(u1);
		c1.sendMessage(m, l);
		System.out.println(l.getLvl());
		this.notifyChannelMsg();
	}

	@Override
	public void addPin(int id, Server s, Message message) throws RemoteException {
		Server s1 = c.getSm().getServer(s.getName());
		u1 = s1.findEquivalentUser(id);
		message.setUser(u1);
		if (s1.findLevel(u1).getLvl() == 3) {
			s1.addPin(message);
			System.out.println("Level 3");
		}
		else {
			System.out.println("Not Level 3");
		}
		this.notifyPins();
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
	public Server addServer(int id, String name) throws RemoteException {
		u1 = c.getUm().getUser(id);
		Server s = null;
		try {
			s = c.getSm().createServer(name, u1);
			this.makeChange();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public void deleteServer(int id, Server s) throws RemoteException {
		u1 = c.getUm().getUser(id);
		c.getSm().deleteServer(s, u1);
	}

	@Override
	public ArrayList<User> getUsers() throws RemoteException {
		return c.getUm().getUsers();
	}

	@Override
	public void addDc(int uid, User userTarget) throws RemoteException {
		u1 = c.getUm().getUser(uid);
		User u2 = c.getUm().getUser(userTarget.getId());
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		c.getDcm().createDC(users);
	}

	@Override
	public Channel getServerChannel(Server server, Channel channel) throws RemoteException{
		return c.getSm().getServer(server.getName()).getChannel(channel.getName());
	}

}
