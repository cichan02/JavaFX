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
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    	Scene scene = new Scene(root);

        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
}
