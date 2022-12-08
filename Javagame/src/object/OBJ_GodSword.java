package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_GodSword extends Entity{

	public OBJ_GodSword(GamePanel gp) {
		super(gp);
		
		type = type_godsword;
		name = "God Sword";
		down1 = setup("/objects/sword_03e");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		attackValue = 10;
		attackArea.width = 36;
		attackArea.height = 36;
		knockBackPower = 13;
		price = 299;
		description = "[" + name + "]\nThanh kiếm nhuốm đầy máu\ncủa thần chiến tranh";
	}

}
