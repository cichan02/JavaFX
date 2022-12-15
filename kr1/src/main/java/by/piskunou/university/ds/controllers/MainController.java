package by.piskunou.university.ds.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class MainController {
	private static final Logger log = LogManager.getLogger();
	
	private static final int LOWER_BOUND = 20;
	private static final int UPPER_BOUND = 150;
	
	@FXML private Button startButton;
	@FXML private Circle circle;
	
	@FXML
	public void start() {
		Thread thread1 = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					 log.warn("Interrupted!", e);
					 Thread.currentThread().interrupt();
				}
				
				synchronized(circle) {
					circle.setRadius(circle.getRadius() + 5);
				}
			}
		});
		
		Thread thread2 = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					log.warn("Interrupted!", e);
					Thread.currentThread().interrupt();
				}
				
				synchronized (circle) {
					circle.setRadius(circle.getRadius() - 10);
				}
			}
		});
		
		thread1.start();
		thread2.start();
		boolean check = true;
		
		while(check) {
			double localRadius;
			synchronized (circle) {
				localRadius = circle.getRadius();
			}	
			if(localRadius > UPPER_BOUND || localRadius < LOWER_BOUND) {
				check = false;
				thread1.interrupt();
				thread2.interrupt();
			}
		}
	}
}
