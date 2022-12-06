package monster;

import java.util.Random;
import java.awt.image.BufferedImage;
import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Coin;
import object.OBJ_Sushi;

import javax.imageio.ImageIO;

public class MON_Cyclope extends Entity{
	
	GamePanel gp;

	public MON_Cyclope(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_monster;
		name = "Cyclope";
		defaultSpeed = 8;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = 2;
		
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
		
		if(onPath == true) {
			//Stop chase
			checkStopChasingOrNot(gp.player, 10, 100);
			
			// Search direction
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {	
			// Start chase
//			checkStartChasingOrNot(gp.player, 5, 100);
			
			getRandomDirection();
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
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
		if(i >= 50 && i < 100) {
			// later
		}
	}
}
