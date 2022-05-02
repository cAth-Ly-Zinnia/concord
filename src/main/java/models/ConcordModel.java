package models;

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
}
