package by.piskunou.university.ds.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import by.piskunou.university.ds.services.ExceptionService;
import by.piskunou.university.ds.services.MainService;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextArea;

public class MainController {
	@FXML private TextArea rawText;
	@FXML private Button transateButton;
	@FXML private Button openButton;
	@FXML private TextArea translatedText;
	
	private final MainService mainService = new MainService();
	private final ExceptionService exceptionService = new ExceptionService();

	@FXML
	public void translate() {
		
	}
	
	@FXML
	public void open() {
		try {
			String text = mainService.open(openButton);
			
			rawText.setText(text);
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("java");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.unexpectedException("IOException", e.getMessage());
		}
	}
}
