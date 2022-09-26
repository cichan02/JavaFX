package application.controllers;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javafx.scene.shape.Circle;

public class MainController {
	@FXML
	private Circle circle;
	private double x;
	private double y;

	// Event Listener on Button[#up].onAction
	@FXML
	public void up(ActionEvent event) {
		circle.setCenterY(y-=5);
	}
	// Event Listener on Button[#left].onAction
	@FXML
	public void left(ActionEvent event) {
		circle.setCenterX(x-=5);
	}
	// Event Listener on Button[#down].onAction
	@FXML
	public void down(ActionEvent event) {
		circle.setCenterY(y+=5);
	}
	// Event Listener on Button[#right].onAction
	@FXML
	public void right(ActionEvent event) {
		circle.setCenterX(x+=5);
	}
}
