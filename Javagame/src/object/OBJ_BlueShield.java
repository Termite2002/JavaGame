package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_BlueShield extends Entity {

	public OBJ_BlueShield(GamePanel gp) {
		super(gp);
		
		type = type_blueshield;
		name = "Blue Shield";
		down0 = setup("/objects/shield_01c");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/shield_01c");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 4;
		price = 100;
		description = "[" + name + "]\nMột cái khiên cứng cáp\nmàu xanh ngọc lấp lánh";
	}

}
