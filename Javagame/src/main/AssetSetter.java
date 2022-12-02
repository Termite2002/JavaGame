package main;

import entity.NPC_Master;
import entity.NPC_Merchant;
import monster.MON_Cyclope;
import object.OBJ_BlueShield;
import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_IronShield;
import object.OBJ_Katana;
import object.OBJ_Key;
import object.OBJ_Sushi;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Coin(gp);
		gp.obj[mapNum][i].worldX = 14*gp.titleSize;
		gp.obj[mapNum][i].worldY = 43*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = 19*gp.titleSize;
		gp.obj[mapNum][i].worldY = 40*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Katana(gp);
		gp.obj[mapNum][i].worldX = 24*gp.titleSize;
		gp.obj[mapNum][i].worldY = 40*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_IronShield(gp);
		gp.obj[mapNum][i].worldX = 24*gp.titleSize;
		gp.obj[mapNum][i].worldY = 43*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Sushi(gp);
		gp.obj[mapNum][i].worldX = 30*gp.titleSize;
		gp.obj[mapNum][i].worldY = 43*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 21*gp.titleSize;
		gp.obj[mapNum][i].worldY = 44*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_BlueShield(gp);
		gp.obj[mapNum][i].worldX = 37*gp.titleSize;
		gp.obj[mapNum][i].worldY = 44*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Sushi(gp));
		gp.obj[mapNum][i].worldX = 37*gp.titleSize;
		gp.obj[mapNum][i].worldY = 39*gp.titleSize;
		i++;
	}
	
	public void setNPC() {
		
		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_Master(gp);
		gp.npc[mapNum][i].worldX = gp.titleSize * 10;
		gp.npc[mapNum][i].worldY = gp.titleSize * 45;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.titleSize * 41;
		gp.npc[mapNum][i].worldY = gp.titleSize * 41;
		i++;

	}

	public void setMonster() {
		
		int mapNum = 0;
		
		int i = 0;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*25;
		gp.monster[mapNum][i].worldY = gp.titleSize*32;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*25;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*24;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
	}
}
