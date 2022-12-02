package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Chest extends Entity{
	GamePanel gp;
	Entity loot;
	boolean opened = false;
	
	public OBJ_Chest(GamePanel gp, Entity loot) {
		super(gp);
		this.gp = gp;
		this.loot = loot;
		
		type = type_obstacle;
		name = "chest";
		image = setup("/objects/treasure");
		image2 = setup("/objects/treasure_open");
		down0 = image;
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void interact() {
		gp.gameState = gp.dialogueState;
		
		if(opened == false) {
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("Bạn mở rương và tìm thấy " + loot.name + "!");
			
			if(gp.player.canObtainItem(loot) == false) {
				sb.append("\n...Nhưng túi đồ bạn không thể chứa thêm!");
			}
			else {
				sb.append("\nBạn nhận được " + loot.name + "!");
//				gp.player.inventory.add(loot);
				down0 = image2;
				opened = true;
			}
			gp.ui.currentDialogue = sb.toString();
		}
		else {
			gp.ui.currentDialogue = "Một cái rương rỗng :((";
		}
	}

}
