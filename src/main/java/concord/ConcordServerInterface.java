package concord;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConcordServerInterface extends Remote{
	// verifies User
	public Concord getConcord() throws RemoteException;
	
 	public boolean verify(String username, String pw) throws RemoteException;
	
	// finds the user in application
	public User findUserById(int id) throws RemoteException;
	
	//notifies the user, focused on messages
	public void notify(User u) throws RemoteException;
	
	public void addPin(Server S, Message message) throws RemoteException;
	
	public void unPin(Server S, Message message) throws RemoteException;
	
	//invites user to server
	public void invite(User admin, User other, Server s) throws RemoteException;
	
	//allows user to accept invite
	public void accept(User member, Server s) throws RemoteException;
	
	//kicks user from server
	public void kick(User user, Server s) throws RemoteException;
	
	public void changeServerName(Server s, String serverName) throws RemoteException;
	
	public void changeChannelName(Channel c, Server s, String channelName) throws RemoteException;
	
	public void addChannel(Server s, Channel channel) throws RemoteException;
	
	public void deleteChannel(Server s, Channel channel) throws RemoteException;
	
	public void changeRole(User user, String newRole, Server s) throws RemoteException;
	
	public void addBlock(User user) throws RemoteException;
	
	public void removeBlock(User user) throws RemoteException;
	
	public void setProfileData(String u) throws RemoteException;
	
	public void setUsername(String u) throws RemoteException;
	
	public void setProfilePic(String u) throws RemoteException;
	
	public void sendPrivateMessage(Message m, User user, DirectConversation dc) throws RemoteException;
	
	public void sendChannelMessage(Message m, Server s, Channel c) throws RemoteException;
}
