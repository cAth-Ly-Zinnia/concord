package concord;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> u = new ArrayList<User>();
	private int created = 0;
	
	
	public UserManager() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ArrayList<User> getUsers(){
		return u;
	}
	
	public User createUser(String un, String rn, String pw) {
		User us = new User(un, rn, pw);
		created = created + 1;
		us.setID(created);
		u.add(us);
		
		return us;
	}
}
