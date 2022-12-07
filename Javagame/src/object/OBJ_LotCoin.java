package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LotCoin extends Entity{
	GamePanel gp;
	public OBJ_LotCoin(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Coin";
		value = 10;
		down0 = setup("/objects/coin_03d");
		down1 = setup("/objects/coin_03d");
	}
	public boolean use(Entity entity) {
		
		gp.playSE(1);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
