package BrickBreaker;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Ball {

	private double cx;
	private double cy;
	private double vx;
	private double vy;
	private double radius;
	private Color color;
	private Circle circle;

	Ball(double cx, double cy, double radius, double vx, double vy) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		this.vx = vx;
		this.vy = vy;
		color = Color.BROWN;
		circle = new Circle(cx, cy, radius);
		circle.setFill(color);
	}

	public Circle updatePosition(double vx, double vy) {
		cx += vx;
		cy += vy;
		this.vx = vx;
		this.vy = vy;
		circle.setCenterX(cx);
		circle.setCenterY(cy);
		//circle.relocate(cx,cy);
		return circle;
	}

	public Circle getCircle() { return circle;}
	public double getCx() {return cx;}
	public double getCy() {return cy;}
	public double getVx() {return vx;}
	public double getVy() {return vy;}
}
