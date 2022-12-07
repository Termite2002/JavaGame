package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_HellSword extends Entity{

	public OBJ_HellSword(GamePanel gp) {
		super(gp);
		
		type = type_hellsword;
		name = "Hell Sword";
		down1 = setup("/objects/sword_01e");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		attackValue = 7;
		attackArea.width = 46;
		attackArea.height = 46;
		knockBackPower = 11;
		price = 299;
		description = "[" + name + "]\nThanh kiếm của quỷ vương\ndưới địa ngục";
	} 

}
