package views;

import java.rmi.RemoteException;

import concord.ConcordClient;
import concord.DirectConversation;
import concord.Server;
import concord.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ConcordModel;

public class NewDcController {
	ConcordClient client;
	Stage stage;
	ConcordModel model;
	DirectConversation dc;
	User userTarget;
	
	
	@FXML
    private MenuButton userMenu;
	
	public void setModel(Stage s, ConcordClient c) {
		stage = s;
		client = c;
	
		MenuItem item = new MenuItem("Find User");
		
		userMenu.getItems().clear();
		
		try {
			for(User u : client.getUsers()) {
				item = new MenuItem(u.getUserName());
				item.setId(u.getUserName());
				item.setOnAction(new EventHandler<ActionEvent>() {
				    @Override 
				    public void handle(ActionEvent e) 
				    {
				    	userTarget = u;
				    	userMenu.setText(userTarget.getUserName());
				    }
				});
				userMenu.getItems().add(item);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirm() throws RemoteException {
		client.addDc(userTarget);
    	stage.close();
	}
    @FXML
    void onClickCancel(ActionEvent event) throws RemoteException {
    	stage.close();
    }

    @FXML
    void onClickConfirm(ActionEvent event) throws RemoteException {
    	confirm();
    }

    @FXML
    void onEnterConfirm(ActionEvent event) throws RemoteException {
    	confirm();
    }

}
