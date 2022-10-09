package by.piskunou.university.ds.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import by.piskunou.university.ds.models.Person;
import by.piskunou.university.ds.services.ExceptionService;
import by.piskunou.university.ds.services.MainService;
import by.piskunou.university.ds.services.PeopleService;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;

public class MainController implements Initializable {
	@FXML private ListView<Person> listView;
	@FXML private Button openButton;
	@FXML private Button saveButton;
	@FXML private Button newButton;
	@FXML private Button deleteButton;
	@FXML private Button editButton;
	
	private final MainService mainService = new MainService();
	private final ExceptionService exceptionService = new ExceptionService();
	private final PeopleService peopleService = new PeopleService();
	
	private Person currentPerson;
	
	private void updateListView() {
		listView.getItems().clear();
		listView.getItems().addAll(peopleService.findAll());
	}
	
	@FXML
	public void openFile (ActionEvent event) {
		try {
			List<Person> people = mainService.open(openButton);
			
			updateListView();
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("txt");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.unexpectedException("IOException", e.getMessage());
		}
	}
	
	@FXML
	public void saveFile (ActionEvent event) {
		try {
			mainService.save(saveButton, listView.getItems());
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("txt");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (IOException e) {
			exceptionService.unexpectedException("IOException", e.getMessage());
		}
	}
	
	@FXML
	public void createPerson (ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/new.fxml"));
	}
	
	@FXML
	public void editPerson(ActionEvent event) {
		
	}
	
	@FXML
	public void deletePerson (ActionEvent event) {
		listView.getItems().remove(currentPerson);
		peopleService.deleteById(currentPerson.getId());
	}
	
	ChangeListener<Person> changeListener = ((observable, oldValue, newValue) -> {
		currentPerson = listView.getSelectionModel().getSelectedItem();
		
		deleteButton.setText("Delete " + currentPerson);
		editButton.setText("Edit " + currentPerson);
	});

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateListView();
		listView.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}
}
