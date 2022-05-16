package views;

import java.rmi.RemoteException;

import concord.ConcordClient;
import concord.DirectConversation;
import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ConcordModel;

public class NewDcController {
	ConcordClient client;
	Stage stage;
	ConcordModel model;
	DirectConversation dc;
	
	// here want to use the dcDropList
	@FXML
	private TextField channelTxtField;
	
	public void setModel(Stage s, ConcordClient c, DirectConversation dc1) {
		stage = s;
		client = c;
		dc = dc1;
	}
	
	public void confirm() throws RemoteException {
		// action finds the user and creates a dc with them
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
