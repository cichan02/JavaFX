package by.piskunou.university.ds.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class MainController implements Initializable {
	@FXML private ListView<String> listView;
	@FXML private ChoiceBox<String> choiceBox;
	@FXML private Button createButton;
	
	private String[] names = new String[] {"by.piskunou.university.ds.models.Address",
										   "by.piskunou.university.ds.models.Person",
										   "by.piskunou.university.ds.models.Student"};

	@FXML
	public void create() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/classEdit.fxml"));
			Parent root = loader.load();
			ClassEditController classEditController = loader.getController();
			classEditController.init(choiceBox.getValue());
			Stage stage = (Stage)createButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException |
				 InstantiationException | IllegalAccessException | IllegalArgumentException |
				 InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void updateListView() {
		try {
			listView.getItems().clear();
			createButton.setDisable(false);
			
			Class<?> clazz = Class.forName(choiceBox.getValue());
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				String string = (((mod == 0) ? "" : (Modifier.toString(mod) + " "))
			            + field.getType().getSimpleName() + " "
			            + field.getName());
				
				listView.getItems().add(string);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().addAll(names);
	}
}
