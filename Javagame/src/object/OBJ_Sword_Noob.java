package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Sword_Noob extends Entity {

	public OBJ_Sword_Noob(GamePanel gp) {
		super(gp);
		
		type = type_sw_noob;
		name = "Bronze Sword";
		down1 = setup("/objects/sword_01b");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		knockBackPower = 1;
		price = 10;
		description = "[" + name + "]\nMột thanh kiếm cũ đã sứt\nmẻ của sư phụ truyền lại";
	}
	
}
