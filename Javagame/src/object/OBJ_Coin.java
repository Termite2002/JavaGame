package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	GamePanel gp;

	public OBJ_Coin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Coin";
		value = 1;
		down0 = setup("/objects/GoldCoin");
		down1 = setup("/objects/GoldCoin");
	}
	public boolean use(Entity entity) {
		
		gp.playSE(1);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
