package concord;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTest {
	Message m;
	@BeforeEach
	void setUp() throws Exception {
		m = new Message();
		m.setMessage("hello");
	}

	@Test
	void testMessage() {
		assertEquals("hello", m.getMessage());
	}

	/*
	@Test
	void testNotifyUser() {
		fail("Not yet implemented");
	}
	*/
}
