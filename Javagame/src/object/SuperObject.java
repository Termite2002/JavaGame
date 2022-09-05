package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	
	public BufferedImage image;
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
		
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.titleSize-sizeX, gp.titleSize-sizeY, null);
		}
	}
}
