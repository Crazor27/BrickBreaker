package BrickBreaker;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Brick {

	//private int total;
	private Rectangle[] rec = new Rectangle[140];
	private static int strokeWidth = 5;

	Brick() {
		//total = bricks;
		int x = 0;
		int y = 20;
		int width = 100;
		int height = 25;
		for(int i = 0; i < 14; i++) {
			for (int j = 0; j < 10; j++) {
				if ((int)(Math.random() * 4) <= 2) {
					rec[10*i + j] = makeRect(x,y, width, height);
				} else {
					rec[10*i + j] = new Rectangle(0,0, 0, 0);
				}
				x += 105;
				if (x >= 1470) {
					x = 0;
					y += 35;
				}
			}
		}
	}

	 public static Rectangle makeRect (int x, int y, int width, int height) {
		 Rectangle rect = new Rectangle(x,y, width, height);
		 Color border;
		 Color fill;
		 int colour = (int)(Math.random() * 5);
		 switch (colour) {
		 case 1:
			 border = Color.DARKBLUE;
			 fill = Color.BLUE;
			 break;
		 case 2:
			 border = Color.DARKGREEN;
			 fill = Color.GREEN;
			 break;
		 case 3:
			 border = Color.DARKRED;
			 fill = Color.RED;
			 break;
		 case 4:
			 border = Color.DARKORANGE;
			 fill = Color.ORANGE;
			 break;
		 default:
			 border = Color.DARKVIOLET;
			 fill = Color.VIOLET;
			 break;
		 }

		 rect.setStroke(border);
		 rect.setStrokeWidth(strokeWidth);
		 rect.setFill(fill);
		 return rect;
	 }

	 public Rectangle[] getRect() {return rec;}
	// public int getTotal() {return total;}
}
