package khizr.game.states;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import khizr.game.Game;
import khizr.game.graphics.Animation;
import khizr.game.graphics.Images;
import khizr.game.objects.Arrow;
import khizr.game.objects.Player;
import khizr.game.objects.Target;


public class GameState extends State {
	/**
	 * author: Khizr Khan
	 */
	
	private static final String BufferedImage = null;

	private Game game;
	private Player player;
	Random random;
	Timer timer;
	Font f;
	Font sf;
	Color peach;
	Color red;
	
	private Target target[];
	private int arrowAmount;
	private int flyingArrowAmount;
	private Arrow arrow[];
	private int highlightBlockY;
	public int score;
	private int targetLimit;
	private int targetAmount;
	private int r;
	private int time;
	private int startTime;
	private boolean start;

	Color brown;

	public GameState(Game game){ 
		super(game);
		this.game = game;
		random = new Random();
		timer = new Timer();
		f = new Font("Britannic Bold", Font.BOLD, 70);
		sf = new Font("Britannic Bold", Font.PLAIN, 30);
		peach = new Color(239, 231, 189);
		red = new Color(189, 90, 66);
		
		player = new Player(game);
		target = new Target[10];
		brown = new Color(66, 31, 5);
		arrowAmount = player.getArrowAmount();
		flyingArrowAmount = 0;
		highlightBlockY = 1;
		score = 0;
		targetAmount = 0;
		targetLimit = 5;
		start = true;
		
		
		for(int i = 0; i<5; i++){
			target[i] = new Target(20, 170 + (61*i) );
		}
		
		for(int i = 5; i<10; i++){
			target[i] = new Target((61 * 10)+20, 170 + (61*(i-5)));
		}
		
		arrow = new Arrow[arrowAmount];
		for(int i = 0; i<arrowAmount; i++){
			arrow[i] = new Arrow(game, player.getArrowSpeed(), player);
		}
		

	}
	
	public void start(){
		startTime =(int) System.currentTimeMillis();
		score = 0;
	
		
	}
	
	@Override
	public void tick() {
		
		if(start){
			startTime = (int) System.currentTimeMillis();
			start = false;
		}
		
		if (time < -2)
			State.setState(game.menuState);
		time = (int)(30000 - (System.currentTimeMillis() - startTime))/1000;
		
		while(targetAmount != targetLimit){
			r = random.nextInt(10);
			if(!target[r].getVisible()){
				target[r].setVisible(true);
				targetAmount++;
			}
		}
		
		for(int a=0; a<arrow.length; a++){
			for(int t = 0; t<targetAmount; t++){
				
				if(arrow[a].getRightDirection()){
					if(arrow[a].getxPos() > 595 && arrow[a].getyPos() == 150+(61*t) && target[t+5].getVisible()){
						arrow[a].stop();
						score++;
						target[t+5].setVisible(false);
						targetAmount--;
						break;
					}
				}
				
				else{
					if(arrow[a].getxPos() < 35 && arrow[a].getyPos() == 150+(61*t) && target[t].getVisible()){
						arrow[a].stop();
						score++;
						target[t].setVisible(false);
						targetAmount--;
						break;
					}
				}
			}
		}
		
		
		if(player.getyPos() >= 161 && player.getyPos()<188)
			highlightBlockY = 480 - (61*5)-5;
		else if(player.getyPos() > 188 +(61 * 0) && player.getyPos()<188 + (61 *1))
			highlightBlockY = 480 - (61*4)-5;
		else if(player.getyPos() > 188 +(61 * 1) && player.getyPos()<188 + (61 *2))
			highlightBlockY = 480 - (61*3)-5;
		else if(player.getyPos() > 188 +(61 * 2) && player.getyPos()<188 + (61 *3))
			highlightBlockY = 480 - (61*2)-5;
		else if(player.getyPos() > 188 +(61 * 3) && player.getyPos()<188 + (61 *4))
			highlightBlockY = 480 - (61*1)-5;
		
		
		if(game.getKeyManager().spaceS){
			for(int i=0; i<arrowAmount; i++){
				System.out.println("1");
				if(!arrow[i].getMoving()){
					arrow[i].shoot(player.getrightDirection(), highlightBlockY -20);
					break;
				} 
			}
			
			
			game.getKeyManager().spaceS = false;
			
		}
		
		for(int i = 0; i<player.getArrowAmount(); i++){
			arrow[i].tick();
		}
		
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		g.setColor(peach);
		g.fillRect(0, 0, 680 , 165);
		g.setFont(sf);
		g.setColor(red);
		g.drawString("SCORE: " + score, 450,60);
		g.drawString("Time Left: " + time, 450,100);
		
		g.setFont(f);
        g.setColor(red);
        g.drawString("BOW N'", 30, 70);
        g.drawString("ARROW", 30, 140);
		
		g.setColor(brown);
		g.fillRect(0, 165, 680, 315);
		
		for(int i = 1; i <= 5; i++ ){
			for (int j = 0; j < 11; j++){
				if((i + j) %2 == 0)
					g.drawImage(Images.darkGrass, (61 * j)+4, 480 - (61*i)-5, 61, 61, null);
				else
					g.drawImage(Images.lightGrass, (61 * j)+4, 480 - (61*i)-5, 61, 61, null);
			}
		}
		
		for(int i=1; i <= 5; i++){
			g.drawImage(Images.brownGrass, (61 * 5)+4 +5, 480 - (61*i)-5, 61 - 10, 61, null);
		}
		
		g.drawImage(Images.whiteGrass, (61 * 5)+4 +5, highlightBlockY,61-10, 61, null);
		
		g.setColor(Color.getColor("#8B4513"));
		g.fillRect((61 * 5)+4, 480 - (61*5)-5, 5, 305);
		g.fillRect((61 * 6)+4 - 5, 480 - (61*5)-5, 5, 305);
		
		for(int i =0; i<10; i++){
			target[i].render(g);			
		}
	
		for(int i = 0; i<player.getArrowAmount(); i++){
			arrow[i].render(g);
		}
		
		player.render(g);
		
		if(time<1){
			g.setColor(peach);
			g.fillRect(20, 200, 640, 180);
			g.setColor(red);
			g.setFont(f);
			g.drawString("SCORE: " + score, 200, 300);
		}
		

		
	}
	

	public int getScore() {
		return score;
	}
	

}
