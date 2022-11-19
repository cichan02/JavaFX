package by.piskunou.university.ds.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Files;

import javafx.scene.Node;
import javafx.stage.FileChooser;

public class MainService {
	private static final String HOME_DIR = "/home/cichan/Documents/University/Discipline of Specialization (DS)/Practice/project4/src/main/resources/";
	
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
	
	public String open(Node node) throws FileNotFoundException, IOException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = fileChooserBuilder("Choose start file", HOME_DIR, ".java");
		File file = fileChooser.showOpenDialog(node.getScene().getWindow());
		
		fileCheck(file, "java");
		
		char[] charArray;
		try (FileReader fileReader = new FileReader(file)){
			charArray = new char[(int) file.length()];
			fileReader.read(charArray);
		}
		
		return new String(charArray);
	}
}
