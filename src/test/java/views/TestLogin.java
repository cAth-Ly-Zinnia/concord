package views;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;

import concord.Channel;
import concord.ConcordClient;
import concord.ConcordServer;
import concord.DirectConversation;
import concord.DirectConversationManager;
import concord.Message;
import concord.Role;
import concord.Server;
import concord.ServerManager;
import concord.User;
import concord.UserManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.ConcordModel;
import models.ViewTransitionModel;
import views.DCController;
import views.MainController;

@ExtendWith(ApplicationExtension.class)
public class TestLogin
{
	ConcordServer cs;
	Registry registry;
	ConcordClient cc;
	User user_1, user_2, user_3;
	ConcordModel model;
	
	@Start	//Before
	private void start(Stage stage) throws RemoteException
	{
		cs = new ConcordServer();
		registry = LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORDS", cs);
		cc = new ConcordClient();
		model = new ConcordModel();
		
		UserManager UM = cs.getConcord().getUm();
		UM.createUser("a", "abc", "123");
		user_1 = UM.getUser(1);
		UM.createUser("b", "def", "456");
		user_2 = UM.getUser(2);
		UM.createUser("c", "ghi", "aaa");
		user_3 = UM.getUser(3);
		
		DirectConversationManager DM = cs.getConcord().getDcm();
		ArrayList<User> a = new ArrayList<User>();
		a.add(user_1); a.add(user_2);
		DirectConversation dc = DM.createDC(a);
		
		ArrayList<User> b = new ArrayList<User>();
		b.add(user_1); b.add(user_3);
		DirectConversation dc1 = DM.createDC(b);
		
		Message m = new Message();
		m.setContent("hello");
		m.setUser(user_1);
		dc.sendMessage(m);
		
		/*
		Message m1 = new Message();
		m.setContent("hi");
		m.setUser(user_3);
		dc1.sendMessage(m1);
		*/
		ServerManager SM = cs.getConcord().getSm();
		try {
			SM.createServer("Test1", user_1);
			SM.createServer("Test2", user_1);
			SM.createServer("Test3", user_1);
			SM.createServer("Test4", user_2);
			SM.createServer("Test5", user_2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Role admin = SM.getServer("Test1").getRole(user_1);
		Channel gen = new Channel();
		gen.setName("general");
		SM.getServer("Test1").addChannel(admin, gen);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class.getResource("../views/MainView.fxml"));
		try
		{
			BorderPane view = loader.load();
			MainController cont = loader.getController();
			ViewTransitionModel vm = new ViewTransitionModel(view, cc, model);
			cont.setModel(vm);
			vm.showLogin();
			
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	void tearDown() throws Exception
	{
		registry.unbind("CONCORDS");
	}
	
	@Test
	public void testLogin(FxRobot robot) throws RemoteException
	{
		robot.clickOn("#userNameTextField");
		robot.write("a");
		
		robot.clickOn("#passwordTextField");
		robot.write("123");
		
		robot.clickOn("#loginSubmitButton");
		robot.clickOn(robot.lookup("#dcList").nth(0).queryAs(Node.class));
		try
		{
			Thread.sleep(100000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		testServer(user_1, 3);
		
		testDc(user_1);
		testMessage(user_1);
		
		Assertions.assertThat(robot.lookup("#usernameLabel")
				.queryAs(Label.class)).hasText(user_1.getUserName());
		
		robot.clickOn(robot.lookup("#svListView").nth(0).queryAs(Node.class));
		
		robot.clickOn("#homeButton");

		/*
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}*/
		
		robot.clickOn("#btnSettings");
		robot.clickOn("#btnUserInfo");
		/*
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		*/
		robot.clickOn("#btnBlockList");
		/*
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		*/
		robot.clickOn("#btnBack");
		
		robot.clickOn("#buttonLogout");
		
		
		robot.clickOn("#userNameTextField");
		robot.write("b");
		
		robot.clickOn("#passwordTextField");
		robot.write("456");
		
		robot.clickOn("#loginSubmitButton");

		testServer(user_2, 2);
		
		testDc(user_2);
		
		testMessage(user_2);
		
		Assertions.assertThat(robot.lookup("#usernameLabel")
				.queryAs(Label.class)).hasText(user_2.getUserName());
		//testUserName(user_2);
		
		robot.clickOn("#btnSettings");
		robot.clickOn("#btnUserInfo");
		robot.clickOn("#btnBlockList");
		robot.clickOn("#btnBack");
		
		
		robot.clickOn("#buttonLogout");
	}
	
	void testServer(User user, int amt) throws RemoteException
	{
		assertEquals(amt, model.getServers().size());
		for (Server s: cs.getServerByUserId(user.getId()))
		{
			//System.out.print(s.getServerName() + " ");
			boolean exist = false;
			for (Server iconS: model.getServers())
			{
				//System.out.println(l.getText());
				if (iconS.getName().equals(s.getName())) exist = true;
			}
			assertTrue(exist);
		}
	}
	
	void testDc(User user)
	{
		for (DirectConversation d: cs.getConcord().getDcm().getDcListByUserId(user.getId()))
		{
			boolean exist = false;
			for (DirectConversation curD: model.getDcs())
				//TODO fix this name
				if (curD.getName().equals(d.getName())) exist = true;
			assertTrue(exist);
		}
	}
	
	void testMessage(User user)
	{
		for (DirectConversation d: cs.getConcord().getDcm().getDcListByUserId(user.getId()))
		{
			for (Message m: d.getMessages())
			{
				boolean exist = false;
				for (Message curM: model.getDcsMessages())
					if (curM.getContent().equals(m.getContent())) exist = true;
				assertTrue(exist);
			}
		}
	}
	/*
	void testUserName(User user)
	{
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
			  .getResource("../views/DcAlterView.fxml"));
		try
		{
			BorderPane view = loader.load();
			DCController cont = loader.getController();	
			cont.setModel(model, cc);
			assertEquals(user.getUserName(), cont.getUserNameLabel().getText());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}