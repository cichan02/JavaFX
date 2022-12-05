package by.piskunou.university.ds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting JavaFX application");

        String fxmlFile = "/fxml/main.fxml";
        log.info("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        log.info("Showing JFX scene");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
