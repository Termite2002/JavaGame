package tile;

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
	public int mapTileNum[][];
	ArrayList<String> fileNames = new  ArrayList<>();
	ArrayList<String> collisionStatus = new  ArrayList<>();
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
//		System.out.println("bd");
		InputStream is = getClass().getResourceAsStream("/mapdata/tidedata.txt");
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
//		System.out.println("kt");
		tile = new Tile[fileNames.size()];
		getTileImage();
		
//		is = getClass().getResourceAsStream("/maps/map101.txt");
//		br = new BufferedReader(new InputStreamReader(is));
//		try {
//			String line2 = br.readLine();
//			String maxTile[] = line2.split(" ");
//			gp.maxWorldCol = maxTile.length;
//			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
//		}catch(IOException e) {
//			System.out.println("E");
//		}
		
		
		loadMap("/maps/map101.txt");
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
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
				
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
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
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.titleSize;	
			int worldY = worldRow * gp.titleSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
			}
			worldCol++;	

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;



			}
		}
	}
}
