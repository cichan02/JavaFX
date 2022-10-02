package by.piskunou.university.ds.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.google.common.io.Files;

import by.piskunou.university.ds.util.Translator;
import javafx.scene.Node;
import javafx.stage.FileChooser;

public class MainService {
	private static final String HOME_DIR = "/home/cichan/Documents/University/Discipline of Specialization (DS)/Practice/project1/src/main/resources/";
	
	private final Translator translator = new Translator();

	private FileChooser fileChooserBuilder(String title, String initDirectory, String extention) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(initDirectory));
		fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Support only " + extention + " file", "*" + extention));
		return fileChooser;
	}
	
	private void fileCheck(File file, String extention) throws NullPointerException, IllegalArgumentException {
		if(file == null) throw new NullPointerException();
		if(!Files.getFileExtension(file.getName()).equals(extention)) throw new IllegalArgumentException();
	}
	
	public void setProperties(Node node) throws FileNotFoundException, IOException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose properties", HOME_DIR, ".properties");
		File propFile = fileChooser.showOpenDialog(node.getScene().getWindow());
			
		fileCheck(propFile, "properties");
		
		Properties properties = new Properties();
		try (FileInputStream fileInputStream = new FileInputStream(propFile)) {
			properties.load(fileInputStream);
		}
		
		translator.setProperties(properties);
	}

	public void save(Node node, String text) throws FileNotFoundException, IOException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose where to save", HOME_DIR, ".txt");
		File file = fileChooser.showSaveDialog(node.getScene().getWindow());
	
		fileCheck(file, "txt");
		
		try (FileWriter fileWriter = new FileWriter(file)) {
			fileWriter.write(text);
		}
	}

	public String open(Node node) throws FileNotFoundException, IOException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose start file", HOME_DIR, ".txt");
		File file = fileChooser.showOpenDialog(node.getScene().getWindow());
		
		fileCheck(file, "txt");
		
		char[] charArray;
		try (FileReader fileReader = new FileReader(file)){
			charArray = new char[(int) file.length()];
			fileReader.read(charArray);
		}
		
		return new String(charArray);
	}

	public String translate(String latin) {
		return translator.translate(latin);
	}
}
