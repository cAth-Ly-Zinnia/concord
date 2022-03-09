package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UMTest {
	User a, b, c;
	UserManager um = new UserManager();
	@BeforeEach
	void setUp() {
		a = new User("blimey", "Emily", "123");
		b = new User("chaiknees", "Hana", "456");
		c = new User("iamsofriendlyandnice", "Lorelei", "789");
		
		um.createUser("blimey", "Emily", "123");
		um.createUser("chaiknees", "Hana", "456");
		um.createUser("iamsofriendlyandnice", "Lorelei", "789");
	}

	@Test
	void testUserManager() {
		assertEquals(3, um.getUsers().size());
	}

	@Test
	void testCreateUsers() {
		ArrayList<User> test = um.getUsers();
		int create = 1;
		for(User u : test) {
			assertEquals(create, u.getID());
			create++;
		}
	}

}
