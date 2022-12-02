package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Key extends Entity{
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		stackable = true;
		name = "Key";
		down1 = setup("/objects/SilverKey");
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, 32);
		down0 = setup("/objects/SilverKey1");
		price = 100;
		description = "[" + name + "]\nMột chiếc chìa khóa thông\nthường";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(entity, gp.obj, "Door");
		if(objIndex != 999) {
			gp.ui.currentDialogue = "Bạn dùng " + name + " và mở cách cửa";
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "Bạn chờ đợi nhưng không có điểu gì xảy ra cả!!!";
			return false;
		}
	}
}
