package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import entity.NPC_Master;
import entity.NPC_Merchant;
import object.Logo;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font MaruMonica, PurisaB, nando;

	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public Entity npc;
	BufferedImage coin, face, face_Master, face_trademan, heart_full, heart_34, heart_half, heart_14, heart_empty;
	
	boolean appearAvatar;
	
	public String currentDialogue = "";
	public int commandNum = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	public int counter = 0;
	
	public UI(GamePanel gp, boolean appearAvatar) {
		
		this.gp = gp;
		this.appearAvatar = appearAvatar;
		
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
		Entity money = new OBJ_Coin(gp);
		coin = money.down1;
		
		// Get face
		NPC_Master master = new NPC_Master(gp);
		face_Master = master.ava;
		NPC_Merchant trademan = new NPC_Merchant(gp);
		face_trademan = trademan.ava;

	}
	public void addMessage(String text) {
		
//		message = text;
//		messageOn = true;
		
		message.add(text);
		messageCounter.add(0);
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
			drawMessage();
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
			if(gp.face == gp.suphu) face = face_Master;
			else if(gp.face == gp.tg) face = face_trademan;
			if(appearAvatar == true) g2.drawImage(face, gp.titleSize + 60, gp.titleSize, 60, 60, null);
		}
		// CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		// INSTRUCTION STATE
		if(gp.gameState == gp.instructionState) {
			drawInstruction();
		}
		// OPTION STATE
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		// GAME OVER STATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		// TRADE STATE
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		// END STATE
		if(gp.gameState == gp.endState) {
			endScreen();
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
	
	public void drawMessage() {
		int messageX = gp.titleSize;
		int messageY = gp.titleSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		
		for(int i = 0; i < message.size(); i++) {
			
			if(message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.cyan);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 150) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
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
		if(gp.face == gp.suphu) face = face_Master;
		else if(gp.face == gp.tg) face = face_trademan;
		if(gp.appearAvatar == true) g2.drawImage(face, gp.titleSize + 60, gp.titleSize, 60, 60, null);
	}
	
	public void drawCharacterScreen() {
		Entity heart = new OBJ_Heart(gp);
		Entity log = new Logo(gp);
		// Create a frame
		final int posX = gp.titleSize*2;
		final int posY = gp.titleSize;
		final int Width = gp.titleSize*7;
		final int Height = gp.titleSize*10;
		drawSubWindow(posX, posY, Width, Height);
		
		// Create text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		int textX = posX + 20;
		int textY = posY + gp.titleSize;
		final int lineHeight = 32;
		
		// Names
		g2.drawString("Level", textX, textY);
		g2.drawImage(log.up0, textX + 70, textY - 28, null);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		g2.drawImage(heart.image, textX + 50, textY - 15, null);
		textY += lineHeight;
		g2.drawString("Speed", textX, textY);
		g2.drawImage(log.left0, textX + 85, textY - 28, null);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		g2.drawImage(log.up1, textX + 120, textY - 28, null);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		g2.drawImage(log.up2, textX + 120, textY - 28, null);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		g2.drawImage(log.up3, textX + 90, textY - 25, null);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		g2.drawImage(log.down0, textX + 105, textY - 25, null);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		g2.drawImage(log.down1, textX + 50, textY - 25, null);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		g2.drawImage(log.down2, textX + 140, textY - 27, null);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		g2.drawImage(log.down3, textX + 60, textY - 28, null);
		textY += lineHeight + 25;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 20 + 20;
		g2.drawString("Shield", textX, textY-10);
		
		// Values
		int tailX = (posX + Width) - 30;
		textY = posY + gp.titleSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.speed);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp + "/" + gp.player.nextLevelExp);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.titleSize, textY - 15, null);
		textY += gp.titleSize + 10;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.titleSize, textY - 10, null);
	}
	public void drawInventory(Entity entity, boolean cursor) {
		
		int posX = 0;
		int posY = 0;
		int Width = 0;
		int Height = 0;
		int slotCol = 0;
		int slotRow = 0;
		if(entity == gp.player) {
			posX = gp.titleSize*16;
			posY = gp.titleSize;
			Width = gp.titleSize*6;
			Height = gp.titleSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			posX = gp.titleSize*2;
			posY = gp.titleSize;
			Width = gp.titleSize*6;
			Height = gp.titleSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		// FRAME

		drawSubWindow(posX, posY, Width, Height);
		
		// SLOT
		final int slotXstart = posX + 20;
		final int slotYstart = posY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.titleSize + 3;
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i < entity.inventory.size(); i++) {
			
			// EQIP CURSOR
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.titleSize+1, gp.titleSize+1, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			// DISPLAY AMOUNT
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(15f));
				int amountX;
				int amountY;
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignRight(s, slotX+49);
				amountY = slotY + gp.titleSize;
				
				//Shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				// Num
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		// CURSOR
		if(cursor == true) {
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.titleSize;
			int cursorHeight = gp.titleSize;
			
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(2));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// DESCRIPTION 
			int dPosX = posX;
			int dPosY = posY + Height + 15;
			int dWidth = Width;
			int dHeight = gp.titleSize*4;
			
			// DRAW DESCRIPTION
			int textX = dPosX + 20;
			int textY = dPosY + 30;
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if(itemIndex < entity.inventory.size()) {
				
				drawSubWindow(dPosX, dPosY, dWidth, dHeight);
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 25;
				}
			}
			
		}

 		
	}
	
	public void drawInstruction() {
		

		
		// FRAME
		int x = gp.titleSize*2;
		int y = gp.titleSize;
		int width = gp.titleSize * 18;
		int height = gp.titleSize * 11;
		drawSubWindow(x, y, width, height);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		
		String text = "Hướng dẫn";
		int X = getXforCenterText(text);
		int Y = y + gp.titleSize;
		g2.drawString(text, X - 2*gp.titleSize, Y+10);
		
		
		// TEXT
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		int textX = x + 20;
		int textY = y + gp.titleSize*2;
		final int lineHeight = 40;
		
		g2.drawString("+ Sử dụng W,A,S,D để di chuyển", textX, textY);
		textY += lineHeight;
		g2.drawString("+ Phím Enter để tương tác, nói chuyện, tấn công", textX, textY);
		textY += lineHeight;
		g2.drawString("+ Ấn phím C để mở bảng trạng thái nhân vật và túi đồ", textX, textY);
		textY += lineHeight;
		g2.drawString("+ Ấn phím O để mở Option", textX, textY);
		textY += lineHeight;
		g2.drawString("+ Ấn phím ESC để pause game", textX, textY);
		textY += lineHeight;
		g2.drawString("* Sát thương gây ra = sức mạnh của bạn + chỉ số vũ khí *", textX, textY);
		textY += lineHeight;
		g2.drawString("* Phòng thủ = chỉ số của bạn + chỉ số khiên *", textX, textY);
		textY += lineHeight;
		g2.drawString("* Đánh quái để cày cấp, có tiền mua trang bị và vật phẩm *", textX, textY);
		textY += lineHeight;
		g2.drawString("* Khi có đồ mạnh, nhớ trang bị để sử dụng!!! *", textX, textY);
		textY += lineHeight;
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {

		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
		
	}
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(28f));
		
		int x = gp.titleSize*7;
		int y = gp.titleSize*2;
		int w = gp.titleSize*11;
		int h = gp.titleSize*10;
		drawSubWindow(x, y, w, h);
		
		switch(subState) {
		case 0: option_top(x,y); break;
		case 1: option_endGameConfirmation(x, y); break;
		case 2: break;
		}
		
	}
	public void option_top(int x, int y) {
		int textX;
		int textY;
		
		//TITLE
		String text = "Cài đặt";
		textX = getXforCenterText(text);
		textY = y + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(20f));
		
		// Music
		textX = x + gp.titleSize;
		textY += gp.titleSize*2;
		g2.drawString("Nhạc nền", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.keyH.enterPressed = false;
			}
		}

		
		// SE
		textY += gp.titleSize;
		g2.drawString("Hiệu ứng âm thanh", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.keyH.enterPressed = false;
			}
		}
		
		// End game
		textY += gp.titleSize;
		g2.drawString("Thoát game", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}
