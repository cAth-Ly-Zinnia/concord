package concord;

public class Role{
	
	private boolean addModerator, addChannel, addAdmin, removeMember, removeModerator, inviteUser, removeChannel;

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


	public boolean isAddModerator() {
		return addModerator;
	}

	public void setAddModerator(boolean addModerator) {
		this.addModerator = addModerator;
	}

	public boolean isAddChannel() {
		return addChannel;
	}

	public void setAddChannel(boolean addChannel) {
		this.addChannel = addChannel;
	}

	public boolean isAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(boolean addAdmin) {
		this.addAdmin = addAdmin;
	}

	public boolean isRemoveMember() {
		return removeMember;
	}

	public void setRemoveMember(boolean removeMember) {
		this.removeMember = removeMember;
	}

	public boolean isRemoveModerator() {
		return removeModerator;
	}

	public void setRemoveModerator(boolean removeModerator) {
		this.removeModerator = removeModerator;
	}

	public boolean isInviteUser() {
		return inviteUser;
	}

	public void setInviteUser(boolean inviteUser) {
		this.inviteUser = inviteUser;
	}

	public boolean isRemoveChannel() {
		return removeChannel;
	}

	public void setRemoveChannel(boolean removeChannel) {
		this.removeChannel = removeChannel;
	}

	public boolean checkStatus(Role u, boolean a, boolean b, boolean c, boolean d,
			boolean e, boolean f, boolean g) {
		if(u.isAddModerator() != a) {
			return false;
		}
		if(u.isAddChannel() != b) {
			return false;
		}
		if(u.isAddAdmin() != c) {
			return false;
		}
		if(u.isRemoveMember() != d) {
			return false;
		}
		if(u.isRemoveModerator() != e) {
			return false;
		}
		if(u.isInviteUser() != f) {
			return false;
		}
		if(u.isRemoveChannel() != g) {
			return false;
		}
		return true;
	}
	
	public boolean equals(Role that) {
		boolean a = that.isAddModerator();
		boolean b = that.isAddChannel();
		boolean c = that.isAddAdmin();
		boolean d = that.isRemoveMember();
		boolean e = that.isRemoveModerator();
		boolean f = that.isInviteUser();
		boolean g = that.isRemoveChannel();
		
		return this.checkStatus(that, a, b, c, d, e, f, g);
	}

	
}
