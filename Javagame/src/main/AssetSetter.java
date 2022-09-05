package main;

import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 500;
		gp.obj[0].worldY = 500;
		gp.obj[0].sizeX = 16;  gp.obj[0].sizeY = 26;
		
		gp.obj[1] = new OBJ_Chest();
		gp.obj[1].worldX = 700;
		gp.obj[1].worldY = 700;
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = 600;
		gp.obj[2].worldY = 600;
		gp.obj[2].sizeX = -18;  gp.obj[2].sizeY = -10;
		
		gp.obj[3] = new OBJ_Boot();
		gp.obj[3].worldX = 1500;
		gp.obj[3].worldY = 1500;
		gp.obj[3].sizeX = 8;  gp.obj[3].sizeY = 8;
	}
}
