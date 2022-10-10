package by.piskunou.university.ds.controllers;

import java.io.IOException;

import by.piskunou.university.ds.models.Address;
import by.piskunou.university.ds.models.Person;
import by.piskunou.university.ds.services.ExceptionService;
import by.piskunou.university.ds.services.PeopleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
	@FXML private TextField firstnameField;
	@FXML private TextField surnameField;
	@FXML private TextField countryField;
	@FXML private TextField cityField;
	@FXML private TextField streetField;
	@FXML private TextField houseNumberField;
	@FXML private Button editButton;
	
	private Person editablePerson;
	
	private final PeopleService peopleService = new PeopleService();
	private final ExceptionService exceptionService = new ExceptionService();
	
	@FXML
	public void edit(ActionEvent event) throws IOException  {
		try {
			Address address = new Address();
			address.setCountry(countryField.getText());
			address.setCity(cityField.getText());
			address.setStreet(streetField.getText());
			address.setHouseNumber(Integer.parseInt(houseNumberField.getText()));
			editablePerson.setFirstname(firstnameField.getText());
			editablePerson.setSurname(surnameField.getText());
			editablePerson.setAddress(address);
			
			peopleService.update(editablePerson);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
			Parent root = loader.load();
			MainController controller = loader.getController();
			controller.updateCityListView();
			Stage stage = (Stage)editButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (NumberFormatException e) {
			exceptionService.numberFormatException();
		} catch (IllegalArgumentException | IOException e) {
			exceptionService.unexpectedException(e.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	void showPersonById(long id) throws IllegalArgumentException {
		editablePerson = peopleService.findById(id);
		Address address = editablePerson.getAddress();
		
		firstnameField.setText(editablePerson.getFirstname());
		surnameField.setText(editablePerson.getSurname());
		countryField.setText(address.getCountry());
		cityField.setText(address.getCity());
		streetField.setText(address.getStreet());
		houseNumberField.setText(Integer.toString(address.getHouseNumber()));
	}
}
