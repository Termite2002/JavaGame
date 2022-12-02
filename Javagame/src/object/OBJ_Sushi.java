package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Sushi extends Entity{
	
	GamePanel gp;
	
	public OBJ_Sushi(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		stackable = true;
		name = "Sushi";
		value = 2;
		down0 = setup("/objects/Sushi co");
		down1 = setup("/objects/Sushi co");
		price = 8;
		description = "[" + name + "]\nMột món ăn Nhật Bản gồm\ncơm trộn giấm kết hợp với\ncác nguyên liệu khác";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Bạn vừa ăn " + name + "!\n*Bạn tràn đầy nhiệt huyết*";
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(16);
		return true;
	}
}
