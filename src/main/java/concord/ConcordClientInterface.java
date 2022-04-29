package concord;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ConcordClientInterface extends Remote{
	//need to create exceptions
	public void createUser(String uName, String rName, String pw) throws RemoteException;
	public void verify(String userName, String pw) throws RemoteException;
	public User findUserById(int id) throws RemoteException;
	public void invite(User user, Server s) throws RemoteException;
	public void accept(User member, Server s) throws RemoteException;
	public void kick(User user, Server s) throws RemoteException;
	public void changeServerName(Server s, String serverName) throws RemoteException;
	public void changeChannelName(Channel c, Server s, String channelName) throws RemoteException;
	public void addChannel(Server s, String name) throws RemoteException;
	public void deleteChannel(Server s, Channel channel) throws RemoteException;
	public void addPin(Server s, Message message) throws RemoteException;
	public void unPin(Server s, Message message) throws RemoteException;
	public void changeRole(User user, String newRole, Server s) throws RemoteException;
	public void addBlock(User user) throws RemoteException;
	public void removeBlock(User user) throws RemoteException;
	public void setProfileData(String s) throws RemoteException;
	public void setUsername(String newUsername) throws RemoteException;
	public void setProfilePic(String newUrlPic) throws RemoteException;
	public void sendDCMessage(Message m, DirectConversation dc) throws RemoteException;
	public void sendChannelMessage(Message m, Server s, Channel c) throws RemoteException;
	public ArrayList<DirectConversation> getDcById() throws RemoteException;
	public ArrayList<Server> getServerByUserId() throws RemoteException;
	
	public void addServer(String name) throws RemoteException;
	public void deleteServer(Server s) throws RemoteException;
	
	public void notifyClient() throws RemoteException;
}
