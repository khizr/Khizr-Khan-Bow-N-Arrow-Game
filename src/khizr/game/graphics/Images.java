package khizr.game.graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;

public class Images {
	/**
	 * author Khizr Khan
	 */

	public static BufferedImage[] playerDown; // player is moving down animation
	public static BufferedImage[] playerUp; // player is moving up animation
	public static BufferedImage[] playerArrowLeft; // player is shooting arrow
													// to the left animation
	public static BufferedImage[] playerArrowRight; // player is shooting arrow
													// to the right animation
	public static BufferedImage[] menuArrow; // Arrow gif displayed on menu
	public static BufferedImage playerLeft; // player is facing left
	public static BufferedImage playerRight; // player is facing right
	public static BufferedImage darkGrass; // darkgrass displayed on game state
	public static BufferedImage lightGrass; // light grass displayed on game
											// state
	public static BufferedImage brownGrass; // grass player walks on
	public static BufferedImage whiteGrass; // when player is standing on block,
											// this grass will appear on block
	public static BufferedImage card;
	public static BufferedImage sheet; // sprite sheet
	public static BufferedImage targetLeft; // target for left side
	public static BufferedImage targetRight; // target for right side
	public static BufferedImage arrowLeft; // arrow facing left
	public static BufferedImage arrowRight; // arrow facing right
	public static Image menuArrowGIF;
	public static Image icon;

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Images.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public static void init() {
		darkGrass = loadImage("/textures/darkgrass.png");
		lightGrass = loadImage("/textures/lightgrass.png");
		brownGrass = loadImage("/textures/browngrass.png");
		whiteGrass = loadImage("/textures/darkbrowngrass2.png");
		card = loadImage("/textures/lotterycard.jpg");
		sheet = loadImage("/textures/archersprite.png");
		targetLeft = loadImage("/textures/targetLeft.png");
		targetRight = loadImage("/textures/targetRight.png");
		arrowLeft = loadImage("/textures/arrowLeft.png");
		arrowRight = loadImage("/textures/arrowRight.png");
		menuArrowGIF = Toolkit.getDefaultToolkit().createImage(
				"/textures/menuArrow.gif");

		try {
			icon = new ImageIcon(
					new URL(
							"https://d13yacurqjgara.cloudfront.net/users/891352/screenshots/2251093/svg_bow_and_arrow_dribbble.gif"))
					.getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		playerUp = new BufferedImage[9];
		for (int i = 0; i < 9; i++) {
			playerUp[i] = sheet.getSubimage(64 * i, 64 * 8, 64, 64);
		}

		playerDown = new BufferedImage[9];
		for (int i = 0; i < 9; i++) {
			playerDown[i] = sheet.getSubimage(64 * i, 64 * 10, 64, 64);
		}

		playerArrowLeft = new BufferedImage[13];
		for (int i = 0; i < 13; i++) {
			playerArrowLeft[i] = sheet.getSubimage(64 * i, 64 * 17, 64, 64);
		}

		playerArrowRight = new BufferedImage[13];
		for (int i = 0; i < 13; i++) {
			playerArrowRight[i] = sheet.getSubimage(64 * i, 64 * 19, 64, 64);
		}

		playerLeft = sheet.getSubimage(64 * 0, 64 * 17, 64, 64);
		playerRight = sheet.getSubimage(64 * 0, 64 * 19, 64, 64);
	}
}
