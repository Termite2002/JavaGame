package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	
	public BufferedImage image, image2, image3, image4, image5;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public int sizeX = 0, sizeY = 0;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = solidArea.x;
	public int solidAreaDefaultY = solidArea.y;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
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
		
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.titleSize-sizeX, gp.titleSize-sizeY, null);
		}
		  // If player is around the edge, draw everything
		  else if(gp.player.worldX < gp.player.screenX ||
		    gp.player.worldY < gp.player.screenY ||
		    rightOffset > gp.worldWidth - gp.player.worldX ||
		    bottomOffset > gp.worldHeight - gp.player.worldY) {
		   g2.drawImage(image, 2*screenX, 2*screenY, gp.titleSize-sizeX, gp.titleSize-sizeY, null); 
		  }  
	}
}
