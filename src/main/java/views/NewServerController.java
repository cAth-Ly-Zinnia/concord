package views;

import java.rmi.RemoteException;

import concord.ConcordClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewServerController {

	ConcordClient client;
	Stage stage;
	
	@FXML
	private TextField serverTxtField;
	
	public void setModel(Stage s, ConcordClient c) {
		stage = s;
		client = c;
	}
	
	public void confirm() throws RemoteException {
		String serverName = serverTxtField.getText();
    	if (serverName.equals(""))
    	{
    		client.addServer(serverName);
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
