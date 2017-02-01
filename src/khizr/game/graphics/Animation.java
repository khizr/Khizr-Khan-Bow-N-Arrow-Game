package khizr.game.graphics;

import java.awt.image.BufferedImage;

public class Animation {
	/**
	 * author: codeNmore
	 */

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed; // time between each frame
		this.frames = frames;
		index = 0; // all animations start from first frame
		timer = 0; // animations start will be when time is 0.
		lastTime = System.currentTimeMillis();
	}

	public void tick() { // updates the animation every 1/60th of a second
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++; // goes on to the next frame
			timer = 0;
			if (index >= frames.length)
				index = 0;
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public int getIndex() {
		return index;
	}

}
