package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

// Entity class là lớp bố mẹ chứa các biến sử dụng cho các class con như player, NPC, monster...
public class Entity {
	GamePanel gp;
	
	public BufferedImage up0, up1, up2, up3, down0, down1, down2, down3, left0, left1, left2, left3, right0, right1, right2, right3, ava;   // Biến này lưu trữ hình ảnh
	public BufferedImage attackUp, attackDown, attackLeft, attackRight;
	public BufferedImage image, image2, image3, image4, image5;
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public boolean collisionOn = false;
	String dialogues[] = new String[20];
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Projectile projectile;

	// STATE
	public int worldX, worldY;
	public int speed;
	public String direction = "down";
	int dialogueIndex = 0;
	public boolean invinsible = false;
	public int spriteNum = 0;
	public boolean collision = false;
	boolean hpBarOn = false;
	
	// COUNTER
	public int actionLockCounter = 0;
	int dyingCounter = 0;
	public int invinsibleCounter = 0;
	public int spriteCounter = 0;
	int hpBarCounter = 0;
	
		
	// CHARACTER STATUS
	public String name;
	boolean attacking = false;
	public int maxLife;
	public int life;
	public boolean alive = true;
	public boolean dying = false;
	public int level;
	public int coin;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public Entity currentWeapon;
	public Entity currentShield;
	
	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int price;
	public boolean stackable = false;
	public int amount = 1;
	// TYPE
	public int type; // 0 = player, 1 = npc, 2 = monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sw_noob = 3;
	public final int type_katana = 4;
	public final int type_woodenshield = 5;
	public final int type_ironshield = 6;
	public final int type_consumable = 7;
	public final int type_pickupOnly = 8;
	public final int type_obstacle = 9;
	public final int type_blueshield = 10;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	public int getTopY() {
		return worldY + solidArea.y;
	}
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	public int getCol() {
		return (worldX + solidArea.x)/gp.titleSize;
	}
	public int getRow() {
		return (worldY + solidArea.y)/gp.titleSize;
	}
	public void damageReaction() {
		
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
	public void interact() {
		
	}
	public boolean use(Entity entity) {
		return false;
	}
	public void checkDrop() {
		
	}
	public void dropItem(Entity droppedItem) {
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
	public void update() {
	
	
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true) {
			if(gp.player.invinsible == false) {
				// receive damage
				gp.player.life -= 1;
				gp.player.invinsible = true;
			}
		}
		
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
		
		if(invinsible == true) {
			invinsibleCounter++;
			if(invinsibleCounter > 25) {
				invinsible = false;
				invinsibleCounter = 0;
			}
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
		  if(gp.player.worldX < gp.player.screenX) {
		   screenX = worldX;
		  }
		  if(gp.player.worldY < gp.player.screenY) {
		   screenY = worldY;
		  }   
		  int rightOffset = gp.screenWidth - gp.player.screenX;      
		  if(rightOffset > gp.worldWidth - gp.player.worldX) {
		   screenX = gp.screenWidth - (gp.worldWidth - worldX);
		  } 
		  int bottomOffset = gp.screenHeight - gp.player.screenY;
		  if(bottomOffset > gp.worldHeight - gp.player.worldY) {
		   screenY = gp.screenHeight - (gp.worldHeight - worldY);
		  }
	
		
//		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
//		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
//		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
//		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			
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
			
			// Monster health bar
			if(type == 2 && hpBarOn == true) {
				
				double oneScale = (double)gp.titleSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-16, gp.titleSize+2, 12);
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX, screenY-15, (int)hpBarValue, 10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 500) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			
			if(invinsible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			g2.drawImage(image, screenX, screenY,  null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//		}
		 if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.worldWidth - gp.player.worldX ||
			    bottomOffset > gp.worldHeight - gp.player.worldY) {
			g2.drawImage(image, screenX, screenY, null); 
		}
	}
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		if(dyingCounter <= 5) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 5 && dyingCounter <= 10) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 10 && dyingCounter <= 15) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 15 && dyingCounter <= 20) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 20 && dyingCounter <= 25) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 25 && dyingCounter <= 30) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 30 && dyingCounter <= 35) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
		}
		if(dyingCounter > 35 && dyingCounter <= 40) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
		if(dyingCounter > 40) {
			alive = false;
		}
	}
	public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;
		
		// Check surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY()-1; break;
		case "down": nextWorldY = user.getBottomY()+1; break;
		case "left": nextWorldX = user.getLeftX()-1; break;
		case "right": nextWorldX = user.getRightX()+1; break;
		}
		int col = nextWorldX/gp.titleSize;
		int row = nextWorldY/gp.titleSize;
		
		for(int i = 0; i < target[1].length; i++) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col &&
						target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetName)){
					index = i;
					break;
				}
			}
		}
		return index;
	}
}
