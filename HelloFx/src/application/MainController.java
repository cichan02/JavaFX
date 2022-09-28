package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
	@FXML
	private ImageView imageView;

	private Image image;
	
	public void displayImage2() {
		image = new Image(getClass().getResourceAsStream("/2.jpg"));
		imageView.setImage(image);
	}
	
	public void displayImage1() {
		image = new Image(getClass().getResourceAsStream("/1.jpg"));
		imageView.setImage(image);
	}
}
