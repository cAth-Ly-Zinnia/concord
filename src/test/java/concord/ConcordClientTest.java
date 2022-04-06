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

class ConcordClientTest {
	Registry registry;
	ConcordServer cs;
	UserManager um;
	Concord c;
	User u;
	
	@BeforeEach
	void setUp() throws Exception {
		cs = new ConcordServer();
		registry = LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORDS", cs);
	}

	@AfterEach
	void tearDown() throws Exception {
		registry.unbind("CONCORDS");
	}

	@Test
	void testSubmit() {
		boolean answer = false;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			answer = csi.verify("chan", "takrak");
			assertEquals(false, answer);
			
			csi.getConcord().getUm().createUser("chan", "chantakrak", "takrak");
			answer = csi.verify("chan", "takrak");
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		
		assertEquals(true, answer);
		
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
	}
	
	
	void testFindUser() {
		User u = null;
		User u1 = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.getConcord().getUm().getUser(1);
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals(u1, u);
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
			
			csi.kick(u1, joe);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		Role r = joe.getRole(u1);
		assertEquals(null , r);
	}
	
	void testInvite() {
		fail("Not yet implemented");
	}

	
	void testAccept() {
		fail("Not yet implemented");
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
			
			csi.changeServerName(joe, "mama");
			assertEquals("mama", joe.getName());

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
			
			joe = csi.getConcord().getSm().getServer("mama");
			csi.changeServerName(joe, "joe");
			
			c = new Channel();
			c.setName("general");
			Role admin = joe.getRole(u);
			
			joe.addChannel(admin, c);
			
			csi.changeChannelName(c, joe, "j");
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals("j", joe.getChannel("j").getName());
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
			csi.addChannel(joe, c);
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
			assertEquals("j", joe.getChannel("j").getName());
			
			c = joe.getChannel("j");
			csi.deleteChannel(joe, c);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals(null, joe.getChannel("j"));
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
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			joe = csi.getConcord().getSm().getServer("joe");
			
			csi.unPin(joe, joe.getPin().get(0));
			assertEquals(0, joe.getPin().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

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
			csi.changeRole(u1, "mod", joe);
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
			
			csi.addBlock(u1);
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
			
			csi.removeBlock(u);
			int block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(1, block);
			
			csi.removeBlock(u1);
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
			
			csi.setProfileData("bored");
			
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
			
			csi.setUsername("iamsofriendly");
			assertEquals("iamsofriendly", u.getUserName());
			
			csi.setUsername("chan");
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
			
			csi.setProfilePic("fortnite.jpg");
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
			
			csi.sendPrivateMessage(m, u, dc);
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
