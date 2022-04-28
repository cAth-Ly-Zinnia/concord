package views;

import concord.ConcordClient;
import concord.DirectConversation;
import concord.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.ConcordModel;
import models.ViewTransitionModel;

public class DCController
{
	ConcordModel concordModel;
	ConcordClient client;
	ViewTransitionModel model;
	
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
		dcMessageListView.setItems(concordModel.getMessages());
		userNameTextField.setText(client.getU().getUserName());
		//dcList.setCellFactory(null);
	}
	
	public Label getUserNameLabel()
	{
		return userNameTextField;
	}
	
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
    	client.sendDCMessage(message, dc);
    	if (dc != null) {
    		concordModel.getMessages().add(message);
    	}
    	dcMessageListView.setItems(concordModel.getMessages());
    	dcMessageTextField.setText("");
    }

}
