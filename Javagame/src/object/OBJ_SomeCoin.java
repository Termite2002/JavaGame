package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SomeCoin extends Entity{
	GamePanel gp;
	public OBJ_SomeCoin(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Coin";
		value = 5;
		down0 = setup("/objects/coin_02d");
		down1 = setup("/objects/coin_02d");
	}
	public boolean use(Entity entity) {
		
		gp.playSE(1);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
