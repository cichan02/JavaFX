package by.piskunou.university.ds.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.common.io.Files;

import by.piskunou.university.ds.models.Person;
import javafx.scene.Node;
import javafx.stage.FileChooser;

public class MainService {
	private static final String HOME_DIR = "/home/cichan/Documents/University/Discipline of Specialization (DS)/Practice/project2/src/main/resources/";

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
	
	
	public List<Person> open(Node node) throws FileNotFoundException, IOException,
											   NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose file", HOME_DIR, ".txt");
		File file = fileChooser.showOpenDialog(node.getScene().getWindow());
		
		fileCheck(file, "txt");
		
		//past your code		
		
		return Collections.emptyList();
	}

	public void save(Node node, List<Person> people) throws FileNotFoundException, IOException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose where to save", HOME_DIR, ".txt");
		File file = fileChooser.showSaveDialog(node.getScene().getWindow());
	
		fileCheck(file, "txt");
		
		//past your code
	}
}
