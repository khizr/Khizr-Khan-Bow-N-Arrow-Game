package khizr.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import khizr.game.display.Display;
import khizr.game.graphics.Images;
import khizr.game.input.KeyManager;
import khizr.game.input.MouseManager;
import khizr.game.states.GameState;
import khizr.game.states.InstructionsState;
import khizr.game.states.MenuState;
import khizr.game.states.State;

/**
 * 
 * @author codeNmore modified by Khizr Khan
 * 
 */

public class Game implements Runnable {

	private Display display; // display for game
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState; // creates the game state
	public State menuState; // creates the menu state
	public State instructionsState; // creates the instructions state

	private KeyManager keyManager; // manages keyboard inputs
	private MouseManager mouseManager; // manages mouse inputs

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addKeyListener(keyManager);
		Images.init();

		gameState = new GameState(this); // instantiates the gameSate
		menuState = new MenuState(this); // instantiates the menuState
		instructionsState = new InstructionsState(this); // instantiates the
															// instructionsState
		State.setState(menuState); // sets state to menu by default
	}

	private void tick() { // method for changes made every 1/60th of a second
		keyManager.tick(); // updates the keymanager

		if (State.getState() != null)
			State.getState().tick(); // updates the state
	}

	private void render() { // updates the graphics of game
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!

		if (State.getState() != null)

			State.getState().render(g); // returns the current state

		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 60; // game will run at 60 frames per second
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) { // the game loop
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
