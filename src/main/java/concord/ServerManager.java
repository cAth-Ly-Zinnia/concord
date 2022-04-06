package concord;

import java.util.ArrayList;

public class ServerManager {
	ArrayList<Server> servers;
	RoleBuilder roleBuilder = new RoleBuilder();
	public ServerManager() {
		this.servers = new ArrayList<Server>();
	}
	
	public ArrayList<Server> getUserServers(User a){
		ArrayList<Server> userSer= new ArrayList<Server>();
		for (Server s : servers) {
			if(s.getRoles().get(a) != null) {
				userSer.add(s);
			}
		}
		return userSer;
	}

	public ArrayList<Server> getServers(){
		return servers;
	}
	
	public Server getServer(String name){
		for (Server s : servers) {
			if (s.getName() == name) {
				return s;
			}
		}
		return null;
	}
	
	public Server createServer(String name, User a) throws Exception{
		Server server = new Server(name, a);
		servers.add(server);		
		return server;
	}

	/**
	 * @param sm the sm to set
	 */
	public void setServers(ArrayList<Server> servers) {
		this.servers = servers;
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
	
	public boolean contains(Server s) {
		for (Server one: servers) {
			if(one.equals(s)) {
				return true;
			}
		}
		return false;
	}
}
