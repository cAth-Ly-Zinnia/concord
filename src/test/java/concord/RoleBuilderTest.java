package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleBuilderTest {
	Role r, mod, mem;
	User a, b, c;
	RoleBuilder roleBuilder = new RoleBuilder();
	boolean test;
	
	@BeforeEach
	void setUp() throws Exception {
		a = new User("joemama", "Joe", "j1");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//TODO method for grabbing all booleans go thru each ind
	//boolean
	
	@Test
	void testRoles() {
		//fail("Not yet implemented");
	}

	@Test
	void testCreateUserRole() {
		try {
			//TODO do more assertions for roles
			r = roleBuilder.createUserRole("admin", a);
			assertEquals(true, r.canAddAdmin());
			assertEquals(true, r.canAddChannel());
			assertEquals(true, r.canAddModerator());
			assertEquals(true, r.canInviteUser());
			assertEquals(true, r.canRemoveChannel());
			assertEquals(true, r.canRemoveMember());
			assertEquals(true, r.canRemoveModerator());
			
			mod = roleBuilder.createUserRole("mod", b);
			mem = roleBuilder.createUserRole("member", c);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error");
		}
	}

}
