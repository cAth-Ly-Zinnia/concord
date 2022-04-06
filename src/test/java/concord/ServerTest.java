package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerTest {

	Server s;
	User a, b, c, temp;
	HashMap<User,Role> hm;
	Role admin, mod, mem;
	@BeforeEach
	void setUp() throws Exception {
		a = new User();
		a.setUserName("joemama");
		a.setRealName("Joe");
		a.setPassword("j1");
		
		s = new Server("Valorant", a);
		temp = a;
		admin = s.getRoleBuilder().createUserRole("admin", temp);
		
		b = new User();
		b.setUserName("angiedaddy");
		b.setRealName("Angie");
		b.setPassword("a1");
		temp = b;
		
		mem = s.getRoleBuilder().createUserRole("member", temp);
		s.getRoles().put(b, mem);
		
		c = new User();
		c.setUserName("BBB");
		c.setRealName("Bradshaw");
		c.setPassword("b1");
		temp = c;
		
		hm = new HashMap<User,Role>();
		hm.put(a, admin);
		hm.put(b, mem);
	}
	
	@Test
	void testName() {
		s.setName("val");
		assertEquals("val", s.getName());
	}

	@Test
	void testServer() {
		boolean add = s.addMember(b);
		assertEquals(true, add);
		assertEquals(s.getRoles().size(), hm.size());
	}

	@Test
	void testChannel() {
		//add and remove
		ArrayList<Channel> channels = new ArrayList<Channel>();
		Channel general = new Channel();
		general.setName("general");
		boolean createChannel = s.addChannel(s.getRoles().get(a), general);
		
		channels.add(general);
		assertEquals(channels, s.getChannels());
		assertEquals(createChannel, true);
	
		Channel new1 = new Channel();
		new1.setName("new");
		createChannel = s.addChannel(s.getRoles().get(b), new1);
		assertEquals(createChannel, false);
	}
	

	@Test
	void testPin() {
		//add and remove
		ArrayList<Message> pins = new ArrayList<Message>();
		Message m = new Message();
		m.setMessage("this is pin");
		
		s.addPin(m);
		pins.add(m);
		assertEquals(pins, s.getPins());
		
		s.removePin(m);
		pins.remove(m);
		assertEquals(pins, s.getPins());
	}

	@Test
	void testMember() {
		//TODO need to somehow see the list
		boolean member = s.addMember(c);
		assertEquals(s.getRoles().size(), 3);
		
		temp = c;
		member = s.removeMember(mem, c);
		assertEquals(false, member);
		assertEquals(s.getRoles().size(), 3);
		
		member = s.removeMember(admin, c);
		assertEquals(true, member);
		assertEquals(s.getRoles().size(), 2);
	}

	@Test
	void testChangeRole() throws Exception{
		boolean change = s.changeUser(admin, b, "mod");
		assertEquals(true, change);
		
		//have to figure out how to see roles...
		//Role brole = s.roles.get(b);
		//assertEquals(brole, null);
		temp = b;
		mod = s.getRoleBuilder().createUserRole("mod", temp);
		
		change = s.changeUser(mod, a, "member");
		assertEquals(false, change);
		
		change = s.changeUser(admin, b, "admin");
		assertEquals(true, change);
		
		//demoting admin to member as admin
		change = s.changeUser(admin, a, "member");
		assertEquals(true, change);
	}

}
