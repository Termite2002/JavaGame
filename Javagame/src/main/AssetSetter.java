package main;

import entity.NPC_Master;
import entity.NPC_Merchant;
import monster.MON_Bat;
import monster.MON_Beast;
import monster.MON_Cyclope;
import monster.MON_MadOrc;
import monster.MON_Orc;
import object.OBJ_BigPotion;
import object.OBJ_BlueShield;
import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_End;
import object.OBJ_GodShield;
import object.OBJ_GodSword;
import object.OBJ_IronShield;
import object.OBJ_Katana;
import object.OBJ_Key;
import object.OBJ_LotCoin;
import object.OBJ_MediumPotion;
import object.OBJ_SmallPotion;
import object.OBJ_SomeCoin;
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
		
		
		gp.obj[mapNum][i] = new OBJ_SomeCoin(gp);
		gp.obj[mapNum][i].worldX = 24*gp.titleSize;
		gp.obj[mapNum][i].worldY = 40*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 24*gp.titleSize;
		gp.obj[mapNum][i].worldY = 43*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Sushi(gp);
		gp.obj[mapNum][i].worldX = 30*gp.titleSize;
		gp.obj[mapNum][i].worldY = 43*gp.titleSize;
		i++;
		
		
		gp.obj[mapNum][i] = new OBJ_BigPotion(gp);
		gp.obj[mapNum][i].worldX = 37*gp.titleSize;
		gp.obj[mapNum][i].worldY = 44*gp.titleSize;
		i++;
		
		
		
		mapNum++;
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 29*gp.titleSize;
		gp.obj[mapNum][i].worldY = 1*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp,new OBJ_GodSword(gp));
		gp.obj[mapNum][i].worldX = 46*gp.titleSize;
		gp.obj[mapNum][i].worldY = 10*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp,new OBJ_GodShield(gp));
		gp.obj[mapNum][i].worldX = 16*gp.titleSize;
		gp.obj[mapNum][i].worldY = 2*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = 9*gp.titleSize;
		gp.obj[mapNum][i].worldY = 16*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_End(gp);
		gp.obj[mapNum][i].worldX = 25*gp.titleSize;
		gp.obj[mapNum][i].worldY = 1*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 2*gp.titleSize;
		gp.obj[mapNum][i].worldY = 45*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 3*gp.titleSize;
		gp.obj[mapNum][i].worldY = 45*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 4*gp.titleSize;
		gp.obj[mapNum][i].worldY = 45*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 2*gp.titleSize;
		gp.obj[mapNum][i].worldY = 46*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 3*gp.titleSize;
		gp.obj[mapNum][i].worldY = 46*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 4*gp.titleSize;
		gp.obj[mapNum][i].worldY = 46*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 2*gp.titleSize;
		gp.obj[mapNum][i].worldY = 47*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 3*gp.titleSize;
		gp.obj[mapNum][i].worldY = 47*gp.titleSize;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_LotCoin(gp);
		gp.obj[mapNum][i].worldX = 4*gp.titleSize;
		gp.obj[mapNum][i].worldY = 47*gp.titleSize;
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
		gp.monster[mapNum][i].worldX = gp.titleSize*16;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*26;
		gp.monster[mapNum][i].worldY = gp.titleSize*22;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*29;
		gp.monster[mapNum][i].worldY = gp.titleSize*32;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*25;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*24;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*40;
		gp.monster[mapNum][i].worldY = gp.titleSize*4;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*7;
		gp.monster[mapNum][i].worldY = gp.titleSize*7;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*39;
		gp.monster[mapNum][i].worldY = gp.titleSize*15;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*47;
		gp.monster[mapNum][i].worldY = gp.titleSize*11;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*22;
		gp.monster[mapNum][i].worldY = gp.titleSize*1;
		
		
		mapNum++;
		i = 0;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*30;
		gp.monster[mapNum][i].worldY = gp.titleSize*38;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*36;
		gp.monster[mapNum][i].worldY = gp.titleSize*35;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*43;
		gp.monster[mapNum][i].worldY = gp.titleSize*43;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*46;
		gp.monster[mapNum][i].worldY = gp.titleSize*48;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*8;
		gp.monster[mapNum][i].worldY = gp.titleSize*46;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*35;
		gp.monster[mapNum][i].worldY = gp.titleSize*23;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*38;
		gp.monster[mapNum][i].worldY = gp.titleSize*24;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*46;
		gp.monster[mapNum][i].worldY = gp.titleSize*23;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*47;
		gp.monster[mapNum][i].worldY = gp.titleSize*24;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*8;
		gp.monster[mapNum][i].worldY = gp.titleSize*46;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*44;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*48;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*15;
		gp.monster[mapNum][i].worldY = gp.titleSize*9;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*18;
		gp.monster[mapNum][i].worldY = gp.titleSize*9;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*15;
		gp.monster[mapNum][i].worldY = gp.titleSize*42;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*21;
		gp.monster[mapNum][i].worldY = gp.titleSize*44;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*18;
		gp.monster[mapNum][i].worldY = gp.titleSize*48;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*33;
		gp.monster[mapNum][i].worldY = gp.titleSize*7;
		
		i++;
		gp.monster[mapNum][i] = new MON_Cyclope(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*46;
		gp.monster[mapNum][i].worldY = gp.titleSize*7;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*18;
		gp.monster[mapNum][i].worldY = gp.titleSize*34;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*36;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*41;
		gp.monster[mapNum][i].worldY = gp.titleSize*4;
		
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*21;
		gp.monster[mapNum][i].worldY = gp.titleSize*29;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*27;
		gp.monster[mapNum][i].worldY = gp.titleSize*23;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*22;
		gp.monster[mapNum][i].worldY = gp.titleSize*42;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*14;
		gp.monster[mapNum][i].worldY = gp.titleSize*48;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*9;
		gp.monster[mapNum][i].worldY = gp.titleSize*35;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*6;
		gp.monster[mapNum][i].worldY = gp.titleSize*40;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*4;
		gp.monster[mapNum][i].worldY = gp.titleSize*35;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*40;
		gp.monster[mapNum][i].worldY = gp.titleSize*48;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*41;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*42;
		gp.monster[mapNum][i].worldY = gp.titleSize*35;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*43;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*44;
		gp.monster[mapNum][i].worldY = gp.titleSize*35;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*45;
		gp.monster[mapNum][i].worldY = gp.titleSize*30;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*10;
		gp.monster[mapNum][i].worldY = gp.titleSize*27;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*8;
		gp.monster[mapNum][i].worldY = gp.titleSize*31;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*7;
		gp.monster[mapNum][i].worldY = gp.titleSize*27;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*6;
		gp.monster[mapNum][i].worldY = gp.titleSize*31;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*5;
		gp.monster[mapNum][i].worldY = gp.titleSize*27;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*4;
		gp.monster[mapNum][i].worldY = gp.titleSize*31;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*22;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*30;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*11;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*12;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*13;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*14;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*22;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*23;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*27;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*28;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*24;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*25;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*26;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*29;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*30;
		gp.monster[mapNum][i].worldY = gp.titleSize*12;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*5;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*17;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*33;
		gp.monster[mapNum][i].worldY = gp.titleSize*16;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*21;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*29;
		gp.monster[mapNum][i].worldY = gp.titleSize*2;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*44;
		gp.monster[mapNum][i].worldY = gp.titleSize*1;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*7;
		gp.monster[mapNum][i].worldY = gp.titleSize*37;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*5;
		gp.monster[mapNum][i].worldY = gp.titleSize*42;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*26;
		gp.monster[mapNum][i].worldY = gp.titleSize*45;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*38;
		gp.monster[mapNum][i].worldY = gp.titleSize*45;
		
		i++;
		gp.monster[mapNum][i] = new MON_Beast(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*33;
		gp.monster[mapNum][i].worldY = gp.titleSize*36;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*43;
		gp.monster[mapNum][i].worldY = gp.titleSize*14;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*48;
		gp.monster[mapNum][i].worldY = gp.titleSize*14;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*21;
		gp.monster[mapNum][i].worldY = gp.titleSize*23;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*13;
		gp.monster[mapNum][i].worldY = gp.titleSize*20;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*7;
		gp.monster[mapNum][i].worldY = gp.titleSize*20;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*27;
		gp.monster[mapNum][i].worldY = gp.titleSize*14;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*6;
		gp.monster[mapNum][i].worldY = gp.titleSize*9;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*39;
		gp.monster[mapNum][i].worldY = gp.titleSize*36;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*11;
		gp.monster[mapNum][i].worldY = gp.titleSize*5;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*13;
		gp.monster[mapNum][i].worldY = gp.titleSize*7;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*33;
		gp.monster[mapNum][i].worldY = gp.titleSize*4;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*48;
		gp.monster[mapNum][i].worldY = gp.titleSize*4;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*40;
		gp.monster[mapNum][i].worldY = gp.titleSize*1;
		
		i++;
		gp.monster[mapNum][i] = new MON_MadOrc(gp);
		gp.monster[mapNum][i].worldX = gp.titleSize*46;
		gp.monster[mapNum][i].worldY = gp.titleSize*1;
	}
}
