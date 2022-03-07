package concord;

import java.util.ArrayList;

public class User {
	String profileData;
	String userName;
	String realName;
	
	//int id;
	
	ArrayList<User> blocks;
	
	String urlPic;

	public User(String un, String rn) {
		// TODO Auto-generated constructor stub
		//is there a better way defining users without having to have arraylist as a parameter
		this.userName = un;
		this.realName = rn;
	}
	
	//addBlock
	public void addBlock(User u) {
		blocks.add(u);
	}
	
	public void removeBlock(User u) {
		blocks.remove(u);
	}

	/**
	 * @param profileData the profileData to set
	 */
	public void setProfileData(String profileData) {
		this.profileData = profileData;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param urlPic the urlPic to set
	 */
	public void setUrlPic(String urlPic) {
		this.urlPic = urlPic;
	}
	
	

}
