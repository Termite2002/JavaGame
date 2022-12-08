package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

// Entity class là lớp bố mẹ chứa các biến sử dụng cho các class con như player, NPC, monster...
public class Entity {
	GamePanel gp;
	
	public BufferedImage up0, up1, up2, up3, down0, down1, down2, down3, left0, left1, left2, left3, right0, right1, right2, right3, ava;   // Biến này lưu trữ hình ảnh
	public BufferedImage attackUp1, attackDown1, attackLeft1, attackRight1, attackUp2, attackDown2, attackLeft2, attackRight2;
	public BufferedImage image, image2, image3, image4, image5;
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	public boolean collisionOn = false;
	String dialogues[] = new String[20];
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Projectile projectile;
	public Entity attacker;

	// STATE
	public int worldX, worldY;
	public String direction = "down";
	int dialogueIndex = 0;
	public boolean invinsible = false;
	public int spriteNum = 0;
	public boolean collision = false;
	boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	
	// COUNTER
	public int actionLockCounter = 0;
	int dyingCounter = 0;
	public int invinsibleCounter = 0;
	public int spriteCounter = 0;
	int hpBarCounter = 0;
	int knockBackCounter = 0;
	
		
	// CHARACTER STATUS
	public String name;
	public int speed;
	public int defaultSpeed;
	public int currentSpeed;
	public boolean attacking = false;
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
	public int knockBackPower = 0;
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
	public final int type_bluesword = 11;
	public final int type_goldsword = 12;
	public final int type_hellsword = 13;
	public final int type_goldshield = 14;
	public final int type_hellshield = 15;
	public final int type_godshield = 16;
	public final int type_godsword = 17;
	
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
	public int getXdistance(Entity target) {
		int xDistance = Math.abs(worldX - target.worldX);
		return xDistance;
	}
	public int getYdistance(Entity target) {
		int yDistance = Math.abs(worldY - target.worldY);
		return yDistance;
	}
	public int getTileDistance(Entity target) {
		int tileDistance = (getXdistance(target) + getYdistance(target))/gp.titleSize;
		return tileDistance;
	}
	public int getGoalCol(Entity target) {
		int goalCol = (target.worldX + target.solidArea.x)/gp.titleSize;
		return goalCol;
	}
	public int getGoalRow(Entity target) {
		int goalRow = (target.worldY + target.solidArea.y)/gp.titleSize;
		return goalRow;
	}
	public void damageReaction() {
		
	}
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
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
	public void checkCollision() {
		
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
	}
	public void update() {
	
		if(knockBack == true) {
			
			checkCollision();
			if(collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else if(collisionOn == false) {
				switch(knockBackDirection) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			knockBackCounter++;
			if(knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		else if(attacking == true) {
			attacking();
			System.out.print("yep ");
		}
		else {
			setAction();
			checkCollision();

			// IF COLLISION IS FALSE, CAN MOVE
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

		

		
		if(invinsible == true) {
			invinsibleCounter++;
			if(invinsibleCounter > 25) {
				invinsible = false;
				invinsibleCounter = 0;
			}
		}
	}
	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		
		boolean targetInRange = false;
		int xDis = getXdistance(gp.player);
		int yDis = getYdistance(gp.player);
		
		switch(direction) {
		case "up":
			if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "down":
			if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "left":
			if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "right":
			if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		if(targetInRange == true) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
			}
		}
	}
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		
		if(getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = true;
			}
		}
	}
	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		
		if(getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				onPath = false;
			}
		}
	}
	public void getRandomDirection() {
		actionLockCounter++;
		
		if(actionLockCounter == 50) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {direction = "up";}
			if(i > 25 && i <= 50) {direction = "down";}
			if(i > 50 && i <= 75) {direction = "left";}
			if(i > 75 && i <= 100) {direction = "right";}
			
			actionLockCounter = 0;
		}
	}
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			// attackArea becomes sollidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			if(type == type_monster) {
				if(gp.cChecker.checkPlayer(this) == true) {
					damagePlayer(attack);
				}
			}
			else {
				// Check monster collision
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				gp.player.damageMonster(monsterIndex,this, currentWeapon.knockBackPower);
			}


			
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.width = solidAreaHeight;
			
			
			
			
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	public void damagePlayer(int attack) {
		if(gp.player.invinsible == false) {
			gp.playSE(24);
			int damage = attack - gp.player.defense;
			if(damage < 0) {
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invinsible = true;
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
			
//			switch(direction) {
//			case "up":
//				if(spriteNum == 1) {
//					image = up1;
//				}
//				if(spriteNum == 2) {
//					image = up2;
//				}
//				if(spriteNum == 0) {
//					image = up0;
//				}
//				if(spriteNum == 3) {
//					image = up3;
//				}
//				break;
//			case "down":
//				if(spriteNum == 1) {
//					image = down1;
//				}
//				if(spriteNum == 2) {
//					image = down2;
//				}
//				if(spriteNum == 0) {
//					image = down0;
//				}
//				if(spriteNum == 3) {
//					image = down3;
//				}
//				break;
//			case "left":
//				if(spriteNum == 1) {
//					image = left1;
//				}
//				if(spriteNum == 2) {
//					image = left2;
//				}
//				if(spriteNum == 0) {
//					image = left0;
//				}
//				if(spriteNum == 3) {
//					image = left3;
//				}
//				break;
//			case "right":
//				if(spriteNum == 1) {
//					image = right1;
//				}
//				if(spriteNum == 2) {
//					image = right2;
//				}
//				if(spriteNum == 0) {
//					image = right0;
//				}
//				if(spriteNum == 3) {
//					image = right3;
//				}
//				break;
//			}
			int x = screenX;
			int y = screenY;
			boolean mod_img_up = false;
			boolean mod_img_left = false;
			
			switch(direction) {
			case "up":
				if(attacking == false) {
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
					if(spriteNum == 0) {image = up0;}
					if(spriteNum == 3) {image = up3;}
				}
				if(attacking == true) {
					mod_img_up = true;
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
				}
				break;
			case "down":
				if(attacking == false) {
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
					if(spriteNum == 0) {image = down0;}
					if(spriteNum == 3) {image = down3;}
				}
				if(attacking == true) {
					if(spriteNum == 1) {image = attackDown1;}
					if(spriteNum == 2) {image = attackDown2;}
				}
				break;
			case "left":
				if(attacking == false) {
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
					if(spriteNum == 0) {image = left0;}
					if(spriteNum == 3) {image = left3;}
				}
				if(attacking == true) {
					mod_img_left = true;
					if(spriteNum == 1) {image = attackLeft1;}
					if(spriteNum == 2) {image = attackLeft2;}
				}
				break;
			case "right":
				if(attacking == false) {
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
					if(spriteNum == 0) {image = right0;}
					if(spriteNum == 3) {image = right3;}				
				}
				if(attacking == true) {
					if(spriteNum == 1) {image = attackRight1;}
					if(spriteNum == 2) {image = attackRight2;}
				}
				break;
			}
			if(mod_img_up == true) {
				y = y - gp.titleSize;
				mod_img_up = false;
			}
			if(mod_img_left == true) {
				x = x - gp.titleSize;
				mod_img_left = false;
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
			g2.drawImage(image, x, y,  null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//		}
		 if(gp.player.worldX < gp.player.screenX ||
			    gp.player.worldY < gp.player.screenY ||
			    rightOffset > gp.worldWidth - gp.player.worldX ||
			    bottomOffset > gp.worldHeight - gp.player.worldY) {
			g2.drawImage(image, x, y, null); 
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
		case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
		case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
		case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
		case "right": nextWorldX = user.getRightX()+gp.player.speed; break;
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
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.titleSize;
		int startRow = (worldY + solidArea.y)/gp.titleSize;;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if(gp.pFinder.search() == true) {
			// Next 
			int nextX = gp.pFinder.pathList.get(0).col * gp.titleSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.titleSize;
			
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.titleSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.titleSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.titleSize) {
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}
}
