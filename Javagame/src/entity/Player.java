package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
//	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);														// Gọi superclass(Entity) và pass gp;
		

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.titleSize/2);
		screenY = gp.screenHeight/2 - (gp.titleSize/2);
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = 25*gp.titleSize;
		worldY = 25*gp.titleSize;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
			
			ava = setup("/nin/Playerava");
			up0 = setup("/nin/up0");
			up1 = setup("/nin/up1");
			up2 = setup("/nin/up2");
			up3 = setup("/nin/up3");
			down0 = setup("/nin/down0");
			down1 = setup("/nin/down1");
			down2 = setup("/nin/down2");
			down3 = setup("/nin/down3");
			left0 = setup("/nin/left0");
			left1 = setup("/nin/left1");
			left2 = setup("/nin/left2");
			left3 = setup("/nin/left3");
			right0 = setup("/nin/right0");
			right1 = setup("/nin/right1");
			right2 = setup("/nin/right2");
			right3 = setup("/nin/right3");
		
	}
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if (spriteNum == 0) spriteNum = 1;
				else if (spriteNum == 1) spriteNum = 2;
				else if (spriteNum == 2) spriteNum = 3;
				else if (spriteNum == 3) spriteNum = 0;
				spriteCounter = 0;
			}
		}
		else {
			spriteNum = 0;
		}
		

	}
	public void pickUpObject(int i) {
		if (i != 999) {
			
//			String objectName = gp.obj[i].name;
//			
//			switch(objectName) {
//			case "key":
//				gp.playSE(1);
//				hasKey++;
//				gp.obj[i] = null;
//				gp.ui.showMessage("You got a key!");
//				break;
//			case "door":
//				if(hasKey > 0) {
//					gp.playSE(2);
//					gp.obj[i] = null;
//					hasKey--;
//					gp.ui.showMessage("You opened the door!");
//				}
//				else {
//					gp.ui.showMessage("You need a key!");
//				}
//				break;
//			case "chest":
//				gp.stopMusic();
//				gp.playSE(5);
//				gp.obj[i] = null;
//				gp.ui.showMessage("You found the treasure!");
//				break;
//			case "boot":
//				speed += 1;
//				gp.playSE(3);
//				gp.obj[i] = null;
//				gp.ui.showMessage("Speed up!");
//				break;
//			}
//			
//			
		}
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	public void draw(Graphics2D g2) {

		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 0) {
				image = up0;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 0) {
				image = down0;
			}
			if(spriteNum == 3) {
				image = down3;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 0) {
				image = left0;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 0) {
				image = right0;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			break;
		}
		
		int x = screenX;
		int y = screenY;
		
		if(screenX > worldX) {
			x = worldX;
		}
		if(screenY > worldY) {
			y = worldY;
		}
		int rightOffset = gp.screenWidth - screenX;
		if(rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - screenY;
		if(bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		
		g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
	}
	
}
