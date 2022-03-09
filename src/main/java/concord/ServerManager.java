package concord;

import java.util.ArrayList;

public class ServerManager {
	ArrayList<Server> sm = new ArrayList<Server>();
	RoleBuilder roleBuilder = new RoleBuilder();
	public ServerManager() {
		
	}
	
	public ArrayList<Server> getUserServers(User a){
		ArrayList<Server> userSer= new ArrayList<Server>();
		for (Server s : sm) {
			if(s.roles.get(a) != null) {
				userSer.add(s);
			}
		}
		return userSer;
	}

	public ArrayList<Server> getServers(){
		return sm;
	}
	
	public Server createServer(String name, User a) throws Exception{
		Server server = new Server(name, a);
		sm.add(server);		
		return server;
	}
}
