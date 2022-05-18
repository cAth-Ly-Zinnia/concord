package views;

import concord.ConcordClient;
import concord.Message;
import concord.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.ConcordModel;
import models.ViewTransitionModelInterface;

public class PinController {
	Stage stage;
	ConcordModel concordModel;
	ConcordClient client;
	Server server;
	
	@FXML
    private ListView<Message> pinListView;
	
	public void setModel(Stage s, ConcordClient c, ConcordModel model, Server s1) {
		stage = s;
		client = c;
		concordModel = model;
		server = s1;
		System.out.println(server.getPins());
		pinListView.setItems(concordModel.getPinMessages());
	}

    @FXML
    void returnToChannel(ActionEvent event) {
    	stage.close();
    }
}
