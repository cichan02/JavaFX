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

public class CreateController {
	@FXML private TextField firstnameField;
	@FXML private TextField surnameField;
	@FXML private TextField countryField;
	@FXML private TextField cityField;
	@FXML private TextField streetField;
	@FXML private TextField houseNumberField;
	@FXML private Button createButton;
	
	private final PeopleService peopleService = new PeopleService();
	private final ExceptionService exceptionService = new ExceptionService();

	@FXML
	public void create(ActionEvent event) {
		try {
			Address address = new Address();
			address.setCountry(countryField.getText());
			address.setCity(cityField.getText());
			address.setStreet(streetField.getText());
			address.setHouseNumber(Integer.parseInt(houseNumberField.getText()));
			Person newPerson = Person.of(firstnameField.getText(), surnameField.getText(), address);
	
			peopleService.save(newPerson);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
			Parent root = loader.load();
			MainController controller = loader.getController();
			controller.updateCityListView();
			Stage stage = (Stage)createButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (NumberFormatException e) {
			exceptionService.numberFormatException();
		} catch (IOException e) {
			exceptionService.unexpectedException(e.getClass().getSimpleName(), e.getMessage());
		}
	}
}
