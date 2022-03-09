package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChannelTest {
	Channel c;
	@BeforeEach
	void setUp(){
		c = new Channel();
		c.changeName("general");
	}

	@Test
	void testChannel() {
		assertEquals("general", c.getChannelName());
	}

	@Test
	void sendMessage() {
		Message m = new Message();
		m.setMessage("hello channel");
		c.sendMessage(m);
		
		assertEquals(true, c.getMessages().contains(m));
	}

}
