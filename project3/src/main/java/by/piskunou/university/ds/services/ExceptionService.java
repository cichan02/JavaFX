package by.piskunou.university.ds.services;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionService {
	public void numberFormatException() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Number format exception");
		alert.setHeaderText("Argument should be a number");
		alert.showAndWait();
	}
}
