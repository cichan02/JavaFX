package by.piskunou.university.ds.services;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionService {
	public void nullFile() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Null file");
		alert.setHeaderText("Please, choose not null file");
		alert.showAndWait();
	}
	
	public void nullSelected() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Null selected person");
		alert.setHeaderText("Please, choose a person");
		alert.showAndWait();
	}
	
	public void wrongFileExtention(String extention) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Wrong file extention");
		alert.setHeaderText("Please, choose file with ." + extention + " extention");
		alert.showAndWait();
	}
	
	public void numberFormatException() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Number format exception");
		alert.setHeaderText("Number of the house must be integer!");
		alert.showAndWait();
	}
	
	public void fileNotFound() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("File not found");
		alert.setHeaderText("Please, try again");
		alert.showAndWait();
	}
	
	public void unexpectedException(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
