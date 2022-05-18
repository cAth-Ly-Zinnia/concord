package views;

import java.io.IOException;
import java.rmi.RemoteException;

import concord.Channel;
import concord.ConcordClient;
import concord.Message;
import concord.Server;
import concord.User;
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

public class ServerController
{
	ConcordClient client;
	ConcordModel concordModel;
	ViewTransitionModel model;
	Stage stage;
	Server server;
	Channel c;
	
	@FXML
    private ListView<Channel> channelListView;

    @FXML
    private Label channelNameLabel;

    @FXML
    private ListView<Message> messageListView;

    @FXML
    private TextField messageTextField;

    @FXML
    private Label userNameLabel;

    @FXML
    private ListView<User> usersListView;
	
	public void setModel(ViewTransitionModel vModel, ConcordModel m, ConcordClient c, Server s)
	{
		model = vModel;
		client = c;
		concordModel = m;
		userNameLabel.setText(client.getU().getUserName());
		channelListView.setItems(concordModel.getChannels());
		channelListView.getSelectionModel().selectedItemProperty()
		.addListener((e)->{onSelectedItem();});
		messageListView.setItems(concordModel.getSerMessages());
		usersListView.setItems(concordModel.getUsers());
		server = s;
	}

	private void onSelectedItem() {
		c = channelListView.getSelectionModel().getSelectedItem();
    	if(c != null) {
	    	System.out.println(c.getName());
	    	channelNameLabel.setText(c.getName());
	    	try {
	    		concordModel.getSerMessages().clear();
	    		concordModel.setSerMessages(c.getMessages());
	    		messageListView.setItems(concordModel.getSerMessages());
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
		
	}

	@FXML
    void addChannelClicked(ActionEvent event) throws Exception {
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(ViewTransitionModel.class
    			.getResource("../views/NewChannelView.fxml"));
    	BorderPane view = loader.load();
		NewChannelController cont = loader.getController();
		
		cont.setModel(stage, client, server);
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
    }
	
    @FXML
    void btnPinsClicked(ActionEvent event) throws Exception 
    {
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(ViewTransitionModel.class
    			.getResource("../views/pinView.fxml"));
    	BorderPane view = loader.load();
		PinController cont = loader.getController();
		
		cont.setModel(stage, client, concordModel, server);
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
    }
    
    @FXML
    void inviteClicked(ActionEvent event) throws Exception {
    	stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(ViewTransitionModel.class
    			.getResource("../views/NewInviteView.fxml"));
    	BorderPane view = loader.load();
		InviteController cont = loader.getController();
		
		cont.setModel(stage, client, server);
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
    }
    
    @FXML
    void btnSettingsClicked(ActionEvent event) 
    {
    	model.showUser();
    }

    @FXML
    void channelListViewClicked(MouseEvent event) 
    {
    	/*
    	concordModel.getSerMessages().clear();
    	c = channelListView.getSelectionModel().getSelectedItem();
    	if (c != null) {
    		concordModel.setSerMessages(c.getMessages());
    		messageListView.setItems(concordModel.getSerMessages());
    	}
    	*/
    }
    
    @FXML
    void highlightMsg(ActionEvent event) {

    }

    @FXML
    void pinMsg(ActionEvent event) {
    	String content = messageTextField.getText();
    	Message msg = new Message();
    	msg.setContent(content);
    	if(content != "") {
    		System.out.println("sends: " + content);
    		try {
    			client.addPin(server, msg);
    			client.sendChannelMessage(msg, server, c);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	messageTextField.setText("");
    }
    
    @FXML
    void boldMsg(ActionEvent event) {

    }
    
    @FXML
    void sendMessage(ActionEvent event) {
    	String content = messageTextField.getText();
    	Message msg = new Message();
    	msg.setContent(content);
    	if(content != "") {
    		System.out.println("sends: " + content);
    		try {
				client.sendChannelMessage(msg, server, c);
				//model.showContent();
				
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	messageTextField.setText("");
    }
    
    @FXML
    void sendMsg(ActionEvent event) {
    	sendMessage(event);
    }
}
