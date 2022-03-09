package concord;

import static org.junit.jupiter.api.Assertions.*;

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
		assertInstanceOf(DirectConversationManager.class, c.d);
		assertInstanceOf(ServerManager.class, c.s);
		assertInstanceOf(UserManager.class, c.u);
	}

}
