package application;
	
//Imports
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Random r = new Random();
		
		GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setHgap(4);
	    pane.setVgap(1);
	    //This for loop will generate 3 random cards and display them
		for(int i = 0;i<3;i++)
		{
			//pick random card
			int cardNum = r.nextInt(53-1)+1;
			//get card
			ImageView card = new ImageView("file:Cards/"+Integer.toString(cardNum)+".png");
			//display card
			pane.add(card, i, 0);
		}
		
		Scene scene = new Scene(pane);
	    primaryStage.setTitle("Question1"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
