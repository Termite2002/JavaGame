package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	ArrayList<String> fileNames = new  ArrayList<>();
	ArrayList<String> collisionStatus = new  ArrayList<>();
	boolean drawPath = true;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;

		InputStream is = getClass().getResourceAsStream("/mapdata/tiledatabeg.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String line;
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		tile = new Tile[fileNames.size()];
		getTileImage();
		
//		is = getClass().getResourceAsStream("/maps/map101.txt");
//		br = new BufferedReader(new InputStreamReader(is));
//		try {
//			String line2 = br.readLine();
//			String maxTile[] = line2.split(" ");
//			gp.maxWorldCol = maxTile.length;
//			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//		}catch(IOException e) {
//			System.out.println("E");
//		}
		
		
		loadMap("/maps/mapbeg.txt", 0);
		loadMap("/maps/dungeon0.txt", 1);
	}
	
	public void getTileImage() {
			
		for(int i = 0; i < fileNames.size(); i++) {
			String fileName;
			boolean collision;
			
			fileName = fileNames.get(i);
			if (collisionStatus.get(i).equals("true")) {
				collision = true;
			}else {
				collision = false;
			}
			
			setup(i, fileName, collision);
		}

//			setup(0, "tile096", false);
//			setup(1, "tile088", false);
//			setup(2, "tile089", true);

			
	}
	public void setup(int index, String imageName, boolean collision) {
		
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/beg/" + imageName));
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath, int map) {
				
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");										// tách chuỗi
					
					int num = Integer.parseInt(numbers[col]);								// int to string
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
			
		}catch(IOException e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.titleSize;	
			int worldY = worldRow * gp.titleSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			// Stop moving the camera at the edge
			if(gp.player.screenX > gp.player.worldX) {
				screenX = worldX;
			}
			if(gp.player.screenY > gp.player.worldY) {
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
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			}
			else if(gp.player.screenX > gp.player.worldX ||
					gp.player.screenY > gp.player.worldY ||
					rightOffset > gp.worldWidth - gp.player.worldX ||
					bottomOffset > gp.worldHeight - gp.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			}
			worldCol++;	

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;



			}
		}
		if(drawPath == true) {
			g2.setColor(new Color(255, 0 , 0, 70));
			
			for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
				int worldX = gp.pFinder.pathList.get(i).col * gp.titleSize;	
				int worldY = gp.pFinder.pathList.get(i).row * gp.titleSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				if(gp.player.screenX > gp.player.worldX) {
					screenX = worldX;
				}
				if(gp.player.screenY > gp.player.worldY) {
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
					g2.fillRect(screenX, screenY, gp.titleSize, gp.titleSize);
				}
				else if(gp.player.screenX > gp.player.worldX ||
						gp.player.screenY > gp.player.worldY ||
						rightOffset > gp.worldWidth - gp.player.worldX ||
						bottomOffset > gp.worldHeight - gp.player.worldY) {
					g2.fillRect(screenX, screenY, gp.titleSize, gp.titleSize);
				}
			}
		}
	}
	
}
