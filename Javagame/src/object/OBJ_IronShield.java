package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_IronShield extends Entity{

	public OBJ_IronShield(GamePanel gp) {
		super(gp);
		
		type = type_ironshield;
		name = "Iron Shield";
		down0 = setup("/objects/shield_01b");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/shield_01b");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 2;
		price = 50;
		description = "[" + name + "]\nMột cái khiên cứng cáp\nđược luyện bằng sắt";
	}

}
