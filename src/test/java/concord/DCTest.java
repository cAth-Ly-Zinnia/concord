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
		a = new User("blimey", "Emily", "123");
		b = new User("chaiknees", "Hana", "456");
		c = new User("iamsofriendlyandnice", "Lorelei", "789");
		
		dc = new DirectConversation();
		dc.addUser(a);
		dc.addUser(b);
	}

	@Test
	void testDirectConversation() {
		assertEquals(2, dc.getUsers().size());
	}

	@Test
	void testSendMessage() {
		hi.setMessage("hey everyone!!!");
		hi.setTS();
		dc.sendMessage(hi);
		assertEquals(1, dc.getMessages().size());
		
		bye.setMessage("bye everyone!!!");
		bye.setTS();
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
	
	/*@Test
	//bugged rn
	void testGetLastTimestamp() {
		assertEquals(bye.getTS(), dc.getLastTimestamp());
	}*/

}
