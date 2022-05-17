package concord;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602199967355945704L;
	private String profileData;
	private String userName;
	private String realName;
	private String password;
	
	private int id;
	
	private ArrayList<User> blocks = new ArrayList<User>();
	
	private String urlPic;

	public User() {
		// TODO Auto-generated constructor stub
		//is there a better way defining users without having to have arraylist as a parameter
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
	 * @return the profileData
	 */
	public String getProfileData() {
		return profileData;
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
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return the blocks
	 */
	public ArrayList<User> getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void setBlocks(ArrayList<User> blocks) {
		this.blocks = blocks;
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
	
	public boolean equals(User u) {
		if(id == u.getId()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return userName;
	}
}