package models;

import java.rmi.RemoteException;
import java.util.ArrayList;

import concord.Channel;
import concord.Concord;
import concord.DirectConversation;
import concord.Message;
import concord.Server;
import concord.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class ConcordModel
{
	
	ObservableList<Server> servers = FXCollections.observableArrayList();
	ObservableList<Channel> channels = FXCollections.observableArrayList();
	ObservableList<DirectConversation> dcs = FXCollections.observableArrayList();
	ObservableList<Message> serMessages = FXCollections.observableArrayList();
	ObservableList<Message> dcsMessages = FXCollections.observableArrayList();
	ObservableList<Message> pinMessages = FXCollections.observableArrayList();
	ObservableList<User> users = FXCollections.observableArrayList();
	
	public ConcordModel() {}
	
	public void reset(Concord c)
	{
		servers.clear();
		for (Server s : c.getSm().getServers()) {
			servers.add(s);
		}
		
		dcs.clear();
		for (DirectConversation dc : c.getDcm().getDcs()) {
			dcs.add(dc);
		}
		serMessages.clear();
		dcsMessages.clear();
	}
	
	public void resetChannelMessage(Concord c, Server s, Channel c1) {
		serMessages.clear();
		for(Message m: c.getSm().getServer(s.getName()).getChannel(c1.getName()).getMessages()) {
			serMessages.add(m);
		}
	}
	
	public void resetChannels(Concord c, Server s) {
		channels.clear();
		for(Channel c1: c.getSm().getServer(s.getName()).getChannels()) {
			channels.add(c1);
		}
	}
	
	public void resetDcsMessage(Concord c, DirectConversation dc) {
		dcsMessages.clear();
		for (Message m: dc.getMessages()) {
			dcsMessages.add(m);
		}
	}
	
	/**
	 * @return the users
	 */
	public ObservableList<User> getUsers() {
		return users;
	}

	public ObservableList<Server> getServers()
	{
		return servers;
	}
	
	public ObservableList<Channel> getChannels()
	{
		return channels;
	}
	
	public ObservableList<DirectConversation> getDcs()
	{
		return dcs;
	}
	
	public ObservableList<Message> getSerMessages()
	{
		return serMessages;
	}
	
	public ObservableList<Message> getDcsMessages()
	{
		return dcsMessages;
	}
	public ObservableList<Message> getPinMessages()
	{
		return pinMessages;
	}
	

	public void setServers(ArrayList<Server> arrayList) {
		arrayList.clear();
		for (Server s: arrayList) {
			arrayList.add(s);
		}
	}

	public void setChannels(ArrayList<Channel> arrayList) {
		channels.clear();
		for (Channel c : arrayList) {
			channels.add(c);
		}
	}

	public void setDcs(ArrayList<DirectConversation> arrayList) {
		dcs.clear();
		for (DirectConversation m : arrayList) {
			dcs.add(m);
		}
	}

	public void setSerMessages(ArrayList<Message> arrayList) {
		serMessages.clear();
		for (Message m : arrayList) {
			serMessages.add(m);
		}
	}

	public void setDcsMessages(ArrayList<Message> arrayList) {
		dcsMessages.clear();
		for (Message m : arrayList) {
			dcsMessages.add(m);
		}
	}

	public void setPinMessages(ArrayList<Message> pm) {
		for (Message m : pm) {
			pinMessages.add(m);
		}
	}
	
	public void setUsers(ObservableList<User> users) {
		this.users = users;
	}
}
