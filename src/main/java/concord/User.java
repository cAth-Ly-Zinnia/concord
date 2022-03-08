package concord;

import java.util.ArrayList;

public class User {
	String profileData;
	String userName;
	String realName;
	String password;
	
	int id;
	
	ArrayList<User> blocks = new ArrayList<User>();
	
	String urlPic;

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

	
	/**
	 * @param profileData the profileData to set
	 */
	
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
	
	

}
