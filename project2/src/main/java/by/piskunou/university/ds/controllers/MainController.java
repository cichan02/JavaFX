package by.piskunou.university.ds.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
	
	private ChangeListener<Person> changeListener = ((observable, oldValue, newValue) -> {
		currentPerson = listView.getSelectionModel().getSelectedItem();
		
		deleteButton.setText("Delete " + currentPerson);
		editButton.setText("Edit " + currentPerson);
	});
	
	private void updateListView() {
		listView.getItems().clear();
		listView.getItems().addAll(peopleService.findAll());
	}
	
	@FXML
	public void openFile (ActionEvent event) {
		try {
			Collection<? extends Person> people = mainService.open(openButton);
			peopleService.setAll(people);
			
			updateListView();
		} catch (NullPointerException e) {
			exceptionService.nullFile();
		} catch (IllegalArgumentException e){
			exceptionService.wrongFileExtention("txt");
		} catch (FileNotFoundException e) {
			exceptionService.fileNotFound();
		} catch (ClassNotFoundException | IOException e) {
			exceptionService.unexpectedException("IOException", e.getMessage());
		}
	}
	
	@FXML
	public void saveFile (ActionEvent event) {
		try {
			mainService.save(saveButton, peopleService.findAll());
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
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/new.fxml"));
			Stage stage = (Stage)newButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			exceptionService.unexpectedException(e.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	@FXML
	public void editPerson(ActionEvent event) {
		try {
			long id = currentPerson.getId();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit.fxml"));
			Parent root = loader.load();
			EditController editController = loader.getController();
			editController.showPersonById(id);
			Stage stage = (Stage)editButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (NullPointerException e) {
			exceptionService.nullSelected();
		} catch (IllegalArgumentException | IOException e) {
			exceptionService.unexpectedException(e.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	@FXML
	public void deletePerson (ActionEvent event) {
		peopleService.deleteById(currentPerson.getId());
		listView.getItems().remove(currentPerson);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateListView();
		listView.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}
}
