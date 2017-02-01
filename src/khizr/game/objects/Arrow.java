package khizr.game.objects;

import java.awt.Graphics;

import khizr.game.Game;
import khizr.game.graphics.Images;

public class Arrow {
	/**
	 * author: Khizr Khan
	 */

	private boolean visible;			//if arrow is on screen
	private boolean rightDirection;		//where arrow is facing
	private boolean moving;				//if arrow is shot
	private int speed;					//speed of arrow
	private int xPos;					//x position of arrow
	private int yPos;					//y position of arrow
	
	
	Player player;
	Game game;
		
	public Arrow(Game game, int arrowSpeed, Player player){
		this.game = game;
		this.player = player;
		speed = arrowSpeed;
		visible = false;
		xPos = 315;	//starting y position of arrow
		yPos = 150;	//starting x positoin of arrow
	}
	
	public void tick(){		//updating the arrow logic
		
		
		if(moving){
			if (rightDirection){
				xPos += speed;		//if arrow is facing right and moving, the x position will increase
			}
			else{
				xPos -= speed;		//if arrow is facing left and moving, the x position will decrease
			}
		}
	}
	
	public void render(Graphics g){		//updates the arrow graphics
		if (rightDirection && visible && moving)
			g.drawImage(Images.arrowRight, xPos, yPos, 50, 100, null);	//draws arrows if its moving to the right	
		
		else if(visible && moving)
			g.drawImage(Images.arrowLeft, xPos, yPos, 50, 100, null);	//draws arrow if it is moving to the left
		
		
	}
	
	public void shoot(boolean rightDirection, int yPos){	//when arrow is shot
		this.yPos = yPos;	
		visible = true;		//makes arrow visible
		moving = true;		//makes arrow move
		this.rightDirection = rightDirection;
	}
	
	public void stop(){		//when arrow is stopped
		moving = false;		//makes arrow stop moving
		visible = false;	//makes arrow invisible
		xPos = 315;			//resets the coordinates
		yPos = 150;
	}
	
	public boolean getRightDirection(){
		return rightDirection;
	}
	
	public boolean getMoving(){
		return moving;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
}
