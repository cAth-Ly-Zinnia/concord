package concord;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.*;
import javafx.event.EventHandler;
import models.ConcordModel;
import models.ViewTransitionModel;
import views.MainController;

public class Main extends Application{
	Registry registry;
	ConcordModel model;
	ConcordClient client;
	ConcordServer cs;
	Stage stage;
	
	public void start(Stage stage) throws Exception{
		this.stage = stage;
		cs = new ConcordServer();
		registry = LocateRegistry.createRegistry(2099);
		registry.rebind("CONCORDS", cs);
		
		model = new ConcordModel();
		FXMLLoader loader = new FXMLLoader();
		client = new ConcordClient(model);
		
		cs.addObserver(client);
		
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view = loader.load();
		ViewTransitionModel vm = new ViewTransitionModel(view, client, model);
		
		MainController cont = loader.getController();
		cont.setModel(vm);
		vm.showLogin();
		
		Scene s = new Scene(view);
		s.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		stage.setScene(s);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent t) 
            {
				stop();
                Platform.exit();
                System.exit(0);
            }
		});
		
		
	}
	
	@Override
	public void stop(){
		System.out.println("attempting to save");
	    cs.getConcord().serializeToXML();
	    try
        {
            registry.unbind("CONCORDS");
        } catch (RemoteException | NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stage.close();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
