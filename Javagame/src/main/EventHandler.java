package main;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		

	}
	
	public void checkEvent() {

		// Check if the character is move away 1 tile from last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.titleSize) {
			canTouchEvent = true;
		}

		if(canTouchEvent == true) {
			if(hit(0,6,43,"up") == true) {damagePit(gp.dialogueState);}
			else if(hit(0,8,5,"up") == true) {teleport(1,33,48);}
			else if(hit(1,33,48,"down") == true) {teleport(0,8,5);}
			else if(hit(0,20,38,"up") == true) {teleport1(0,20,36);}
			else if(hit(0,20,36,"down") == true) {teleport1(0,20,38);}
			else if(hit(0,47,29, "any") == true || hit(0,46,29, "any") == true) {endGame(gp.endState);}
			else if(hit(1,25,2,"up") == true) {activateEnd(gp.dialogueState);}
		}
	}
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.titleSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.titleSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;

				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}

		
		return hit;
	}
	public void activateEnd(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.active = true;
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Hòn đảo rung lắc dữ dội, dường như bạn đã giải phóng một nguồn năng \nlượng. Đã đến lúc rời hòn đảo này rồi!";
		}
	}
	public void endGame(int gameState) {
		if(gp.keyH.enterPressed == true) {
			if(gp.active == true) {
				gp.stopMusic();
				gp.playMusic(22);
				gp.gameState = gameState;
			}
			else {
				gp.gameState = gp.dialogueState;
				gp.ui.currentDialogue = "Bạn chưa phong ấn nguồn năng lượng, chưa thể khởi hành!";
			}
		}
	}
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Bạn vào lều để nghỉ ngơi\n* Những nỗi lo tạm thời biến mất *";
//		gp.player.life -= 1;
//		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
		gp.aSetter.setMonster();
	}
	
	public void teleport(int map, int col, int row) {
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		
		canTouchEvent = false;
		gp.stopMusic();
		gp.playSE(19);
		if(tempMap == 1 && tempCol == 33 && tempRow == 48) {
			gp.playMusic(21);
		}
		else if(tempMap == 0 && tempCol == 8 && tempRow == 5) {
			gp.playMusic(20);
		}
	}
	public void teleport1(int map, int col, int row) {
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		
		canTouchEvent = false;
		gp.stopMusic();
		gp.playSE(19);
		if(tempCol == 20 && tempRow == 38) {
			gp.playMusic(15);
		}
		else if (tempCol == 20 && tempRow == 36) {
			gp.playMusic(20);
		}
	}
}
