package application;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.ChoiceBox;

public class MainController implements Initializable {
	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private Label label;
	private String[] food = {"pizza", "sushi", "ramen"};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBox.getItems().addAll(food);
		choiceBox.setOnAction(this::getFood);
	}
	
	public void getFood(ActionEvent event) {
		String currentFood = choiceBox.getValue();
		label.setText(currentFood);
	}
}
