package by.piskunou.university.ds;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
    	ResourceBundle resources = ResourceBundle.getBundle("words", Locale.of("be", "BY"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"), resources);
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	
        stage.setScene(scene);
        stage.show();
    }
}
