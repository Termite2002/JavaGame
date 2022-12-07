package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_HellShield extends Entity{

	public OBJ_HellShield(GamePanel gp) {
		super(gp);
		
		type = type_hellshield;
		name = "Hell Shield";
		down0 = setup("/objects/shield_01e");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/shield_01e");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 6;
		price = 249;
		description = "[" + name + "]\nChiến khiên phả ra hơi\nthở của địa ngục";
	} 

}
