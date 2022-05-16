package views;

import java.rmi.RemoteException;

import concord.ConcordClient;
import concord.DirectConversation;
import concord.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ConcordModel;
import models.ViewTransitionModel;

public class DCController
{
	ConcordModel concordModel;
	ConcordClient client;
	ViewTransitionModel model;
	Stage stage;
	
    @FXML
    //private ListView<DirectConversation> dcList;
    private ListView<DirectConversation> dcListView;

    @FXML
    private ListView<Message> dcMessageListView;

    @FXML
    private TextField dcMessageTextField;
    
    @FXML
    private Label userNameTextField;
	
	public void setModel(ViewTransitionModel model, ConcordModel m, ConcordClient c)
	{
		this.model = model;
		concordModel = m;
		client = c;
		
		dcListView.setItems(concordModel.getDcs());
		dcMessageListView.setItems(concordModel.getDcsMessages());
		userNameTextField.setText(client.getU().getUserName());
	}
	
	public Label getUserNameLabel()
	{
		return userNameTextField;
	}
	
	//TODO when i have time
	/*
	@FXML
	void addDC(ActionEvent event) {
		stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(ViewTransitionModel.class
    			.getResource("../views/NewDCView.fxml"));
    	BorderPane view = loader.load();
		NewDcController cont = loader.getController();
		
		cont.setModel(stage, client);
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}*/
	
    @FXML
    void onClickSettings(ActionEvent event) 
    {
    	model.showUser();
    }
    
    @FXML
    void onDcListViewClicked(MouseEvent event) 
    {
    	
    }
    
    @FXML
    void onEnterPressed(ActionEvent event) {
    	Message message = new Message();
    	String content = dcMessageTextField.getText();
    	message.setContent(content);
    	message.setUser(client.getU());
    	// need to find the curDc client.getDcById();
  
    	DirectConversation dc = dcListView.getSelectionModel().getSelectedItem();
    	try {
			client.sendDCMessage(message, dc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

}
