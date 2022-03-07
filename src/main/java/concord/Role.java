package concord;

public class Role{
	
	boolean addModerator, addChannel, addAdmin, removeMember, removeModerator, inviteUser, removeChannel;

	public Role() {
		this(false, false, false, false,
				false, false, false);
	}


	public Role(boolean addModerator, boolean addChannel, boolean addAdmin, boolean removeMember,
			boolean removeModerator, boolean inviteUser, boolean removeChannel) {
		super();
		this.addModerator = addModerator;
		this.addChannel = addChannel;
		this.addAdmin = addAdmin;
		this.removeMember = removeMember;
		this.removeModerator = removeModerator;
		this.inviteUser = inviteUser;
		this.removeChannel = removeChannel;
	}


	public boolean canAddModerator() {
		return addModerator;
	}

	public void setAddModerator(boolean addModerator) {
		this.addModerator = addModerator;
	}

	public boolean canAddChannel() {
		return addChannel;
	}

	public void setAddChannel(boolean addChannel) {
		this.addChannel = addChannel;
	}

	public boolean canAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(boolean addAdmin) {
		this.addAdmin = addAdmin;
	}

	public boolean canRemoveMember() {
		return removeMember;
	}

	public void setRemoveMember(boolean removeMember) {
		this.removeMember = removeMember;
	}

	public boolean canRemoveModerator() {
		return removeModerator;
	}

	public void setRemoveModerator(boolean removeModerator) {
		this.removeModerator = removeModerator;
	}

	public boolean canInviteUser() {
		return inviteUser;
	}

	public void setInviteUser(boolean inviteUser) {
		this.inviteUser = inviteUser;
	}

	public boolean canRemoveChannel() {
		return removeChannel;
	}

	public void setRemoveChannel(boolean removeChannel) {
		this.removeChannel = removeChannel;
	}
	
}
