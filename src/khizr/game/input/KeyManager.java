package khizr.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	/**
	 * author: Khizr Khan
	 */

	private boolean[] keys;
	public boolean up, down, left, right, space, spaceB, spaceS, spaceR;

	public KeyManager() {
		keys = new boolean[256];
		spaceR = true;
		spaceS = false;
	}

	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];

		if (space == true) {
			spaceB = true;
		}

		if (space && spaceR) {
			spaceS = true;
			spaceR = false;
		}

	}

	public void keyPressed(KeyEvent e) {

		keys[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

		if (space == false) {
			spaceR = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			return;

		spaceB = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
