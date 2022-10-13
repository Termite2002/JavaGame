package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import entity.Entity;
import entity.NPC_Master;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font MaruMonica, PurisaB, nando;

	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	BufferedImage face_Master, heart_full, heart_34, heart_half, heart_14, heart_empty;
	
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			MaruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			PurisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/FVF Fernando 08.ttf");
			nando = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_34 = heart.image2;
		heart_half = heart.image3;
		heart_14 = heart.image4;
		heart_empty = heart.image5;
		
		// Get face
		NPC_Master master = new NPC_Master(gp);
		face_Master = master.ava;

	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2, boolean appearAvatar) {
		
		this.g2 = g2;
		
		g2.setFont(nando);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
			if(appearAvatar == true) g2.drawImage(face_Master, gp.titleSize + 60, gp.titleSize, 60, 60, null);
		}
		
//		g2.setFont(new Font("Arial", Font.PLAIN, 40));
//		g2.setColor(Color.white);
//		g2.drawImage(keyImage, gp.titleSize/2, gp.titleSize/2, gp.titleSize-12,gp.titleSize-20, null);
//		g2.drawString("x " + gp.player.hasKey, 70, 50);
//		
//		// MESSAGE
//		if(messageOn == true) {
//			
//			g2.setFont(g2.getFont().deriveFont(30F));
//			g2.drawString(message, gp.titleSize/2, 300);
//			
//			messageCounter++;
//			if(messageCounter > 120) {
//				messageCounter = 0;
//				messageOn = false;
//			}
//		}
	}
	
	public void drawPlayerLife() {
		
//		gp.player.life = 7;
		
		int x = gp.titleSize/2;
		int y = gp.titleSize/2;
		int i = 0;
		
		// DRAW MAX HEART
		while(i < gp.player.maxLife/4) {
			g2.drawImage(heart_empty, x, y, gp.titleSize-5, gp.titleSize-5 ,null);
			i++;
			x += gp.titleSize;
		}
		
		// RESET
		x = gp.titleSize/2;
		y = gp.titleSize/2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_14, x, y, gp.titleSize-5, gp.titleSize-5 ,null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_half, x, y,gp.titleSize-5, gp.titleSize-5 , null);
			}
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_34, x, y,gp.titleSize-5, gp.titleSize-5 , null);
			}
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y,gp.titleSize-5, gp.titleSize-5 , null);
			}
			i++;
			x += gp.titleSize;
		}
	}
	
	public void drawTitleScreen() {
		
		Color title_c = new Color(200,150,107);
		g2.setColor(new Color(74,82,112));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		String text = "Wandering Hero";
		int x = getXforCenterText(text);
		int y = gp.titleSize * 3;
		
		//SHADOW
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		// MAIN COLOR
		g2.setColor(title_c);
		g2.drawString(text, x, y);
		
		// Image
		x = gp.screenWidth/2;
		y += gp.titleSize * 2;
		g2.drawImage(gp.player.ava, x-70, y-30, gp.titleSize*3, gp.titleSize*3, null);
		
		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
		
		text = "NEW GAME";
		x = getXforCenterText(text);
		y += gp.titleSize * 4.5;
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		g2.setColor(title_c);
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.titleSize, y);
		}

		text = "LOAD GAME";
		x = getXforCenterText(text);
		y += gp.titleSize + 20;
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		g2.setColor(title_c);
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.titleSize, y);
		}

		text = "QUIT";
		x = getXforCenterText(text);
		y += gp.titleSize + 20;
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		g2.setColor(title_c);
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.titleSize, y);
		}

	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
		String text = "PAUSED";
		int x = getXforCenterText(text);
		int	y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		// Window
		int x = gp.titleSize * 2;
		int y = gp.titleSize / 2;
		int width = gp.screenWidth - (gp.titleSize * 4);
		int height = gp.titleSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += gp.titleSize;
		y += gp.titleSize;
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x + gp.titleSize, y);
			y += 40;
		}

	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXforCenterText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
