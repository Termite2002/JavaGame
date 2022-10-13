package main;

import entity.NPC_Master;
import monster.MON_Cyclope;
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
		
		gp.obj[0] = new OBJ_Door(gp);
		gp.obj[0].worldX = gp.titleSize*7;
		gp.obj[0].worldY = gp.titleSize*7;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Master(gp);
		gp.npc[0].worldX = gp.titleSize * 15;
		gp.npc[0].worldY = gp.titleSize * 20;
		

	}

	public void setMonster() {

		gp.monster[0] = new MON_Cyclope(gp);
		gp.monster[0].worldX = gp.titleSize*25;
		gp.monster[0].worldY = gp.titleSize*32;
	}
}
