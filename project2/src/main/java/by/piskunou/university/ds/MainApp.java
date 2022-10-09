package by.piskunou.university.ds;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {	
	public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
    	Parent root = loader.load();
    	Scene scene = new Scene(root);

        stage.setTitle("Project 2: Demo");
        stage.setScene(scene);
        stage.show();
    }
}
