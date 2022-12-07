package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SmallPotion extends Entity{
	
	GamePanel gp;
	public OBJ_SmallPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		stackable = true;
		name = "Lọ máu nhỏ";
		value = 3;
		down0 = setup("/objects/potion_01a");
		down1 = setup("/objects/potion_01a");
		price = 16;
		description = "[" + name + "]\nDùng để hồi lượng nhỏ\nmáu";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Bạn vừa uống " + name + "!\n*Bạn tràn đầy nhiệt huyết*";
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(23);
		return true;
	}
}
