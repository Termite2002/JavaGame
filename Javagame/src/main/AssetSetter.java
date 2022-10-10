package main;

import entity.NPC_Master;
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
		

	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Master(gp);
		gp.npc[0].worldX = gp.titleSize * 15;
		gp.npc[0].worldY = gp.titleSize * 20;
	}
}
