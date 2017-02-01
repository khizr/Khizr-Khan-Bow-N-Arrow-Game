package khizr.game.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import khizr.game.Game;
import khizr.game.graphics.Animation;
import khizr.game.graphics.Images;

public class Player {
	/**
	 * author: Khizr Khan
	 */

	private Game game;
	private Animation downAnimation; // animation for moving down
	private Animation upAnimation; // animation for moving up
	private Animation ArrowLeft; // animation for shooting to the left
	private Animation ArrowRight; // animation for shooting the right
	private boolean rightDirection; // if player is facing right or left
	private int yPos; // y position of player
	private int speed; // speed of player
	private int arrowAmount; // how many arrows player can have in the air at
								// one time
	private int arrowSpeed; // how fast arrow moves

	public Player(Game game) {
		this.game = game;
		speed = 1;
		yPos = 165;
		arrowAmount = 5;
		arrowSpeed = 1;

		downAnimation = new Animation(100, Images.playerDown);
		upAnimation = new Animation(100, Images.playerUp);
		ArrowRight = new Animation(75, Images.playerArrowRight);
		ArrowLeft = new Animation(75, Images.playerArrowLeft);
	}

	public void tick() {
		if (game.getKeyManager().down)
			if ((yPos + speed) <= 408)
				yPos += speed;
		if (game.getKeyManager().up)
			if ((yPos - speed) >= 162)
				yPos -= speed;

		if (game.getKeyManager().right)
			rightDirection = true;
		if (game.getKeyManager().left)
			rightDirection = false;

		downAnimation.tick();
		upAnimation.tick();
		ArrowRight.tick();
		ArrowLeft.tick();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(getCurrentAnimationFrame(), (61 * 5) + 2, yPos, 64, 64,
				null);
	}

	private BufferedImage getCurrentAnimationFrame() { // return the frame of
														// selected frame

		if (game.getKeyManager().up) {
			return upAnimation.getCurrentFrame(); // if up is pressed, show up
													// animation
		} else if (game.getKeyManager().down) {
			return downAnimation.getCurrentFrame(); // if down is pressed, show
													// down animation
		} else if (game.getKeyManager().spaceB) { // if space is pressed, shoot.
			if (rightDirection) {

				if (ArrowRight.getIndex() == 12) {
					game.getKeyManager().spaceB = false;
				}
				return ArrowRight.getCurrentFrame();
			}
			if (!rightDirection) {
				if (ArrowLeft.getIndex() == 12) {
					game.getKeyManager().spaceB = false;
				}

				return ArrowLeft.getCurrentFrame();
			}
		} else if (rightDirection) { // if right is pressed, face right
			return Images.playerRight;
		} else {
			return Images.playerLeft;
		} // if left is pressed, face left
		return Images.playerLeft;

	}

	public int getArrowAmount() {
		return arrowAmount;
	}

	public int getArrowSpeed() {
		return arrowSpeed;
	}

	public boolean getrightDirection() {
		return rightDirection;
	}

	public int getyPos() {
		return yPos;
	}
}
