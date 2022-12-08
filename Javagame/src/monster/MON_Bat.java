package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Coin;
import object.OBJ_LotCoin;
import object.OBJ_SomeCoin;
import object.OBJ_Sushi;

public class MON_Bat extends Entity{
	GamePanel gp;
	public MON_Bat(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_monster;
		name = "Bat";
		defaultSpeed = 9;
		speed = defaultSpeed;
		maxLife = 8;
		life = maxLife;
		attack = 7;
		defense = 4;
		exp = 7;
		
		solidArea.x = 3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}
	public void getImage() {

		up0 = setup("/monster/Bat/bat_down_1");
		up1 = setup("/monster/Bat/bat_down_2");
		up2 = setup("/monster/Bat/bat_down_1");
		up3 = setup("/monster/Bat/bat_down_2");
		down0 = setup("/monster/Bat/bat_down_1");
		down1 = setup("/monster/Bat/bat_down_2");
		down2 = setup("/monster/Bat/bat_down_1");
		down3 = setup("/monster/Bat/bat_down_2");

		
		up0 = UtilityTool.scaleImage(up0, gp.titleSize, gp.titleSize);
		up1 = UtilityTool.scaleImage(up1, gp.titleSize, gp.titleSize);
		up2 = UtilityTool.scaleImage(up2, gp.titleSize, gp.titleSize);
		up3 = UtilityTool.scaleImage(up3, gp.titleSize, gp.titleSize);
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		down2 = UtilityTool.scaleImage(down2, gp.titleSize, gp.titleSize);
		down3 = UtilityTool.scaleImage(down3, gp.titleSize, gp.titleSize);

	}
	public void setAction() {
		
		if(onPath == true) {
//			//Stop chase
//			checkStopChasingOrNot(gp.player, 10, 100);
//			
//			// Search direction
//			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {	
			// Start chase
//			checkStartChasingOrNot(gp.player, 5, 100);
			
//			getRandomDirection();
			actionLockCounter++;
			
			if(actionLockCounter == 50) {
		
				if(direction == "down") {direction = "up";}
				else if(direction == "up") {direction = "down";}	
				actionLockCounter = 0;
			}
		}
	}
	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gp.player.direction;
//		onPath = true;
	}
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;
		
		if(i < 25) {
			dropItem(new OBJ_Coin(gp));
		}
		if(i >= 25 && i < 50) {
			dropItem(new OBJ_Sushi(gp));
		}
		if(i >= 50 && i < 90) {
			dropItem(new OBJ_LotCoin(gp));
		}
	}
}
