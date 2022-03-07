package concord;

public class RoleBuilder {

	public RoleBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public Role createUserRole(String role, User u) throws Exception {
		//need to define the permissions
		// addModerator, addChannel, addAdmin, removeMember, removeModerator, inviteUser, removeChannel;
		if (role.equals("admin")) {
			return new Role(true, true, true, true, true, true, true);
		}
		else if(role.equals("mod")) {
			return new Role(false, true, false, true, false, true, true);
		}
		else if (role.equals("member")){
			return new Role(false, false, false, false, false, false, false);
	
		}
		else {
			//TODO implements user role
			throw new Exception("ya done goofed");
		}
	}
}
