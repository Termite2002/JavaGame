package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Katana extends Entity{

	public OBJ_Katana(GamePanel gp) {
		super(gp);
		
		type = type_katana;
		name = "Katana";
		down0 = setup("/objects/Katana");
		down0 = UtilityTool.scaleImage(down0, 18, 42);
		down1 = setup("/objects/Katana");
		down1 = UtilityTool.scaleImage(down1, 18, 42);
		attackValue = 2;
		attackArea.width = 40;
		attackArea.height = 40;
		price = 60;
		knockBackPower = 10;
		description = "[" + name + "]\nMột thanh katana sắc bén";
	}

}
