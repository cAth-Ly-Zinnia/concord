package concord;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	HashMap<User, Role> users;
	ArrayList<Channel> channels;
	ArrayList<Message> messages;
	ArrayList<Message> pins;
	String name;
	RoleBuilder roleBuilder = new RoleBuilder();
	
	public Server() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean invite(Role requester, User user) {
		//TODO write all functions here as booleans to test out later!!!
		return true;
		
	}
	
	public boolean kick(Role requester, User user) {
		if(requester.canRemoveMember()) {
			users.remove(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addChannel(Role requester, Channel channel) {
		if(requester.canAddChannel()) {
			channels.add(channel);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void deleteChannel(Channel channel) {
		channels.remove(channel);
	}
	
	public void addPin(Message msg){
		pins.add(msg);
	}
	
	public void removePin(Message msg) {
		pins.remove(msg);
	}
	
	public void addMember(User user) {
		//figure out the roles here use hash map
		Role role;
		try {
			role = roleBuilder.createUserRole("member", user);
			users.put(user, role);
		} catch (Exception e) {
			System.out.println("interesting...");
			e.printStackTrace();
		}
	}
	
	public boolean removeMember(Role requester, User kickedUser) {
		//same here
		if(requester.canRemoveMember()) {
			users.remove(kickedUser);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean changeUser(Role requester, User changedUser, String newRole) {
		Role role;
		boolean canAdmin = newRole.equals("admin") && requester.canAddAdmin();
		boolean canMod = newRole.equals("mod") && requester.canAddModerator();
		//for admin to change admin/mod to member feelsbad...
		boolean canDemote = newRole.equals("member") && requester.canAddAdmin();
		try {
			if (canAdmin || canMod || canDemote) {
				role = roleBuilder.createUserRole(newRole, changedUser);
				users.put(changedUser, role);
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		//something horrible happened if it reaches here
		return false;
	}

}
