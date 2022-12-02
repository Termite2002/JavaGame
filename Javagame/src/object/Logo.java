package object;

import entity.Entity;
import main.GamePanel;

public class Logo extends Entity {

	public Logo(GamePanel gp) {
		super(gp);
		up0 = setup("/objects/level");
		up1 = setup("/objects/strength");
		up2 = setup("/objects/dex");
		up3 = setup("/objects/attack");
		down0 = setup("/objects/def");
		down1 = setup("/objects/exp");
		down2 = setup("/objects/lvup");
		down3 = setup("/objects/tien");
		left0 = setup("/objects/speed");
	}

}
