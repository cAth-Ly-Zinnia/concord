package views;

import java.rmi.RemoteException;

import concord.Channel;
import concord.ConcordClient;
import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ConcordModel;

public class NewChannelController {

	ConcordClient client;
	Stage stage;
	ConcordModel model;
	Server server;
	
	@FXML
	private TextField channelTxtField;
	
	public void setModel(Stage s, ConcordClient c, Server s1) {
		stage = s;
		client = c;
		server = s1;
	}
	
	public void confirm() throws RemoteException {
		String channelName = channelTxtField.getText();
    	if (!channelName.equals(""))
    	{
    		// need to get this // -> serverListView.getSelectionModel().getSelectedItem();
    		client.addChannel(server, channelName);
    	}
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
