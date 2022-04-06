package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleBuilderTest {
	Role r, mod, mem;
	User a, b, c;
	RoleBuilder roleBuilder = new RoleBuilder();
	boolean test;
	
	@BeforeEach
	void setUp() throws Exception {
		a = new User();
		a.setUserName("joemama");
		a.setRealName("Joe");
		a.setPassword("j1");
		
		b = new User();
		b.setUserName("chaiknees");
		b.setRealName("Hana");
		b.setPassword("456");
	}

	@Test
	void testRoles() {
		
	}

	@Test
	void testCreateUserRole() {
		try {
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
			e.printStackTrace();
			fail("error");
		}
	}

}
