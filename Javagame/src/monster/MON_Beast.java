package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BigPotion;
import object.OBJ_Coin;
import object.OBJ_LotCoin;
import object.OBJ_SomeCoin;
import object.OBJ_Sushi;

public class MON_Beast extends Entity{
	GamePanel gp;
	public MON_Beast(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_monster;
		name = "Beast";
		defaultSpeed = 5;
		speed = defaultSpeed;
		maxLife = 49;
		life = maxLife;
		attack = 3;
		defense = 6;
		exp = 20;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}
	public void getImage() {

		up0 = setup("/monster/Beast/up0");
		up1 = setup("/monster/Beast/up1");
		up2 = setup("/monster/Beast/up2");
		up3 = setup("/monster/Beast/up3");
		down0 = setup("/monster/Beast/down0");
		down1 = setup("/monster/Beast/down1");
		down2 = setup("/monster/Beast/down2");
		down3 = setup("/monster/Beast/down3");
		left0 = setup("/monster/Beast/left0");
		left1 = setup("/monster/Beast/left1");
		left2 = setup("/monster/Beast/left2");
		left3 = setup("/monster/Beast/left3");
		right0 = setup("/monster/Beast/right0");
		right1 = setup("/monster/Beast/right1");
		right2 = setup("/monster/Beast/right2");
		right3 = setup("/monster/Beast/right3");
	}
	
	public void setAction() {
		
		if(onPath == true) {
			//Stop chase
			checkStopChasingOrNot(gp.player, 15, 100);
			
			// Search direction
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {	
			// Start chase
			checkStartChasingOrNot(gp.player, 6, 100);
			
			getRandomDirection();
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gp.player.direction;
//		onPath = true;
	}
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;
		

		if(i >= 0 && i < 50) {
			dropItem(new OBJ_LotCoin(gp));
		}
		if(i >= 50 && i < 60) {
			dropItem(new OBJ_BigPotion(gp));
		}
	}
}

