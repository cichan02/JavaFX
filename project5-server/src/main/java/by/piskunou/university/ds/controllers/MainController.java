package by.piskunou.university.ds.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.piskunou.university.ds.util.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController{
	private static final Logger log = LogManager.getLogger();
	
	@FXML private Button startButton;
	
	private Thread serverThread = new Server();
	
	@FXML
	public void startServer() {
		startButton.setDisable(true);
		
		log.info("Start server");
		serverThread.start();
	}
}
