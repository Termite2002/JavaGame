package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Kunai extends Projectile{
	
	GamePanel gp;
	
	public OBJ_Kunai(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Kunai";
		speed = 5;
		maxLife = 80;
		life = maxLife;
		attack = 1;
		getImage();
	}
	public void getImage() {
		up1 = setup("/projectile/Shuriken");
	}
}
