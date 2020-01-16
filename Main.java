package BrickBreaker;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {

	double vx = -5;
	double vy = -5;

	Brick brick = new Brick();
	Ball ball1 = new Ball(765, 710,10,vx,vy);
	Rectangle [] rec = brick.getRect();
	Circle ball = ball1.getCircle();
	Player player = new Player();
	Shape intersectionBounds;
	boolean pause1 = false;

	void update(double vx, double vy) {
		ball1.updatePosition(vx, vy);
	}

	boolean checkWin() {
		boolean win = true;
		for(int i = 0; i < 140; i++) {
			if(rec[i].getWidth() != 0) {
				win = false;
			}
		}
		return win;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		final int maxWidth = 1465;
		final int maxHeight = 800;
		Scene scene = new Scene(root, maxWidth, maxHeight);
		primaryStage.setTitle("Testing");
		primaryStage.setScene(scene);
		primaryStage.show();
		root.getChildren().add(ball);
		root.getChildren().add(player.get());



		Timer timer = new Timer();
		for (int i = 0; i < 140; i++ ) {
			root.getChildren().add(rec[i]);
		}

		Timeline inBounds = new Timeline(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				double cx = ball.getCenterX();
				double cy = ball.getCenterY();

				if((cx - ball.getRadius()) < 0 || cx + ball.getRadius() > maxWidth ) {
					vx = -ball1.getVx();
					update(vx, vy);
				}

				if(cy - ball.getRadius() < 0 ) {
					vy = -ball1.getVy();
					update(vx, vy);
				} else if(cy + ball.getRadius() > maxHeight ) {
					vx = 0;
					vy = 0;
					update(vx, vy);
					System.out.println("You lose.");
					System.exit(0);
				}
			}
		}));

		Timeline ballMove = new Timeline(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				update(vx, vy);
				TranslateTransition translate = new TranslateTransition();
				translate.setDuration(Duration.millis(500));
				translate.setNode(ball);
				translate.setByX(vx);
				translate.setByY(vy);
			    translate.setAutoReverse(false);
			    translate.play();
			}
		}));


		Timeline checkCollision = new Timeline(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (int i = 0; i < 140; i++) {
					Shape collision = Shape.intersect(ball, rec[i]);
					if (collision.getBoundsInLocal().getWidth() >0 || collision.getBoundsInLocal().getHeight() >0) {
						//intersections

						/*System.out.print(" Cx range:" + (ball1.getCx() - ball.getRadius()) + "-" + (ball1.getCx() + ball.getRadius()) +
								" Cy range: " + (ball1.getCy() - ball.getRadius())+ "-" + (ball1.getCy() + ball.getRadius()) +
								" BrickX range " + rec[i].getX() + "-" + (rec[i].getX() + rec[i].getWidth()) +
								" BrickY range " + rec[i].getY() + "-" + (rec[i].getY() + rec[i].getHeight()));
						 */
						if( (ball1.getCy() >= rec[i].getY() - rec[i].getStrokeWidth()) && (ball1.getCy() <= rec[i].getY() + rec[i].getHeight() + rec[i].getStrokeWidth())
								&& ( (ball1.getCx() + ball.getRadius() >= rec[i].getX() - rec[i].getStrokeWidth() && ball1.getCx() + ball.getRadius() <= rec[i].getX() + 0.2*rec[i].getWidth())
								|| (ball1.getCx() - ball.getRadius() <= rec[i].getX() + rec[i].getWidth() + rec[i].getStrokeWidth() && ball1.getCx() - ball.getRadius() >= rec[i].getX() + 0.8*rec[i].getWidth()))
							//((collision.contains(ball.getCenterX() + ball.getRadius(), ball.getCenterY()))
								//|| collision.contains(ball.getCenterX() - ball.getRadius(), ball.getCenterY()))
						//	&&(collision.contains(rec[i].getX(), rec[i].getY() + (double)(rec[i].getHeight()/2))
						//		|| collision.contains(rec[i].getX() + rec[i].getWidth(), rec[i].getY() + (double)(rec[i].getHeight()/2)))
							) {
							//SHOULD BE HORIZONTAL BUT...

							vx = -ball1.getVx();
							update(vx, vy);
						//	System.out.print("Horizontal");


						} else if( (ball1.getCx() >= rec[i].getX() - rec[i].getStrokeWidth()) && (ball1.getCx() <= rec[i].getX() + rec[i].getWidth() + rec[i].getStrokeWidth())
								&& ( (ball1.getCy() + ball.getRadius() >= rec[i].getY() - rec[i].getStrokeWidth() && (ball1.getCy() + ball.getRadius() <= rec[i].getY() + 0.2*rec[i].getHeight()) )
								|| (ball1.getCy() - ball.getRadius() <= rec[i].getY() + rec[i].getHeight() + rec[i].getStrokeWidth() && (ball1.getCy() - ball.getRadius() >= rec[i].getY() + 0.8*rec[i].getHeight()) ))

								) {
							//SHOULD BE vertical but...

							vy = -ball1.getVy();
							update(vx, vy);
						//	System.out.print("Vertical");

						} else if( (ball1.getCx() + ball.getRadius() == rec[i].getX() && (ball1.getCy() + ball.getRadius() == rec[i].getY()) &&(ball1.getVx() > 0 && ball1.getVy() > 0)) 
								|| (ball1.getCx() - ball.getRadius() == rec[i].getX() + rec[i].getWidth() && (ball1.getCy() + ball.getRadius() == rec[i].getY() &&(ball1.getVx() < 0 && ball1.getVy() > 0))) 
								|| (ball1.getCx() + ball.getRadius() == rec[i].getX() && (ball1.getCy() - ball.getRadius() == rec[i].getY() + rec[i].getHeight() && ball1.getVx() > 0 && ball1.getVy() < 0)) 
								|| (ball1.getCx() - ball.getRadius() == rec[i].getX() + rec[i].getWidth() && (ball1.getCy() - ball.getRadius() == rec[i].getY() + rec[i].getHeight() && ball1.getVx() < 0 && ball1.getVy() < 0)) ) {
							//corner

							vx = -ball1.getVx();
							vy = -ball1.getVy();
							update(vx, vy);
						//	System.out.print("Diagonal");

						} else {
							vx = -ball1.getVx();
							vy = -ball1.getVy();
							update(vx, vy);

							System.out.print("Somethings not right.");
						}



						System.out.println("");
						rec[i].setX(0);
						rec[i].setY(0);
						rec[i].setWidth(0);
						rec[i].setHeight(0);
						rec[i].setStrokeWidth(0);

						if(checkWin()) {
							System.out.println("You win!");
							System.exit(0);
						}
						pause1=true;

					}
				}
				Shape collision2 = Shape.intersect(ball, player.get());
				
				if (collision2.getBoundsInLocal().getWidth() >0 || collision2.getBoundsInLocal().getHeight() >0 ) {
				     
					System.out.print(" Cx range:" + (ball1.getCx() - ball.getRadius()) + "-" + (ball1.getCx() + ball.getRadius()) +
					" Cy range: " + (ball1.getCy() - ball.getRadius())+ "-" + (ball1.getCy() + ball.getRadius()) +
					" PlayerX range " + player.getX() + "-" + (player.getX() + player.get().getWidth()) +
					" PlayerY range " + player.getY() + "-" + (player.getY() + player.get().getHeight()));
			 
					
					if ( (ball1.getCx() + ball.getRadius() == player.getX() && (ball1.getCy() + ball.getRadius() == player.getY()) &&(ball1.getVx() > 0 && ball1.getVy() > 0))
								||(ball1.getCx() - ball.getRadius() == player.getX() + player.get().getWidth() && (ball1.getCy() + ball.getRadius() == player.getY() &&(ball1.getVx() < 0 && ball1.getVy() > 0))) ) {
						//diagonal
						System.out.print(" Diagonal Plat Hit");
						vx = -ball1.getVx();
						vy = -ball1.getVy();
						update(vx, vy);
					} else if( (ball.getCenterX() >= player.getX()) && (ball.getCenterX() <= (player.getX() + player.get().getWidth()))
							&& ((ball.getCenterY() + ball.getRadius()) <= player.getY()+ 0.2*player.get().getHeight()) &&
							(ball.getCenterY() + ball.getRadius()) >= player.getY()) {
						//vertical
						System.out.print(" Vertical Plat Hit");
						vy = -ball1.getVy();
						update(vx, vy);
					} else if((ball.getCenterY() >= player.getY()) && (ball.getCenterY() <= (player.getY() + player.get().getHeight()))
							&& ( (ball.getCenterX() - ball.getRadius() == (player.getX() + player.get().getWidth()))
									|| ((ball.getCenterX() + ball.getRadius()) == player.getX()))) {
						//horizontal
						System.out.print(" Horizontal Plat Hit");
						vx = -ball1.getVx();
						update(vx,vy);
					}
					
					System.out.println("");
					/*else {
					}
						vx = -ball1.getVx();
						vy = -ball1.getVy();
						update(vx,vy);
					}*/
				}
			}
		}));

		/*Timeline pause = new Timeline(new KeyFrame(new Duration(20), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(pause1){
					ballMove.pause();
				} else {
					ballMove.play();
				}
			}
		}));
		*/
		//pause.setCycleCount(Timeline.INDEFINITE);
		inBounds.setCycleCount(Timeline.INDEFINITE);
		ballMove.setCycleCount(Timeline.INDEFINITE);
		checkCollision.setCycleCount(Timeline.INDEFINITE);
		//pause.playFromStart();
		inBounds.playFromStart();
		ballMove.playFromStart();
		checkCollision.playFromStart();

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			   @Override
			   public void handle(MouseEvent e) {
				   if ((e.getX() - 40) <= 0) {
				    	  player.setX(0);
				   } else if((e.getX()) >= 1430) {
				    	  player.setX(1388);
				   } else {
				    	  player.setX(e.getX() - 40);
				   }
			   }
			};
			//Adding event Filter
			player.get().addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);

	}



	 public static void main (String [] args) {
         launch();
     }
}