//		// BACK
//		textY += gp.titleSize*4;
//		g2.drawString("Trở lại", textX, textY);
//		if(commandNum == 3) {
//			g2.drawString(">", textX-25, textY);
//			if(gp.keyH.enterPressed == true) {
//				subState = 1;
//				commandNum = 0;
//			}
//		}
	
		// volume music box
		textX = x + gp.titleSize*6 + 15;
		textY = y + gp.titleSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 130, 25);
		int volumeWidth = 26 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 25);
		
		// volume se box
		textY += gp.titleSize;
		g2.drawRect(textX, textY, 130, 25);
		volumeWidth = 26 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 25);
		
	}
	public void option_endGameConfirmation(int x, int y) {
		int textX = x + gp.titleSize;
		int textY = y + gp.titleSize*3;
		
		currentDialogue = "Thoát game và\ntrở lại màn hình menu?";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Đồng ý";
		textX = getXforCenterText(text);
		textY += gp.titleSize * 3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		
		// NO
		text = "Khum";
		textX = getXforCenterText(text);
		textY += gp.titleSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 2;
			}
		}
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
		text = "Game Over";
		g2.setColor(Color.black);
		x = getXforCenterText(text);
		y = gp.titleSize*4;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenterText(text);
		y += gp.titleSize*5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-50, y);
		}
		
		// Back to menu
		text = "Quit";
		x = getXforCenterText(text);
		y += 80;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-50, y);
		}
	}
	public void drawTransition() {
		
		counter++;
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.titleSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.titleSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
		
	}
	public void trade_select() {
		
		drawDialogueScreen();
		
		// DRAW WINDOW
		int x = gp.titleSize * 19;
		int y = gp.titleSize * 4 + 35;
		int width = gp.titleSize * 3;
		int height = (int)(gp.titleSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//DRAW TEXT
		x += gp.titleSize;
		y += gp.titleSize;
		g2.drawString("Mua", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		y += gp.titleSize;
		g2.drawString("Bán", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		y += gp.titleSize;
		g2.drawString("Rời đi", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Ta hi vọng con sẽ tiếp tục ghé thăm lần sau!!!Hehe";
			}
		}
		
	}
	public void trade_buy() {
		drawInventory(gp.player, false);
		drawInventory(npc, true);
		
		// Draw hint window
		int x = gp.titleSize * 2;
		int y = gp.titleSize * 10 + 15;
		int width = gp.titleSize*6;
		int height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString(" [ECS] Quay lại", x+24, y+60);
		
		// Draw player coin
		x = gp.titleSize * 16;
		drawSubWindow(x, y, width, height);
		g2.drawString("Tiền của bạn: " + gp.player.coin + " Coin", x+24, y+60);
		
		// Draw price window
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			x = (int) (gp.titleSize*5.5);
			y = (int) (gp.titleSize*5.5);
			width = (int) (gp.titleSize*2.5);
			height = gp.titleSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+15, y+15, 21, 21, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignRight(text, gp.titleSize*8);
			g2.drawString(text, x - 15, y + 32);
			
			// Buy 
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Bạn không đủ tiền mua vật phẩm này!!!";
					drawDialogueScreen();
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						gp.gameState = gp.dialogueState;
						currentDialogue = "Túi đồ đã đầy!";
					}
				}
//				else if(gp.player.inventory.size() == gp.player.maxInventorySize) {
//					subState = 0;
//					gp.gameState = gp.dialogueState;
//					currentDialogue = "Túi đồ đã đầy!";
//				}
//				else {
//					gp.player.coin -= npc.inventory.get(itemIndex).price;
//					gp.player.inventory.add(npc.inventory.get(itemIndex));
//				}
			}
		}
		
	}
	public void trade_sell() {
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;
		
		// Draw hint window
		x = gp.titleSize * 2;
		y = gp.titleSize * 10 + 15;
		width = gp.titleSize*6;
		height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString(" [ECS] Quay lại", x+24, y+60);
		
		// Draw player coin
		x = gp.titleSize * 16;
		drawSubWindow(x, y, width, height);
		g2.drawString("Tiền của bạn: " + gp.player.coin + " Coin", x+24, y+60);
		
		// Draw price window
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			x = (int) (gp.titleSize*19.5);
			y = (int) (gp.titleSize*5.5);
			width = (int) (gp.titleSize*2.5);
			height = gp.titleSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+15, y+15, 21, 21, null);
			
			int price = (int)(gp.player.inventory.get(itemIndex).price/2);
			String text = "" + price;
			x = getXforAlignRight(text, gp.titleSize*22);
			g2.drawString(text, x - 15, y + 32);
			
			// Sell 
			if(gp.keyH.enterPressed == true) {
				
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Bạn không thể bán đồ đang trang bị!";
					
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
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
	
	public int getXforAlignRight(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	public void endScreen() {
		
		// FRAME
		int x = gp.titleSize*2-20;
		int y = gp.titleSize;
		int width = gp.titleSize * 22;
		int height = gp.titleSize * 12;
		drawSubWindow(x, y, width, height);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		
		String text = "Thành công thoát khỏi hòn đảo";
		int X = getXforCenterText(text);
		int Y = y + gp.titleSize;
		g2.drawString(text, X - gp.titleSize, Y+10);
		
		
		// TEXT
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		
		int textX = x + 20;
		int textY = y + gp.titleSize*2;
		final int lineHeight = 40;
		
		textY += 60;
		g2.drawString("Đội ngũ thực hiện:", textX+gp.titleSize*3, textY);
		textY += lineHeight;
		g2.drawString("* LEAD: Phạm Bình Nguyên", textX+30+gp.titleSize*4, textY);
		textY += lineHeight;
		g2.drawString("* Nguyễn Sỹ Hội", textX+30+gp.titleSize*4, textY);
		textY += lineHeight;
		g2.drawString("* Nguyễn Văn Đỗ Phi", textX+30+gp.titleSize*4, textY);
		textY += lineHeight;
		g2.drawString("* Trần Minh Quang", textX+30+gp.titleSize*4, textY);
		textY += lineHeight + gp.titleSize;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
		g2.drawString("Một quá trình phát triển sóng gió nhưng học được nhiều điều!!!", textX+20, textY);
		textY += lineHeight;
		g2.drawString("Tuy còn chưa hoàn thiện nhưng hi vọng trải nghiệm của bạn vui vẻ!", textX+20, textY);
		textY += lineHeight;
		g2.drawString("Cảm ơn bạn đã hoàn thành trò chơi của chúng tôi!", textX+20, textY);
		textY += lineHeight;
		g2.drawString("Dành tặng lời cảm ơn đến thầy cô, bạn bè và người thân!!!", textX+20, textY);
		textY += lineHeight;

	}
}
