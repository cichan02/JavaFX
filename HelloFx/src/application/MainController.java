package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javafx.scene.control.Label;

public class MainController implements Initializable{
	@FXML
	private ListView<String> listView;
	@FXML
	private Label label;
	
	private String[] food = {"pizzaj;adfskjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj", "sushi", "ramen", "a", "b", "c", "i", "c", "h", "a", "n", "p", "i", "s", "k",
							 "dadfaksjkas;kdfasdfj jfsdvasdnfajdsasdjf;kaljdf;klasjdf;kdf;lkasdjf;aks", "e", "f"};
	private String currentFood;
	
	private ChangeListener<String> changeListener = (obserVal, str1, str2) -> {
		currentFood = listView.getSelectionModel().getSelectedItem();
		label.setText(currentFood);
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listView.getItems().addAll(food);
		listView.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}
}
