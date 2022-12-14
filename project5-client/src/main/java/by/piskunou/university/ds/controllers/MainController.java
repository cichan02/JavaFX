package by.piskunou.university.ds.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainController {
	private static final Logger log = LogManager.getLogger();
	private static final String HOST = "localhost";
	
	@FXML ListView<String> fileList;
	@FXML Button lsButton;
	@FXML Button cdButton;
	@FXML Button getButton;
	@FXML Label currentDir;
	
	private String currentFile;
	
	@FXML
	public void ls() {
		try(Socket s = new Socket(HOST, 4321)) {
			log.debug("Send 'ls' command to server");
			
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println("ls");
			pr.flush();
			
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			
			String str = bf.readLine();
			fileList.getItems().clear();
			if(!currentDir.getText().equals("/home/cichan")) {
				fileList.getItems().add("..");	
			}
			for(int i = 0; i < Integer.parseInt(str); i++) {
				String shortname = bf.readLine();
				fileList.getItems().add(shortname);
			}
		} catch (IOException e) {
			throwException();
		} 
	}
	
	@FXML
	public void cd() {
		try(Socket s = new Socket(HOST, 4321)) {
			log.debug("Send 'cd' command to server");
			
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println("cd " + currentFile);
			pr.flush();
			
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			
			fileList.getItems().clear();
			String answer = bf.readLine();
			if(answer.equals("yes")) {
				currentDir.setText(currentDir.getText() + "/" + currentFile);
			} else if(answer.equals("no")) {
			} else {
				currentDir.setText(answer);
			}
		} catch (IOException e) {
			throwException();
		} 
	}
	
	@FXML
	public void get() {
		try(Socket s = new Socket(HOST, 4321);
			BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
			DataInputStream dis = new DataInputStream(bis)) {
			log.debug("Send 'get' command to server");
			
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println("get");
			pr.flush();

			int filesCount = dis.readInt();

			for(int i = 0; i < filesCount; i++) {
				long fileLength = dis.readLong();
				String fileName = dis.readUTF();
					
				File file = new File("/home/cichan/Documents/University/Discipline of Specialization (DS)"
						+ "/Practice/project5-client/src/main/resources/client/" + fileName);
				file.createNewFile();
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				
				for(int j = 0; j < fileLength; j++) {
					bos.write(bis.read());
				}
				bos.close();
			}
			log.debug("Receive files");
		} catch (IOException e) {
			throwException();
		} 
	}
	
	@FXML
	public void updateCurrentFile() {
		currentFile = fileList.getSelectionModel().getSelectedItem();
	}
		
	private void throwException() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Server not found");
		alert.setHeaderText("Connection lost");
		alert.showAndWait();
	}	
}
