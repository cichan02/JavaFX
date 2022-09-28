package application;

import javax.xml.transform.Templates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MainController {
	@FXML
	private CheckBox checkBox;	
	@FXML
	private ImageView imageView;
	@FXML
	private Text text;
	
	public void change(ActionEvent event) {
		String status = checkBox.isSelected() ? "on" : "off";
		
		Image image = new Image("/" + status + ".jpg");
		imageView.setImage(image);
		
		text.setText(status.toUpperCase());
	}
}
