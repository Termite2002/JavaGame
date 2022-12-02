package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Door extends Entity{
	GamePanel gp;
	public OBJ_Door(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = "Door";
		down0 = setup("/objects/Door");
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void interact() {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Bạn cần chìa khóa để mở";
	}
}
