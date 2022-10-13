package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Cyclope extends Entity{

	public MON_Cyclope(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Cyclope";
		speed = 1;
		maxLife = 5;
		life = maxLife;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}
	public void getImage() {

		up0 = setup("/monster/Cyclope/up0");
		up1 = setup("/monster/Cyclope/up1");
		up2 = setup("/monster/Cyclope/up2");
		up3 = setup("/monster/Cyclope/up3");
		down0 = setup("/monster/Cyclope/down0");
		down1 = setup("/monster/Cyclope/down1");
		down2 = setup("/monster/Cyclope/down2");
		down3 = setup("/monster/Cyclope/down3");
		left0 = setup("/monster/Cyclope/left0");
		left1 = setup("/monster/Cyclope/left1");
		left2 = setup("/monster/Cyclope/left2");
		left3 = setup("/monster/Cyclope/left3");
		right0 = setup("/monster/Cyclope/right0");
		right1 = setup("/monster/Cyclope/right1");
		right2 = setup("/monster/Cyclope/right2");
		right3 = setup("/monster/Cyclope/right3");
	}

	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 100) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
}
