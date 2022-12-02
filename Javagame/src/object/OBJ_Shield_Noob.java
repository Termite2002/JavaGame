package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Shield_Noob extends Entity {

	public OBJ_Shield_Noob(GamePanel gp) {
		super(gp);
		
		type = type_woodenshield;
		name = "Wooden Shield";
		down1 = setup("/objects/shield_01a");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 1;
		description = "[" + name + "]\nMột cái khiên gỗ được\nlàm từ thân cây sưa đỏ";
	}

}
