package by.piskunou.university.ds.services;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionService {
	private void frame(AlertType alertType, String title, String header) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.showAndWait();
	}
	
	private void warningFrame(String title, String header) {
		frame(AlertType.WARNING, title, header);
	}
	
	private void errorFrame(String title, String header) {
		frame(AlertType.ERROR, title, header);
	}
	
	public void nullFile() {
		warningFrame("Null file", "Please, choose not null file");
	}
	
	public void wrongFileExtention(String extention) {
		warningFrame("Wrong file extention", "Please, choose file with ." + extention + " extention");
	}
	
	public void fileNotFound() {
		errorFrame("File not found", "Please, try again");
	}
	
	public void unexpectedException(String title, String message) {
		errorFrame(title, message);
	}
}
