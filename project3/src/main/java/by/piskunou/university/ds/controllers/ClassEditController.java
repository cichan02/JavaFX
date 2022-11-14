package by.piskunou.university.ds.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import by.piskunou.university.ds.services.ExceptionService;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ClassEditController {
	@FXML private Label className;
	@FXML private ListView<String> fieldList;
	@FXML private Button changeButton;
	@FXML private Button backButton;
	@FXML TextField textArgs;
	private Object godObject;
	private String fieldName;
	
	private final ExceptionService exceptionService = new ExceptionService();

	@FXML
	public void changeValue() {
		try {  
			Field field = godObject.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			String arg = textArgs.getText();
			String fieldType = field.getType().getSimpleName();
			Object value = switch (fieldType) {
				case "String" -> arg;
				case "long" -> Long.parseLong(arg);
				case "int" -> Integer.parseInt(arg);
				case "Address" -> {
					Constructor<?> constructor = field.getType().getConstructor(String.class, String.class, String.class, int.class);
					String[] args = arg.split("\\s");
					yield constructor.newInstance(args[0], args[1], args[2], Integer.parseInt(args[3]));
				}
				default -> throw new IllegalArgumentException("Unexpected value: " + fieldType);
			};
			field.set(godObject, value);
			updateFieldList();
		} catch (NumberFormatException e) {
			exceptionService.numberFormatException();
		} catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException |
				 NoSuchMethodException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goBack() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
	    	Parent root = loader.load();
	    	Stage stage = (Stage)backButton.getScene().getWindow();
	    	Scene scene = new Scene(root);

	        stage.setScene(scene);
	        stage.centerOnScreen();
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void updateFieldName() {
		String currentString = fieldList.getSelectionModel().getSelectedItem();
		String[] strings = currentString.split(" ");
		fieldName = strings[strings.length - 3];
		
		changeButton.setDisable(false);
		changeButton.setText("Change " + fieldName + " value");
	}

	public void init(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
											  InstantiationException, IllegalAccessException, IllegalArgumentException,
											  InvocationTargetException {
		this.className.setText(className);
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor();
		godObject = constructor.newInstance();
		updateFieldList();
	}

	private void updateFieldList() throws IllegalArgumentException, IllegalAccessException {
		fieldList.getItems().clear();
		Field[] fields = godObject.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			
			field.setAccessible(true);
			String string = (((mod == 0) ? "" : (Modifier.toString(mod) + " "))
		            + field.getType().getSimpleName() + " "
		            + field.getName() + " = "
		            + field.get(godObject));
			
			fieldList.getItems().add(string);
		}
	}
}
