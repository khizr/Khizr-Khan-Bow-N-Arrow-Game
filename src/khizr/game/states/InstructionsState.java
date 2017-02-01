package khizr.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import khizr.game.Game;
import khizr.game.graphics.Images;

public class InstructionsState extends State {
	/**
	 * author: Khizr Khan
	 */
	
	Color peach;
	Color red;
	Color gray;
	Color hover;
	Font f;
	Font sf;

	public InstructionsState(Game game) {
		super(game);

		peach = new Color(239, 231, 189);
		red = new Color(189, 90, 66);
		gray = new Color(195, 195, 195);
		hover = new Color(222, 173, 115);
		f = new Font("Britannic Bold", Font.BOLD, 90);
		sf = new Font("Britannic Bold", Font.BOLD, 30);
	}

	@Override
	public void tick() {
		if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>50 && game.getMouseManager().getMouseY()<100  && game.getMouseManager().leftPressed){
			State.setState(game.gameState);
		}
		
		if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>130 && game.getMouseManager().getMouseY()<180 && game.getMouseManager().leftPressed){
			State.setState(game.menuState);
        }
		
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g.setColor(peach);
        g.fillRect(0, 0, 680, 480);
        
        
        g.setFont(sf);
        g.setColor(red);
        g.drawString("Use the up and down keys to move", 60, 290);
        g.drawString("Use the left and right keys to aim", 60, 330);
        g.drawString("Shoot as many targets as possible", 60, 370);
        g.drawString("in under 30 seconds", 60, 410);
        
        
        g.fillRect(60, 50, 560, 50);
        g.fillRect(60, 130, 560, 50);
        
        if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>50 && game.getMouseManager().getMouseY()<100){
        	g.setColor(hover);
        	g.fillRect(60, 50, 560, 50);
        }
        
        if(game.getMouseManager().getMouseX()>60 && game.getMouseManager().getMouseX()<620 && game.getMouseManager().getMouseY()>130 && game.getMouseManager().getMouseY()<180){
        	g.setColor(hover);
        	g.fillRect(60, 130, 560, 50);
        }
        
        g.setColor(gray);
        g.fillRect(20, 230, 640, 4);
        
        g.setColor(Color.WHITE);
        g.setFont(sf);
        g.drawString("PLAY", 295, 90);
        g.drawString("MAIN MENU", 233, 170);
		
	}

}
