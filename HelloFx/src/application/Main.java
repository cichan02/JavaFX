package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Scene scene = new Scene(root);
		String css = this.getClass().getResource("/application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		stage.setOnCloseRequest(event -> {
			event.consume();
			logout(event, stage);
		});
		stage.setScene(scene);
		stage.show();
	}
	
	public void logout(WindowEvent event, Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout");
		alert.setContentText("Do you want to save before exiting?: ");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("You logout");
			stage.close();
		}
	}
}
