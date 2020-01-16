package BrickBreaker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	private double x = 730;
	private double y = 750;
	private int width = 85;
	private int height = 20;
	private Rectangle rect = new Rectangle(x,y,width,height);

	Player() {
		rect.setFill(Color.GRAY);
		rect.setStroke(Color.DARKGRAY);
		//rect.setStrokeWidth(5);
	}
	
	public Rectangle get() {return rect;}
	public double getX() {return x;}
	public double getY() {return y;}
	public void setX(double x) { this.x = x; rect.setX(x);}

}
