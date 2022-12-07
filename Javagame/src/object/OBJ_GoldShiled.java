package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_GoldShiled extends Entity{

	public OBJ_GoldShiled(GamePanel gp) {
		super(gp);
		
		type = type_goldshield;
		name = "Gold Shield";
		down0 = setup("/objects/shield_01d");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/shield_01d");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 4;
		price = 149;
		description = "[" + name + "]\nKhiên bằng vàng khá nặng\nnhưng vô cùng chiến";
	}

}
