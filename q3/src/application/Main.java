package application;
	
//Imports
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {
	//Variable Decleration
	//Initialize the 3 points on the perimiter
	private Circle circle = new Circle(360, 250, 150);
	double x = genDoub();
	private Circle point1 = new Circle(360+150*Math.cos(x), 250+150*Math.sin(x), 12);
	double y = genDoub();
	private Circle point2 = new Circle(360+150*Math.cos(y), 250+150*Math.sin(y), 12);
	double z = genDoub();
	private Circle point3 = new Circle(360+150*Math.cos(z), 250+150*Math.sin(z), 12);
	private Line a = new Line();
	private Line b = new Line();
	private Line c = new Line();
	Text linA = new Text("a");
	Text linB = new Text("b");
	Text linC = new Text("c");
	Text angA = new Text();
	Text angB = new Text();
	Text angC = new Text();
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		
		fixLines();
		
		circle.setFill(Color.WHITE);
	    circle.setStroke(Color.BLACK);
	    
		point1.setFill(Color.RED);
		point1.setStroke(Color.BLACK);
		
		point2.setFill(Color.RED);
		point2.setStroke(Color.BLACK);

		point3.setFill(Color.RED);
		point3.setStroke(Color.BLACK);
		
		setAng();
		
		pane.getChildren().addAll(circle,point1,point2,point3,a,b,c,linA,linB,linC,angA,angB,angC);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 720, 500);
	    primaryStage.setTitle("Q3"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
	    //If point a is moved
		point1.setOnMouseDragged(e -> {
			if (point1.contains(e.getX(), e.getY())) {
				if(circle.contains(e.getX(),e.getY()))
				{
					//move the point to mouse
					point1.setCenterX(e.getX());
					point1.setCenterY(e.getY());
					//call functions to fix lines and calculate new angles
					fixLines();
					setAng();
				}
			}
		});
		
		//If point b is moved
		point2.setOnMouseDragged(e -> {
			if (point2.contains(e.getX(), e.getY())) {
				if(circle.contains(e.getX(),e.getY()))
				{
					//move the point to mouse
					point2.setCenterX(e.getX());
					point2.setCenterY(e.getY());
					//call functions to fix lines and calculate new angles
					fixLines();
					setAng();
				}
			}
		});
		//If point c is moved
		point3.setOnMouseDragged(e -> {
			if (point3.contains(e.getX(), e.getY())) {
				if(circle.contains(e.getX(),e.getY()))
				{
					//move the point to mouse
					point3.setCenterX(e.getX());
					point3.setCenterY(e.getY());
					//call functions to fix lines and calculate new angles
					fixLines();
					setAng();
				}
			}
		});
	}
	
	
	//This function will reset the lines that makeup the triangle if called
	private void fixLines()
	{
		//set points for start and end of first line
		a.setStartX(point1.getCenterX());
		a.setStartY(point1.getCenterY());
		a.setEndX(point2.getCenterX());
		a.setEndY(point2.getCenterY());
		linA.setX((point1.getCenterX()+point2.getCenterX())/2);
	    linA.setY((point1.getCenterY()+point2.getCenterY())/2);
		
	    //set points for start and end of second line
		b.setStartX(point2.getCenterX());
		b.setStartY(point2.getCenterY());
		b.setEndX(point3.getCenterX());
		b.setEndY(point3.getCenterY());
		linB.setX((point2.getCenterX()+point3.getCenterX())/2);
	    linB.setY((point2.getCenterY()+point3.getCenterY())/2);
		
	    //set points for start and end of third line
		c.setStartX(point3.getCenterX());
		c.setStartY(point3.getCenterY());
		c.setEndX(point1.getCenterX());
		c.setEndY(point1.getCenterY());
		linC.setX((point1.getCenterX()+point3.getCenterX())/2);
	    linC.setY((point1.getCenterY()+point3.getCenterY())/2);
	}
	
	//This function generates and returns a random double
	private double genDoub()
	{
		Random r = new Random();
		return(2*Math.PI * r.nextDouble());
	}
	
	//This function calculates an displays the angles for alll three points
	private void setAng()
	{
		//Calculate lengths
		double aLen = Math.sqrt(Math.pow((point2.getCenterY()-point1.getCenterY()),2)+Math.pow((point2.getCenterX()-point1.getCenterX()),2));
		double bLen = Math.sqrt(Math.pow((point3.getCenterY()-point2.getCenterY()),2)+Math.pow((point3.getCenterX()-point2.getCenterX()),2));
		double cLen = Math.sqrt(Math.pow((point1.getCenterY()-point3.getCenterY()),2)+Math.pow((point1.getCenterX()-point3.getCenterX()),2));
		
		//Display lengths
		linA.setText("a = "+Double.toString(aLen).substring(0,3));
		linB.setText("b = "+Double.toString(bLen).substring(0,3));
		linC.setText("c = "+Double.toString(cLen).substring(0,3));
		
		//Calculate angles
		double ang1 = Math.acos((aLen*aLen-bLen*bLen-cLen*cLen) / (-2*bLen*cLen));
		double ang2 = Math.acos((bLen*bLen-aLen*aLen-cLen*cLen) / (-2*aLen*cLen));
		double ang3 = Math.acos((cLen*cLen-bLen*bLen-aLen*aLen) / (-2*aLen*bLen));
		
		//Display angles
		angA.setText(Double.toString(ang1*(180/Math.PI)).substring(0,5));
		angB.setText(Double.toString(ang2*(180/Math.PI)).substring(0,5));
		angC.setText(Double.toString(ang3*(180/Math.PI)).substring(0,5));
		
		angA.setX(point3.getCenterX());
		angA.setY(point3.getCenterY()-20);
		
		angB.setX(point1.getCenterX());
		angB.setY(point1.getCenterY()-20);

		angC.setX(point2.getCenterX());
		angC.setY(point2.getCenterY()-20);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
