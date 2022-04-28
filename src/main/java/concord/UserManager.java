package concord;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> users;
	private int id = 0;
	
	
	public UserManager() {
		// TODO Auto-generated constructor stub
		this.users = new ArrayList<User>();
		
	}
	
	
	public User getUser(int id) {
		for(User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User verify (String username, String pw) {
		for (User user : this.getUsers()) {
			if (user.getUserName().equals(username)) {
				if (user.getPassword().equals(pw)) {
					return user;
				}
			}
		}
		System.out.println("did not find");
		return null;
	}
	
	public void createUser(String un, String rn, String pw) {
		User us = new User();
		
		us.setUserName(un);
		us.setRealName(rn);
		us.setPassword(pw);
		
		id = id + 1;
		us.setId(id);
		users.add(us);
	}


	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}


	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public boolean contains(User u) {
		for (User one: users) {
			if(one.equals(u)) {
				return true;
			}
		}
		return false;
	}

}
