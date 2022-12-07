package entity;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_BigPotion;
import object.OBJ_BlueShield;
import object.OBJ_BlueSword;
import object.OBJ_GoldShiled;
import object.OBJ_GoldSword;
import object.OBJ_HellShield;
import object.OBJ_HellSword;
import object.OBJ_IronShield;
import object.OBJ_Katana;
import object.OBJ_Key;
import object.OBJ_MediumPotion;
import object.OBJ_SmallPotion;
import object.OBJ_Sushi;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		
		super(gp);
		
		name = "trademan";
		direction = "down";
		speed = 1;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
		setItems();
	}
	public void getImage() {
		ava = setup("/npc/trademan/Faceset");
		up0 = setup("/npc/trademan/down0");
		up1 = setup("/npc/trademan/down0");
		up2 = setup("/npc/trademan/down0");
		up3 = setup("/npc/trademan/down0");
		down0 = setup("/npc/trademan/down0");
		down1 = setup("/npc/trademan/down0");
		down2 = setup("/npc/trademan/down0");
		down3 = setup("/npc/trademan/down0");
		left0 = setup("/npc/trademan/down0");
		left1 = setup("/npc/trademan/down0");
		left2 = setup("/npc/trademan/down0");
		left3 = setup("/npc/trademan/down0");
		right0 = setup("/npc/trademan/down0");
		right1 = setup("/npc/trademan/down0");
		right2 = setup("/npc/trademan/down0");
		right3 = setup("/npc/trademan/down0");
		
//		up0 = UtilityTool.scaleImage(up0, gp.titleSize, gp.titleSize);
//		up1 = UtilityTool.scaleImage(up1, gp.titleSize, gp.titleSize);
//		up2 = UtilityTool.scaleImage(up2, gp.titleSize, gp.titleSize);
//		up3 = UtilityTool.scaleImage(up3, gp.titleSize, gp.titleSize);
//		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
//		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
//		down2 = UtilityTool.scaleImage(down2, gp.titleSize, gp.titleSize);
//		down3 = UtilityTool.scaleImage(down3, gp.titleSize, gp.titleSize);
//		left0 = UtilityTool.scaleImage(left0, gp.titleSize, gp.titleSize);
//		left1 = UtilityTool.scaleImage(left1, gp.titleSize, gp.titleSize);
//		left2 = UtilityTool.scaleImage(left2, gp.titleSize, gp.titleSize);
//		left3 = UtilityTool.scaleImage(left3, gp.titleSize, gp.titleSize);
//		right0 = UtilityTool.scaleImage(right0, gp.titleSize, gp.titleSize);
//		right1 = UtilityTool.scaleImage(right1, gp.titleSize, gp.titleSize);
//		right2 = UtilityTool.scaleImage(right2, gp.titleSize, gp.titleSize);
//		right3 = UtilityTool.scaleImage(right3, gp.titleSize, gp.titleSize);
	}
	
	public void setDialogue() {
		dialogues[0] = "Hehe, ở đây ta có vài món đồ ngon cho con nè!!!Con có muốn xem qua\nkhông?";

	}
	public void setItems() {
		inventory.add(new OBJ_Sushi(gp));
		inventory.add(new OBJ_SmallPotion(gp));
		inventory.add(new OBJ_MediumPotion(gp));
		inventory.add(new OBJ_BigPotion(gp));
		inventory.add(new OBJ_IronShield(gp));
		inventory.add(new OBJ_Katana(gp));
		inventory.add(new OBJ_BlueShield(gp));
		inventory.add(new OBJ_BlueSword(gp));
		inventory.add(new OBJ_GoldShiled(gp));
		inventory.add(new OBJ_GoldSword(gp));
		inventory.add(new OBJ_HellShield(gp));
		inventory.add(new OBJ_HellSword(gp));
	}
	public void speak() {
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
