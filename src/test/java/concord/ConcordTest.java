package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcordTest {

	Concord c;
	@BeforeEach
	void setUp(){
		c = new Concord();
	}

	@Test
	void testConcord() {
		assertInstanceOf(DirectConversationManager.class, c.getDcm());
		assertInstanceOf(ServerManager.class, c.getSm());
		assertInstanceOf(UserManager.class, c.getUm());
	}
	
	@Test
	void testXML() {
		
		c.getUm().createUser("chan", "chantakrak", "takrak");
		User u= c.getUm().getUser(1);
		u.setProfileData("joe");
		u.setUrlPic("wrekc.jpg");
		
		c.getUm().createUser("tako", "ben", "truck");
		User u1= c.getUm().getUser(2);
		u1.setProfileData("mama");
		u1.setUrlPic("yoinks.jpg");
		
		DirectConversation dc = new DirectConversation();
		dc.addUser(u);
		dc.addUser(u1);
		Message m = new Message();
		m.setContent("hello");
		m.setUser(u);
		dc.sendMessage(m);
		c.getDcm().getDcs().add(dc);
		
		
		try {
			c.getSm().createServer("joe", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Server joe = c.getSm().getServer("joe");
		Role admin = joe.getRole(u);
		Channel c1 = new Channel();
		c1.setName("joe");
		joe.addChannel(admin, c1);
		Level l = joe.findLevel(u);
		c1.sendMessage(m, l);

		assertEquals(1, l.getLvl());
		assertEquals(3, l.getLvlProgress());
		
		c1.sendMessage(m, l);

		assertEquals(2, l.getLvl());
		assertEquals(0, l.getLvlProgress());
		
		c.serializeToXML();
		Concord diskC = Concord.readXML();
		
		assertTrue(c.equals(diskC));
	}

}
