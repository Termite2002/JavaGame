package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_GoldSword extends Entity{

	public OBJ_GoldSword(GamePanel gp) {
		super(gp);
		
		type = type_goldsword;
		name = "Gold Sword";
		down1 = setup("/objects/sword_01d");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		attackValue = 4;
		attackArea.width = 44;
		attackArea.height = 44;
		knockBackPower = 8;
		price = 149;
		description = "[" + name + "]\nMột thanh kiếm mạ vàng\nóng ánh lên vẻ sang trọng";
	}

}
