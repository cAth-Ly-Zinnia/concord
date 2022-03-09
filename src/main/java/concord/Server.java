package concord;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	HashMap<User, Role> roles = new HashMap<User, Role>();
	ArrayList<Channel> channels = new ArrayList<Channel>();
	ArrayList<Message> messages = new ArrayList<Message>();
	ArrayList<Message> pins = new ArrayList<Message>();
	String name;
	RoleBuilder roleBuilder = new RoleBuilder();
	
	public Server(String n, User u) {
		// TODO Auto-generated constructor stub
		try {
			Role admin = this.roleBuilder.createUserRole("admin", u);
			this.name = n;
			roles.put(u, admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//for now everyone can change the name TODO change permissions
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean invite(Role requester, User user) {
		//TODO write all functions here as booleans to test out later!!!
		return true;
		
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
	
	public boolean deleteChannel(Role requester, Channel channel) {
		if(requester.canAddChannel()) {
			channels.remove(channel);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addPin(Message msg){
		pins.add(msg);
	}
	
	public void removePin(Message msg) {
		pins.remove(msg);
	}
	
	public boolean addMember(User user) {
		//figure out the roles here use hash map
		Role role;
		try {
			role = roleBuilder.createUserRole("member", user);
			roles.put(user, role);
			return true;
		} catch (Exception e) {
			System.out.println("interesting...");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeMember(Role requester, User kickedUser) {
		//same here
		if(requester.canRemoveMember()) {
			roles.remove(kickedUser);
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
				roles.put(changedUser, role);
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
