package concord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5151351892689964862L;
	private HashMap<User, Role> roles = new HashMap<User, Role>();
	private HashMap<User, Level> levels = new HashMap<User, Level>();
	private ArrayList<Channel> channels = new ArrayList<Channel>();
	private ArrayList<Message> pins = new ArrayList<Message>();
	private String name;
	private RoleBuilder roleBuilder = new RoleBuilder();
	
	public Server(String n, User u) {
		// TODO Auto-generated constructor stub
		try {
			Role admin = this.roleBuilder.createUserRole("admin", u);
			this.name = n;
			roles.put(u, admin);
			Channel general = new Channel();
			general.setName("general");
			channels.add(general);
			Level l = new Level();
			levels.put(u, l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Server()
	{
		new Server(name, new User());
	}
	
	public User[] hashToArray() {
		User[] u = {};
		return this.roles.keySet().toArray(u);
	}
	
	public boolean contains(Message pin) {
		for(Message p : pins) {
			if(p.equals(pin)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<User> getUsers(){
		HashSet<User> u1 = new HashSet<User>(roles.keySet());
		ArrayList<User> users = new ArrayList<User>(u1);
		return users;
	}
	
	public User findEquivalentUser(int id) {
		HashSet<User> users = new HashSet<User>(roles.keySet());
		User[] a = new User[roles.size()];
		users.toArray(a);
		for(User user:a) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public Level findLevel(User u) {
		return levels.get(u);
	}
	
	public boolean contains(Channel that) {
		for(Channel c: channels) {
			if(c.equals(that)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(Server that) {
		for(Message pin: pins) {
			if(!that.contains(pin)) {
				return false;
			}
		}
		
		for(Channel c: channels) {
			if(!that.contains(c)) {
				return false;
			}
		}
		
		User[] u = hashToArray();
		User[] u1 = that.hashToArray();
		for(int i = 0; i < u.length; i++) {
			if(!u[i].equals(u1[i])){
				return false;
			}
			if(!roles.get(u[i]).equals(that.getRoles().get(u1[i]))) {
				return false;
			}
		}
		
		if(!name.equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	public Role getRole(User u) {
		return roles.get(u);
	}
	
	public Channel getChannel(String channel) {
		for (Channel c : channels) {
			if (c.getName().equals(channel)) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Message> getPin(){
		return pins;
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
		if(requester.isAddChannel()) {
			channels.add(channel);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean deleteChannel(Role requester, Channel channel) {
		for(Channel c :this.getChannels()) {
			if (c.getName().equals(channel.getName())) {
				System.out.println("found channel");
				if(requester.isRemoveChannel()) {
					channels.remove(c);
					return true;
				}
			}
		}
		return false;
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
		Level l;
		try {
			role = roleBuilder.createUserRole("member", user);
			roles.put(user, role);
			l = new Level();
			levels.put(user, l);
			return true;
		} catch (Exception e) {
			System.out.println("interesting...");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeMember(Role requester, User kickedUser) {
		//same here
		if(requester.isRemoveMember()) {
			roles.remove(kickedUser);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean changeUser(Role requester, User changedUser, String newRole) {
		Role role;
		boolean canAdmin = newRole.equals("admin") && requester.isAddAdmin();
		boolean canMod = newRole.equals("mod") && requester.isAddModerator();
		//for admin to change admin/mod to member feelsbad...
		boolean canDemote = newRole.equals("member") && requester.isAddAdmin();
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

	/**
	 * @return the roles
	 */
	public HashMap<User, Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(HashMap<User, Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the channels
	 */
	public ArrayList<Channel> getChannels() {
		return channels;
	}

	/**
	 * @param channels the channels to set
	 */
	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}

	/**
	 * @return the pins
	 */
	public ArrayList<Message> getPins() {
		return pins;
	}

	/**
	 * @param pins the pins to set
	 */
	public void setPins(ArrayList<Message> pins) {
		this.pins = pins;
	}

	/**
	 * @return the roleBuilder
	 */
	public RoleBuilder getRoleBuilder() {
		return roleBuilder;
	}

	/**
	 * @param roleBuilder the roleBuilder to set
	 */
	public void setRoleBuilder(RoleBuilder roleBuilder) {
		this.roleBuilder = roleBuilder;
	}
}
