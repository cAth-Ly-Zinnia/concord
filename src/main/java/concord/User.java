package concord;

import java.util.ArrayList;

public class User {
	private String profileData;
	private String userName;
	private String realName;
	private String password;
	
	private int id;
	
	private ArrayList<User> blocks = new ArrayList<User>();
	
	private String urlPic;

	public User(String un, String rn, String pw) {
		// TODO Auto-generated constructor stub
		//is there a better way defining users without having to have arraylist as a parameter
		this.userName = un;
		this.realName = rn;
		this.password = pw;
	}
	
	//addBlock
	public void addBlock(User u) {
		if(this != u) {
			blocks.add(u);
		}
	}
	
	public void removeBlock(User u) {
		if(this != u) {
			blocks.remove(u);
		}
	}

	public ArrayList<User> getBlocks() {
		return blocks;
	}
	
	public void setProfileData(String profileData) {
		this.profileData = profileData;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the profileData
	 */
	public String getProfileData() {
		return profileData;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @return the urlPic
	 */
	public String getUrlPic() {
		return urlPic;
	}

	/**
	 * @param urlPic the urlPic to set
	 */
	public void setUrlPic(String urlPic) {
		this.urlPic = urlPic;
	}
	
	public void setID(int i) {
		this.id = i;
	}
	
	public int getID() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
