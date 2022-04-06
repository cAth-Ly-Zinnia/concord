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
		try {
			a = new User();
			a.setUserName("blimey");
			a.setRealName("Emily");
			a.setPassword("123");
			
			b = new User();
			b.setUserName("chaiknees");
			b.setRealName("Hana");
			b.setPassword("456");

			sm.createServer("test", a);
			sm.createServer("test1", a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testServerManager() {
		try {
			sm.createServer("test2", b);
			ArrayList<Server> total = sm.getServers();
			assertEquals(3, total.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetUserServers() {
		try {
			sm.createServer("test2", b);
			
			ArrayList<Server> test = sm.getUserServers(a);
			assertEquals(2, test.size());
			
			ArrayList<Server> test1 = sm.getUserServers(b);
			assertEquals(1, test1.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
