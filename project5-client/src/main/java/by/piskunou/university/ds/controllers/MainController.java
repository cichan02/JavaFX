package by.piskunou.university.ds.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
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
			e.printStackTrace();
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
			e.printStackTrace();
		} 
	}
	
	@FXML
	public void get() {
		try(Socket s = new Socket(HOST, 4321)) {
			log.debug("Send 'get' command to server");
			
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println("get");
			pr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@FXML
	public void updateCurrentFile() {
		currentFile = fileList.getSelectionModel().getSelectedItem();
	}
}
