package application.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainSceneController {
	@FXML
	private TextField textField;

	// Event Listener on Button.onAction
	@FXML
	public void btnOkClicked(ActionEvent event) {
		Stage mainStage = (Stage)textField.getScene().getWindow();
		String title = textField.getText();
		mainStage.setTitle(title);
	}
}
