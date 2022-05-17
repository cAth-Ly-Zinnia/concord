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
		
	}
	
	public void resetChannels(Concord c, Server s) {
			
	}
	
	public void resetDcsMessage(Concord c, DirectConversation dc) {
		
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
	

	public void setServers(ObservableList<Server> servers) {
		this.servers = servers;
	}

	public void setChannels(ObservableList<Channel> channels) {
		this.channels = channels;
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

	public void setPinMessages(ObservableList<Message> pinMessages) {
		this.pinMessages = pinMessages;
	}
	
	public void setUsers(ObservableList<User> users) {
		this.users = users;
	}
}
