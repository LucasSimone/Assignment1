package application;
	
//Imports
import java.lang.Math;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane gp = new GridPane();
        gp.setPadding(new Insets(30,30,30,30));
		
		TextField amount = new TextField();
		TextField years = new TextField();
		TextField interestRate = new TextField();
		TextField future = new TextField();
		
		//Make amount text field
		Label amountLabel = new Label("Investment Amount    ");
        amount.setPromptText("Amount");
        gp.add(amountLabel, 0,0);
        gp.add(amount, 1, 0);
        
        //Make amount text field
        Label yearLabel = new Label("Years");
        years.setPromptText("Years");
        gp.add(yearLabel, 0,1);
        gp.add(years, 1, 1);
        
        //Make annual intrest text field
        Label interestLabel = new Label("Annual Interest Rate");
        interestRate.setPromptText("Rate");
        gp.add(interestLabel, 0,2);
        gp.add(interestRate, 1, 2);
        
        //Make future value text field
        Label futureLabel = new Label("Future Value");
        future.setPromptText("Future");
        gp.add(futureLabel, 0,3);
        gp.add(future, 1, 3);
        
        //Make calculate button
        Label btnLabel = new Label();
        Button _btn1 = new Button("Calculate");
        _btn1.setDefaultButton(true);
        gp.add(btnLabel, 0, 4);
        gp.add(_btn1, 1, 4);

        //If calculate button is clicked
        _btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//Calculation for future value
            	double expo = Double.parseDouble(years.getText()) * 12;
            	double base = 1 + ((Double.parseDouble(interestRate.getText())/12)/100);
            	double right = Math.pow(base,expo);
            	double left = Double.parseDouble(amount.getText());
            	//Display future value
                future.appendText(Double.toString(left*right));
            }
        });
        
        Scene scene = new Scene(gp, 500, 300);
	    primaryStage.setTitle("Question2");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
