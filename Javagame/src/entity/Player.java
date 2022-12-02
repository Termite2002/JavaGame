package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Katana;
import object.OBJ_Key;
import object.OBJ_Kunai;
import object.OBJ_Sword_Noob;
import object.OBJ_Shield_Noob;

public class Player extends Entity{
	
	UtilityTool uT;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
//	public int hasKey = 0;
//	public ArrayList<Entity> inventory = new ArrayList<>();
//	public final int maxInventorySize = 20;
	
	
	public Player(GamePanel gp, KeyHandler keyH, UtilityTool uT) {
		
		super(gp);														// Gọi superclass(Entity) và pass gp;
		
		this.uT = uT;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.titleSize/2);
		screenY = gp.screenHeight/2 - (gp.titleSize/2);
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
//		attackArea.width = 36;
//		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttack();
		setItems();
	}
	
	public void setDefaultValues() {
		worldX = 5*gp.titleSize;
		worldY = 44*gp.titleSize;
		speed = 4;
		direction = "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 12;
		life = maxLife;
		coin = 200;
		projectile = new OBJ_Kunai(gp);
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		currentWeapon = new OBJ_Sword_Noob(gp);
		currentShield = new OBJ_Shield_Noob(gp);
		attack = getAttack();
		defense = getDefense();
		
	}
	public void setDefaultPositions() {
		worldX = 6*gp.titleSize;
		worldY = 44*gp.titleSize;
		direction = "down";
	}
	public void restoreLifeandMana() {
		life = maxLife;
		invinsible = false;
	}
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));

	}
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength + currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity + currentShield.defenseValue;
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
	public void getPlayerAttack() {
		
		attackUp = setup("/nin/nin_atk_up");
		attackDown = setup("/nin/nin_atk_down");
		attackLeft = setup("/nin/nin_atk_left");
		attackRight = setup("/nin/nin_atk_right");
		attackUp = UtilityTool.scaleImage(attackUp, gp.titleSize, gp.titleSize*2);
		attackDown = UtilityTool.scaleImage(attackDown, gp.titleSize, gp.titleSize*2);
		attackLeft = UtilityTool.scaleImage(attackLeft, gp.titleSize*2, gp.titleSize);
		attackRight = UtilityTool.scaleImage(attackRight, gp.titleSize*2, gp.titleSize);
		
	}
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
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
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			// CHECK EVENT
			gp.eHandler.checkEvent();

			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			if(keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(8);
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
			gp.keyH.enterPressed = false;
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
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false) {
			projectile.set(worldX, worldY, direction, true, this);
			
			gp.projectileList.add(projectile);
			gp.playSE(5);
		}
		
		if(invinsible == true) {
			invinsibleCounter++;
			if(invinsibleCounter > 35) {
				invinsible = false;
				invinsibleCounter = 0;
			}
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			gp.playSE(18);
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
			// Check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			
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
	public void pickUpObject(int i) {
		if (i != 999) {
			
			// PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			// obstacle
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			// INVENTORY ITEMS
			else {

				String text = "";
				String objectName;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					objectName = gp.obj[gp.currentMap][i].name;
					switch(objectName) {
					case "Key":
						gp.playSE(1);
						text = "Nhặt được " + gp.obj[gp.currentMap][i].name;
						break;
					case "Katana":
						gp.playSE(1);
						text = "Nhặt được " + gp.obj[gp.currentMap][i].name;
						break;
					case "Iron Shield":
						gp.playSE(1);
						text = "Nhặt được " + gp.obj[gp.currentMap][i].name;
						break;
					case "Blue Shield":
						gp.playSE(1);
						text = "Nhặt được " + gp.obj[gp.currentMap][i].name;
						break;
					case "Sushi":
						gp.playSE(1);
						text = "Nhặt được " + gp.obj[gp.currentMap][i].name;
						break;
					}
				}
				else {
					text = "Túi đồ đã đầy, không thể nhặt thêm";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
			
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
		
		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				attackCanceled = true;
				if(gp.npc[gp.currentMap][i].name == "master") {
					gp.face = gp.suphu;
				}
				else if(gp.npc[gp.currentMap][i].name == "trademan") {
					gp.face = gp.tg;
				}
				gp.appearAvatar = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
//			else {
//				gp.playSE(8);
//				attacking = true;
//			}
		}
		

	}
	public void contactMonster(int i) {
		
		if(i != 999) {
			
			if(invinsible == false && gp.monster[gp.currentMap][i].dying == false)  {
				gp.playSE(7);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invinsible = true;
			}
			
		}
		else {
			
		}
	}
	public void damageMonster(int i) {
		if(i != 999) {
			
			if(gp.monster[gp.currentMap][i].invinsible == false) {
				gp.playSE(9);
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[gp.currentMap][i].invinsible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.playSE(10);
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
		else {
			System.out.println("Miss");
		}
	}
	public void checkLevelUp() {
		if(exp >= nextLevelExp) {
			level++;
			if(level > 5) {
				nextLevelExp += nextLevelExp/2;
			}
			else {
				nextLevelExp = nextLevelExp*2;
			}
			if(maxLife > life) {
				life += (maxLife - life);
			}
			if(level == 3 || level == 7) {
				speed++;
			}
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(17);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Bạn đã lên cấp " + level + " !\n" 
					+ "* Bạn tràn trề sự quyết tâm!!! *";
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sw_noob || selectedItem.type == type_katana) {
				currentWeapon = selectedItem;
				attack = getAttack();
			}
			if(selectedItem.type == type_woodenshield || selectedItem.type == type_ironshield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					}
					else {
						inventory.remove(itemIndex);
					}
				}
			}
		}
	}
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		// Check if stackable
		if(item.stackable == true) {
			int index = searchItemInInventory(item.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else {
				if(inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		else {
			if(inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
	}
	public void draw(Graphics2D g2) {

		
		BufferedImage image = null;
		int x = screenX;
		int y = screenY;
		boolean mod_img_up = false;
		boolean mod_img_left = false;
		
		switch(direction) {
		case "up":
			if(attacking == false ) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 0) {image = up0;}
				if(spriteNum == 3) {image = up3;}
			}
			if(attacking == true) {
				mod_img_up = true;
				if(spriteNum == 1) {image = attackUp;}
				if(spriteNum == 2) {image = attackUp;}
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
				if(spriteNum == 1) {image = attackDown;}
				if(spriteNum == 2) {image = attackDown;}
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
				if(spriteNum == 1) {image = attackLeft;}
				if(spriteNum == 2) {image = attackLeft;}
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
				if(spriteNum == 1) {image = attackRight;}
				if(spriteNum == 2) {image = attackRight;}
			}
			break;
		}
		
		
		
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
		
		if(mod_img_up == true) {
			y = y - gp.titleSize;
			mod_img_up = false;
		}
		if(mod_img_left == true) {
			x = x - gp.titleSize;
			mod_img_left = false;
		}
		
		if(invinsible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65f));
		}
		g2.drawImage(image, x, y, null);
		
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// Debug
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invin: " + invinsibleCounter, 10,400);
	}
	
}
