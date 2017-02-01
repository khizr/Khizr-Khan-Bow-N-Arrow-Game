package khizr.game.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import khizr.game.graphics.Images;

public class Target {
	/**
	 * author: Khizr Khan
	 */

	private boolean visible;
	private int xPos;
	private int yPos;

	public Target(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		visible = false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (visible) {
			if (xPos == 20)
				g.drawImage(Images.targetLeft, xPos, yPos, 30, 54, null); // if
																			// target
																			// is
																			// on
																			// the
																			// left
																			// side,
																			// print
																			// on
																			// left
																			// side

			else
				g.drawImage(Images.targetRight, xPos, yPos, 30, 54, null); // if
																			// target
																			// is
																			// on
																			// the
																			// right
																			// right
																			// side,
																			// print
																			// on
																			// right
																			// side.
		}
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisible() {
		return visible;
	}
}
