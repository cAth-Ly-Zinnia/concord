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
	
	public void addPin(Server S, Message message) throws RemoteException;
	
	public void unPin(Server S, Message message) throws RemoteException;
	
	//invites user to server
	public void invite(int id, User in, Server s) throws RemoteException;
	
	//allows user to accept invite
	public void accept(User member, Server s) throws RemoteException;
	
	//kicks user from server
	public void kick(int id, User user, Server s) throws RemoteException;
	
	public void changeServerName(int id, Server s, String serverName) throws RemoteException;
	
	public void changeChannelName(int id, Channel c, Server s, String channelName) throws RemoteException;
	
	public void addChannel(int id, Server s, Channel channel) throws RemoteException;
	
	public void deleteChannel(int id, Server s, Channel channel) throws RemoteException;
	
	public void changeRole(int id, User user, String newRole, Server s) throws RemoteException;
	
	public void addBlock(int id, User user) throws RemoteException;
	
	public void removeBlock(int id, User user) throws RemoteException;
	
	public void setProfileData(int id, String u) throws RemoteException;
	
	public void setUsername(int id, String u) throws RemoteException;
	
	public void setProfilePic(int id, String u) throws RemoteException;
	
	public void sendPrivateMessage(Message m, DirectConversation dc) throws RemoteException;
	
	public void sendChannelMessage(Message m, Server s, Channel c) throws RemoteException;
}
