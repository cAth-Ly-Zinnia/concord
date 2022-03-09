package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SMTest {
	ServerManager sm = new ServerManager();
	User a, b;
	@BeforeEach
	void setUp(){
		a = new User("blimey", "Emily", "123");
		b = new User("chaiknees", "Hana", "456");
	}

	@Test
	void testServerManager() {
		try {
			sm.createServer("test", a);
			sm.createServer("test1", a);
			sm.createServer("test2", b);
			ArrayList<Server> total = sm.getServers();
			assertEquals(3, total.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetUserServers() {
		ArrayList<Server> test = sm.getUserServers(a);
		assertEquals(2, test.size());
		
		ArrayList<Server> test1 = sm.getUserServers(b);
		assertEquals(1, test1.size());
	}

}
