package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class MainController {
	@FXML
	private Label welcomeLabel;
	@FXML
	private TextField textField;
	@FXML
	private Button submitButton;

	@FXML
	public void submit(ActionEvent event) {
		try {
			int age = Integer.parseInt(textField.getText());
			String text = age >= 18 ? "Welcome young man!" : "Fuck out, bo-o-oy :)";
			welcomeLabel.setText(text);
		} catch (NumberFormatException e) {
			welcomeLabel.setText("Enter only integer numbers please");
		}
	}
}
