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
		testSendChannelMessage();
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
			
			csi.accept(u.getId(), joe);
			assertNotNull(joe.getRole(u));
			
			u1 = cc.findUserById(1);
			csi.kick(u1.getId(), u, joe);
			Role r = joe.getRole(u);
			assertEquals(null , r);
			
			csi.accept(u.getId(), joe);
			assertNotNull(joe.getRole(u));
			
			cc.kick(u, joe);
			
			cc.verify("tako", "yaki");
			
			cc.accept(joe);
			assertNotNull(joe.getRole(u));
			
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
			csi.addChannel(u.getId(), joe, "chat");
			c = cs.getC().getSm().getServer("joe").getChannel("chat");
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
		assertEquals(c.getName(), joe.getChannel("chat").getName());
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
			cc.addChannel(joe, "joe");
			assertEquals(c.getName(), joe.getChannel("joe").getName());
			
			//delete channel for cc
			cc.setU(csi.findUserById(1));
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
			assertEquals(0, joe.getPins().size());
			
			csi.addPin(1, joe, m);
			assertEquals(1, joe.getPins().size());
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
			
			csi.unPin(joe, joe.getPins().get(0));
			assertEquals(0, joe.getPins().size());
			
			m = new Message();
			m.setMessage("This is a message");
			cc.addPin(joe, m);
			assertEquals(1, joe.getPins().size());
			
			cc.unPin(joe, joe.getPins().get(0));
			assertEquals(0, joe.getPins().size());
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
			csi.changeRole(u.getId(), u1, "mod", joe);
			Role mod = joe.getRole(u1);
			
			assertNotEquals(member, mod);
			assertNotEquals(admin, mod);
			
			cc.changeRole(u1, "member", joe);
			member = joe.getRole(u1);
			assertNotEquals(mod, member);
			assertNotEquals(admin, member);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testAddBlock() {
		User u = null;
		User u1 = null;
		User u2 = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.findUserById(2);
			u2 = csi.findUserById(3);
			
			int block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(0, block);
			
			csi.addBlock(u.getId(), u1);
			block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(1, block);
			
			cc.addBlock(u2);
			block = cc.getU().getBlocks().size();
			assertEquals(2, block);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}

	void testRemoveBlock() {
		User u = null;
		User u1 = null;
		User u2 = null;
		
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			u1 = csi.findUserById(2);
			u2 = csi.findUserById(3);
			
			csi.removeBlock(u.getId(), u);
			int block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(2, block);
			
			csi.removeBlock(u.getId(), u1);
			block = csi.getConcord().getUm().getUser(1).getBlocks().size();
			assertEquals(1, block);
			
			cc.removeBlock(u2);
			block = cc.getU().getBlocks().size();
			assertEquals(0, block);
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}
//done
	void testSetProfileData() {
		User u = null;
		try {
			ConcordServerInterface csi;
			csi = (ConcordServerInterface) registry.lookup("CONCORDS");
			u = csi.findUserById(1);
			assertEquals(null, u.getProfileData());
			
			csi.setProfileData(u.getId(), "bored");
			assertEquals("bored", u.getProfileData());
			
			cc.setProfileData("still bored");
			assertEquals("still bored", u.getProfileData());
			
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
			
			cc.setUsername("chan");
			assertEquals("chan", u.getUserName());
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
			
			cc.setProfilePic("fortknife.jpg");
			assertEquals("fortknife.jpg", csi.getConcord().getUm().getUser(1).getUrlPic());
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
			
			cc.sendDCMessage(m, dc);
			assertEquals(2, dc.getMessages().size());
			
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
			User u1 = csi.findUserById(2);
			
			joe = csi.getConcord().getSm().getServer("joe");
			for (Channel c1: joe.getChannels()) {
				System.out.println(c1.getName());
			}
			c = joe.getChannel("j");
			assertEquals(0, c.getMessages().size());
			
			Message m = new Message();
			m.setContent("hello");
			
			Level l = joe.findLevel(u);
			assertEquals(3, l.getLvl());

			csi.sendChannelMessage(m, 2, joe, c);
			assertEquals(1, c.getMessages().size());
			l = joe.findLevel(u1);
			assertEquals(3, l.getLvlProgress());
			
			cc.verify("chan", "takrak");
			cc.sendChannelMessage(m, joe, c);
			l = joe.findLevel(u);
			assertEquals(3, l.getLvl());
			assertEquals(1, l.getLvlProgress());
			
			l = joe.findLevel(u1);
			csi.sendChannelMessage(m, 2, joe, c);
			assertEquals(2, l.getLvl());
			assertEquals(0, l.getLvlProgress());
		} catch (Exception e) {
			e.printStackTrace();
			fail("threw exceptions");
		}
	}
	

}
