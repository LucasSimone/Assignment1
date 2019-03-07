package application;

//Imports
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.Scanner;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;



public class Main extends Application {
	//variable decleration
	BarChart<String,Number> barChart;
	TextField filename;
	@Override
	public void start(Stage primaryStage) {
		
		CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Letters");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Frequency");
        xAxis.setCategories(FXCollections.observableArrayList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
    	
		filename = new TextField("Enter directory here.");
		filename.setAlignment(Pos.BASELINE_LEFT);
		
		HBox hBox1 = new HBox(5);
	    hBox1.setAlignment(Pos.BOTTOM_CENTER);
	    hBox1.getChildren().addAll(new Label("FileName: "),	filename);
	    
	    Button view = new Button("View");
	    view.defaultButtonProperty().bind(view.focusedProperty());
	    view.setAlignment(Pos.BOTTOM_RIGHT);
	    hBox1.getChildren().addAll(view);
	    
	    barChart = new BarChart<>(xAxis, yAxis);
	    
	    HBox hBox2 = new HBox(5);
	    hBox2.setAlignment(Pos.BASELINE_CENTER);
	    hBox2.getChildren().addAll(barChart);
	    
	    VBox vbox = new VBox(hBox2,hBox1);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(600);
        primaryStage.show(); 
	    
        //If the button is clicked
        view.setOnAction(e-> 
        {
        	//call histo function
        	histo();
        });

        //If the enter key is pressed
        filename.setOnKeyPressed(e-> {
        	if (e.getCode() == KeyCode.ENTER)  
        	{
        		//call histo function
        		histo();
            }
        });
	    
	}
	
	//This function takes each char from the file one at a time and 
	//counts each occurrence of a letter by storing the result in a 
	//integer array. The information is then stored in a series which 
	//is displayed on a barchart.
	private void histo()
	{
		java.io.File file = new java.io.File(filename.getText());
    	try {
	    	Scanner input = new Scanner(file);
	    	int[] alpha = new int[26];
	    	while(input.hasNext())
	    	{
	    		//Count each letter
	    		String word = input.next();
	    		for(int i = 0;i<word.length();i++)
	    		{
	    			alpha[(int)Character.toLowerCase(word.charAt(i))-97]++;
	    		}
	    	}
	    	input.close();
	    	XYChart.Series<String,Number> chars = new XYChart.Series<>();
	    	chars.setName("Letter Frequency");
	    	//Add data to series
	    	for(int i = 0;i<26;i++)
	    	{
	    		chars.getData().add(new XYChart.Data<String,Number>(Character.toString((char)(i+97)),alpha[i]));
	    	}
	    	//Display the histogram
	    	barChart.getData().addAll(chars);
    	} catch(Exception e1) {}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
