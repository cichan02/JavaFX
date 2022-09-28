package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private AnchorPane scenePane;
	@FXML
	private Button logoutButton;

	@FXML
	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout");
		alert.setContentText("Do you want to save before exiting?: ");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			Stage stage =(Stage)scenePane.getScene().getWindow();
			System.out.println("You logout");
			stage.close();
		}
	}
}
