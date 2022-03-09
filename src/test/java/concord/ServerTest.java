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
		a = new User("joemama", "Joe", "j1");
		s = new Server("Valorant", a);
		temp = a;
		admin = s.roleBuilder.createUserRole("admin", temp);
		
		b = new User("angiedaddy", "Angie", "a1");
		temp = b;
		
		mem = s.roleBuilder.createUserRole("member", temp);
		s.roles.put(b, mem);
		
		c = new User("BBB", "Bradshaw", "b1");
		
		hm = new HashMap<User,Role>();
		hm.put(a, admin);
		hm.put(b, mem);
	}

	@Test
	void testServer() {
		boolean add = s.addMember(b);
		assertEquals(true, add);
		assertEquals(s.roles.size(), hm.size());
	}

	@Test
	void testChannel() {
		//add and remove
		ArrayList<Channel> channels = new ArrayList<Channel>();
		Channel general = new Channel();
		general.changeName("general");
		boolean createChannel = s.addChannel(s.roles.get(a), general);
		
		channels.add(general);
		assertEquals(channels, s.channels);
		assertEquals(createChannel, true);
	
		Channel new1 = new Channel();
		new1.changeName("new");
		createChannel = s.addChannel(s.roles.get(b), new1);
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
		assertEquals(pins, s.pins);
		
		s.removePin(m);
		pins.remove(m);
		assertEquals(pins, s.pins);
	}

	@Test
	void testMember() {
		//TODO need to somehow see the list
		boolean member = s.addMember(c);
		assertEquals(s.roles.size(), 3);
		
		temp = c;
		member = s.removeMember(mem, c);
		assertEquals(false, member);
		assertEquals(s.roles.size(), 3);
		
		member = s.removeMember(admin, c);
		assertEquals(true, member);
		assertEquals(s.roles.size(), 2);
	}

	@Test
	void testChangeRole() throws Exception{
		boolean change = s.changeUser(admin, b, "mod");
		assertEquals(true, change);
		
		//have to figure out how to see roles...
		//Role brole = s.roles.get(b);
		//assertEquals(brole, null);
		temp = b;
		mod = s.roleBuilder.createUserRole("mod", temp);
		
		change = s.changeUser(mod, a, "member");
		assertEquals(false, change);
		
		change = s.changeUser(admin, b, "admin");
		assertEquals(true, change);
		
		//demoting admin to member as admin
		change = s.changeUser(admin, a, "member");
		assertEquals(true, change);
	}

}
