package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_GodShield extends Entity{

	public OBJ_GodShield(GamePanel gp) {
		super(gp);
		
		type = type_godshield;
		name = "God Shield";
		down0 = setup("/objects/shield_03d");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = setup("/objects/shield_03d");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		defenseValue = 10;
		price = 249;
		description = "[" + name + "]\nChiến khiên bất hoại của\nthần chiến tranh";
	} 

}
