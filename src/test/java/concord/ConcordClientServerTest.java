package concord;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcordClientServerTest {
	Registry registry;
	ConcordServer cs;
	UserManager um;
	Concord c;
	User u;
	ConcordClient cc;
	ConcordServerInterface csi;
	
	@BeforeEach
	void setUp() throws Exception {
		cs = new ConcordServer();
		registry = LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORDS", cs);
		cc = new ConcordClient();
	}

	@AfterEach
	void tearDown() throws Exception {
		registry.unbind("CONCORDS");
		csi = null;
	}

	@Test
	void testSubmit() {
		User answer = null;
		
		try {
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			cc.setCsi(csi);
			//System.out.println(csi.findUserById(2).getUserName());
			answer = csi.verify("chan", "takrak");
			assertNull(answer);
			
			csi.getConcord().getUm().createUser("chan", "chantakrak", "takrak");
			answer = csi.verify("chan", "takrak");
			
			cc.verify("chan", "takrak");
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		
		assertNotNull(answer);
		assertEquals(cc.getU(), answer);
		
		testFindUser();
		testKick();
		testChangeServerName();
		testChangeChannelName();
		testAddChannel();
		testDeleteChannel();
		testAddPin();
		testUnPin();
		testChangeRole();
		testAddBlock();
		testRemoveBlock();
		testSetProfileData();
		testSetUsername();
		testSetProfilePic();
		testSendPrivateMessage();
		testAccept();
	}
	
	
	void testFindUser() {
		User u = null;
		User u1 = null;
		User u2 = null;
		System.out.println("running find user");
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.getConcord().getUm().getUser(1);
			u2 = cc.findUserById(1);
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals(u1, u);
		assertEquals(u2, u);
	}
	
	
	void testKick() {
		User u = null;
		User u1 = null;
		Server joe = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			
			csi.getConcord().getUm().createUser("tako", "japan", "yaki");
			
			csi.getConcord().getSm().createServer("joe", u);
			joe = csi.getConcord().getSm().getServer("joe");

			u1 = csi.findUserById(2);
			assertEquals("tako", u1.getUserName());
			
			joe.addMember(u1);
			Role member = joe.getRole(u1);
			assertNotNull(member);
			
			csi.kick(u.getId(), u1, joe);
			Role r = joe.getRole(u1);
			assertEquals(null , r);
			
			joe.addMember(u1);
			member = joe.getRole(u1);
			assertNotNull(member);
			
			cc.kick(u1, joe);
			r = joe.getRole(u1);
			assertEquals(null , r);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}
	
	//not implemented yet
	void testInvite() {
		
	}

	
	void testAccept() {
		User u = null;
		User u1 = null;
		Server joe = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			joe = csi.getConcord().getSm().getServer("joe");
			
			u = csi.findUserById(2);
			assertNull(joe.getRole(u));
			
			csi.accept(u, joe);
			assertNotNull(joe.getRole(u));
			
			u1 = cc.findUserById(1);
			csi.kick(u1.getId(), u, joe);
			Role r = joe.getRole(u);
			assertEquals(null , r);
			
			cc.accept(u, joe);
			assertNotNull(joe.getRole(u));
			
			cc.kick(u, joe);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	} 

	
	
	void testChangeServerName() {
		User u = null;
		Server joe = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			csi.verify("chan", "takrak");

			joe = csi.getConcord().getSm().getServer("joe");
			assertEquals("joe", joe.getName());
			
			csi.changeServerName(u.getId(), joe, "mama");
			assertEquals("mama", joe.getName());
			
			cc.changeServerName(joe, "mama1");
			assertEquals("mama1", joe.getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		
	}
	
	void testChangeChannelName() {
		User u = null;
		Server joe = null;
		Channel c = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			
			joe = csi.getConcord().getSm().getServer("mama1");
			csi.changeServerName(u.getId(), joe, "joe");
			
			c = new Channel();
			c.setName("general");
			Role admin = joe.getRole(u);
			
			joe.addChannel(admin, c);
			
			csi.changeChannelName(u.getId(), c, joe, "j");
			assertEquals("j", joe.getChannel("j").getName());
			
			cc.changeChannelName(c, joe, "joe");
			assertEquals("joe", joe.getChannel("joe").getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		
	}

	void testAddChannel() {
		User u = null;
		Server joe = null;
		Channel c = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);

			joe = csi.getConcord().getSm().getServer("joe");
			c = new Channel();
			c.setName("chat");
			csi.addChannel(u.getId(), joe, c);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals(c, joe.getChannel("chat"));
	}

	void testDeleteChannel() {
		User u = null;
		Server joe = null;
		Channel c = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			
			joe = csi.getConcord().getSm().getServer("joe");
			assertEquals("joe", joe.getChannel("joe").getName());
			
			c = joe.getChannel("joe");
			csi.deleteChannel(u.getId(), joe, c);
			assertEquals(null, joe.getChannel("joe"));
			
			//add channel for cc test is here
			cc.addChannel(joe, c);
			assertEquals(c, joe.getChannel("joe"));
			
			//delete channel for cc
			cc.deleteChannel(joe, c);
			assertEquals(null, joe.getChannel("joe"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testAddPin() {
		User u = null;
		Server joe = null;
		Message m = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			joe = csi.getConcord().getSm().getServer("joe");
			
			m = new Message();
			m.setMessage("This is a message");
			assertEquals(0, joe.getPin().size());
			
			csi.addPin(joe, m);
			assertEquals(1, joe.getPin().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		
	}

	void testUnPin() {
		User u = null;
		Server joe = null;
		Message m = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			joe = csi.getConcord().getSm().getServer("joe");
			
			csi.unPin(joe, joe.getPin().get(0));
			assertEquals(0, joe.getPin().size());
			
			m = new Message();
			m.setMessage("This is a message");
			cc.addPin(joe, m);
			assertEquals(1, joe.getPin().size());
			
			cc.unPin(joe, joe.getPin().get(0));
			assertEquals(0, joe.getPin().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	//TODO left off here
	void testChangeRole() {
		User u = null;
		Server joe = null;
		User u1 = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			joe = csi.getConcord().getSm().getServer("joe");
			
			csi.getConcord().getUm().createUser("tamago", "egg", "yaki");
			u1 = csi.findUserById(3);
			joe.addMember(u1);
			
			assertEquals("tamago", u1.getUserName());
			
			Role member = joe.getRole(u1);
			Role admin = joe.getRole(u);
			csi.changeRole(u.getId(), u1, "mod", joe);
			Role mod = joe.getRole(u1);
			
			assertNotEquals(member, mod);
			assertNotEquals(admin, mod);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testAddBlock() {
		User u = null;
		User u1 = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.findUserById(3);
			
			int block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(0, block);
			
			csi.addBlock(u.getId(), u1);
			block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(1, block);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testRemoveBlock() {
		User u = null;
		User u1 = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.findUserById(3);
			
			csi.removeBlock(u.getId(), u);
			int block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(1, block);
			
			csi.removeBlock(u.getId(), u1);
			block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(0, block);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testSetProfileData() {
		User u = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			assertEquals(null, u.getProfileData());
			
			csi.setProfileData(u.getId(), "bored");
			
			assertEquals("bored", u.getProfileData());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testSetUsername() {
		User u = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			assertEquals("chan", u.getUserName());
			
			csi.setUsername(u.getId(), "iamsofriendly");
			assertEquals("iamsofriendly", u.getUserName());
			
			csi.setUsername(u.getId(), "chan");
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testSetProfilePic() {
		User u = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			assertEquals(null, u.getUrlPic());
			
			csi.setProfilePic(u.getId(), "fortnite.jpg");
			assertEquals("fortnite.jpg", u.getUrlPic());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testSendPrivateMessage() {
		User u = null;
		User u1 = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.findUserById(3);
			
			ArrayList<User> users = new ArrayList<User>();
			users.add(u);
			users.add(u1);
			
			DirectConversation dc = csi.getConcord().getDcm().createDC(users);
			assertEquals(0, dc.getMessages().size());
			
			Message m = new Message();
			m.setContent("hello");
			
			csi.sendPrivateMessage(m, dc);
			assertEquals(1, dc.getMessages().size());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testSendChannelMessage() {
		User u = null;
		Server joe = null;
		Channel c = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			
			joe = csi.getConcord().getSm().getServer("joe");
			c = joe.getChannel("general");
			assertEquals(0, c.getMessages().size());
			
			Message m = new Message();
			m.setContent("hello");
			
			csi.sendChannelMessage(m, joe, c);
			assertEquals(1, c.getMessages().size());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}
	

}
