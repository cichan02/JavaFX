package by.piskunou.university.ds.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.IOException;

import by.piskunou.university.ds.service.ExceptionService;
import by.piskunou.university.ds.service.MainService;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class MainController {
	@FXML private Button setButton;
	@FXML private Button saveButton;
	@FXML private Button openButton;
	@FXML private TextArea latinText;
	@FXML private TextArea cyrillicText;
	@FXML private Button translateButton;
	@FXML private Text successText;
	
	private final MainService mainService = new MainService();
	private final ExceptionService exceptionService = new ExceptionService();

	@FXML
	public void setProperties(ActionEvent event) {
		try {
			mainService.setProperties(setButton);
			
			successText.setText("Ok :)");
			cyrillicText.setText("");
			openButton.setDisable(false);
			latinText.setDisable(false);
			translateButton.setDisable(false);
			saveButton.setDisable(true);	
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("properties");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.ioException(e.getMessage());
		}
	}
	
	@FXML
	public void saveFile(ActionEvent event) {
		try {
			mainService.save(saveButton, cyrillicText.getText());
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("txt");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.ioException(e.getMessage());
		}
	}
	
	@FXML
	public void openFile(ActionEvent event) {
		try {
			String text = mainService.open(openButton);
			
			latinText.setText(text);
			saveButton.setDisable(true);
			cyrillicText.setText("");
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("txt");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.ioException(e.getMessage());
		}
	}
	
	@FXML
	public void translate(ActionEvent event) {
		String latin = latinText.getText();
		String cyrillicResult = mainService.translate(latin);
		
		cyrillicText.setText(cyrillicResult);
		saveButton.setDisable(false);
	}
}
