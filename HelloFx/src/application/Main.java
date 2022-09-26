package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group(); 
		Scene scene = new Scene(root, 600, 600, Color.LIGHTBLUE);
		
		Text text = new Text();
		text.setText("WHOOOA!!");
		text.setX(300);
		text.setY(50);
		text.setFont(Font.font("Verdana", 50));
		text.setFill(Color.BLUEVIOLET);
		
		Line line = new Line();
		line.setStartX(100);
		line.setStartY(50);
		line.setEndX(200);
		line.setEndY(100);
		line.setStrokeWidth(5);
		line.setStroke(Color.RED);
		line.setOpacity(0.5);
		line.setRotate(50);
		
		Rectangle rectangle = new Rectangle(); 
		rectangle.setX(400);
		rectangle.setY(400);
		rectangle.setWidth(100);
		rectangle.setHeight(150);
		rectangle.setFill(Color.SEAGREEN);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(3);
		
		Polygon triangle = new Polygon();
		triangle.getPoints().setAll(200.0, 200.0,
									300.1, 300.2,
									200.5, 300.78);
		triangle.setFill(Color.YELLOW);
		
		Circle circle = new Circle();
		circle.setCenterX(50);
		circle.setCenterY(300);
		circle.setRadius(40);
		circle.setFill(Color.LIGHTBLUE);
		circle.setStroke(Color.FUCHSIA);
		circle.setStrokeWidth(2);
		
		Image image = new Image("pizza.png");
		ImageView imageView = new ImageView(image);
		imageView.setX(300);
		imageView.setY(300);

		root.getChildren().add(text);
		root.getChildren().add(line);
		root.getChildren().add(rectangle);
		root.getChildren().add(triangle);
		root.getChildren().add(circle);
		root.getChildren().add(imageView);
		
		stage.setScene(scene);
		stage.show();
	}
}
