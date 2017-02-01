package khizr.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import khizr.game.Game;
import khizr.game.graphics.Images;

public class MenuState extends State {
	/**
	 * author: codeNmore
	 */

	Game game;
	Color peach;
	Color red;
	Color gray;
	Color hover;
	Font f;
	Font sf;

	
	public MenuState(Game game) {
		super(game);
		this.game = game;
		
		peach = new Color(239, 231, 189);
		red = new Color(189, 90, 66);
		gray = new Color(195, 195, 195);
		hover = new Color(222, 173, 115);
		f = new Font("Britannic Bold", Font.BOLD, 90);
		sf = new Font("Britannic Bold", Font.BOLD, 40);
	}

	@Override
	public void tick() {
		if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>300 && game.getMouseManager().getMouseY()<350  && game.getMouseManager().leftPressed){
			State.setState(game.gameState);
		}
		
		if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>380 && game.getMouseManager().getMouseY()<430 && game.getMouseManager().leftPressed){
			State.setState(game.instructionsState);
        }
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g.setColor(peach);
        g.fillRect(0, 0, 680, 480);
        
        g.drawImage(Images.icon, -20, 0,400, 300, null);
        
        
        g.setFont(f);
        g.setColor(red);
        g.drawString("BOW N'", 300, 120);
        g.drawString("ARROW", 300, 200);
        
        g.fillRect(60, 300, 560, 50);
        g.fillRect(60, 380, 560, 50);
        
        if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>300 && game.getMouseManager().getMouseY()<350){
        	g.setColor(hover);
        	g.fillRect(60, 300, 560, 50);
        }
        
        if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>380 && game.getMouseManager().getMouseY()<430){
        	g.setColor(hover);
        	g.fillRect(60, 380, 560, 50);
        }
        
        g.setColor(gray);
        g.fillRect(20, 267, 640, 4);
        
        g.setColor(Color.WHITE);
        g.setFont(sf);
        g.drawString("PLAY", 295, 340);
        g.drawString("INSTRUCTIONS", 200, 420);
        
        
        
        

	}


}
