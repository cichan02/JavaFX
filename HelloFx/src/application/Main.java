package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
		
		Image icon = new Image("https://www.shareicon.net/data/2015/09/20/643803_technology_512x512.png");	
		stage.getIcons().add(icon);
		
		stage.setScene(scene);
		stage.show();
	}
}
