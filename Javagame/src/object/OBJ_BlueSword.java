package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_BlueSword extends Entity{

	public OBJ_BlueSword(GamePanel gp) {
		super(gp);
		
		type = type_bluesword;
		name = "Blue Sword";
		down0 = setup("/objects/sword_01c");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/sword_01c");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		attackValue = 3;
		attackArea.width = 36;
		attackArea.height = 36;
		price = 100;
		knockBackPower = 7;
		description = "[" + name + "]\nThanh kiếm được khảm\nngọc xanh";
	}

}
