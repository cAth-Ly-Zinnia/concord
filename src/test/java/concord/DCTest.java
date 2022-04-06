package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DCTest {
	DirectConversation dc;
	User a, b, c;
	Message hi = new Message(); 
	Message bye= new Message();
	@BeforeEach
	void setUp() throws Exception {
		a = new User();
		a.setUserName("blimey");
		a.setRealName("Emily");
		a.setPassword("123");
		
		b = new User();
		b.setUserName("chaiknees");
		b.setRealName("Hana");
		b.setPassword("456");
		
		c = new User();
		c.setUserName("iamsofriendlyandnice");
		c.setRealName("Lorelei");
		c.setPassword("789");
		
		dc = new DirectConversation();
		dc.addUser(a);
		dc.addUser(b);
		
		hi.setMessage("hey everyone!!!");
		hi.setTimeStamp(null);
		dc.sendMessage(hi);
	}

	@Test
	void testDirectConversation() {
		assertEquals(2, dc.getUsers().size());
	}

	@Test
	void testSendMessage() {
		//this is the hi msg
		assertEquals(1, dc.getMessages().size());
		
		bye.setMessage("bye everyone!!!");
		bye.setTimeStamp(null);
		dc.sendMessage(bye);
		assertEquals(2, dc.getMessages().size());
	}

	@Test
	void testUser() {
		dc.addUser(c);
		assertEquals(3, dc.getUsers().size());
		
		dc.removeUser(c);
		assertEquals(2, dc.getUsers().size());
	}
	
	@Test
	void testGetLastTimestamp() {
		assertEquals(hi, dc.getLastMessage());
		
		//add more msgs here to check if it grabs most recent!!!
	}

}
