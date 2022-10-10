package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

// Entity class là lớp bố mẹ chứa các biến sử dụng cho các class con như player, NPC, monster...
public class Entity {
	GamePanel gp;

	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up0, up1, up2, up3, down0, down1, down2, down3, left0, left1, left2, left3, right0, right1, right2, right3, ava;   // Biến này lưu trữ hình ảnh
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "right":
			direction = "left";
			break;
		case "left":
			direction = "right";
			break;
		case "down":
			direction = "up";
			break;
		}
	}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
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

	public BufferedImage setup(String imagePath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));

		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		  // STOP MOVING CAMERA
	
		
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			
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
			
			g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
		}
	}
}
