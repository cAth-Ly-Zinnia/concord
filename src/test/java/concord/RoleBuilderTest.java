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
			boolean isValid = r.checkStatus(r, true, true, true, true, true, true, true);
			assertEquals(true, isValid);
			
			mod = roleBuilder.createUserRole("mod", b);
			isValid =  mod.checkStatus(mod, false, true, false, true, false, true, true);
			assertEquals(true, isValid);
			
			mem = roleBuilder.createUserRole("member", c);
			isValid =  mem.checkStatus(mem, false, false, false, false, false, false, false);
			assertEquals(true, isValid);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error");
		}
	}

}
