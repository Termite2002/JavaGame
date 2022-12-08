package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import object.OBJ_BigPotion;
import object.OBJ_BlueShield;
import object.OBJ_LotCoin;
import object.OBJ_SmallPotion;
import object.OBJ_SomeCoin;

public class MON_MadOrc extends Entity{
	GamePanel gp;
	public MON_MadOrc(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_monster;
		name = "Mad Orc";
		defaultSpeed = 5;
		speed = defaultSpeed;
		maxLife = 32;
		life = maxLife;
		attack = 15;
		defense = 10;
		exp = 13;
		
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 40;
		solidArea.height = 44;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;

		getImage();
		getAttackImage();
	}
	public void getImage() {

		up0 = setup("/monster/Mad Orc/orc_up_1");
		up1 = setup("/monster/Mad Orc/orc_up_2");
		up2 = setup("/monster/Mad Orc/orc_up_1");
		up3 = setup("/monster/Mad Orc/orc_up_2");
		down0 = setup("/monster/Mad Orc/orc_down_1");
		down1 = setup("/monster/Mad Orc/orc_down_2");
		down2 = setup("/monster/Mad Orc/orc_down_1");
		down3 = setup("/monster/Mad Orc/orc_down_2");
		left0 = setup("/monster/Mad Orc/orc_left_1");
		left1 = setup("/monster/Mad Orc/orc_left_2");
		left2 = setup("/monster/Mad Orc/orc_left_1");
		left3 = setup("/monster/Mad Orc/orc_left_2");
		right0 = setup("/monster/Mad Orc/orc_right_1");
		right1 = setup("/monster/Mad Orc/orc_right_2");
		right2 = setup("/monster/Mad Orc/orc_right_1");
		right3 = setup("/monster/Mad Orc/orc_right_2");
		
		up0 = UtilityTool.scaleImage(up0, gp.titleSize, gp.titleSize);
		up1 = UtilityTool.scaleImage(up1, gp.titleSize, gp.titleSize);
		up2 = UtilityTool.scaleImage(up2, gp.titleSize, gp.titleSize);
		up3 = UtilityTool.scaleImage(up3, gp.titleSize, gp.titleSize);
		down0 = UtilityTool.scaleImage(down0, gp.titleSize, gp.titleSize);
		down1 = UtilityTool.scaleImage(down1, gp.titleSize, gp.titleSize);
		down2 = UtilityTool.scaleImage(down2, gp.titleSize, gp.titleSize);
		down3 = UtilityTool.scaleImage(down3, gp.titleSize, gp.titleSize);
		left0 = UtilityTool.scaleImage(left0, gp.titleSize, gp.titleSize);
		left1 = UtilityTool.scaleImage(left1, gp.titleSize, gp.titleSize);
		left2 = UtilityTool.scaleImage(left2, gp.titleSize, gp.titleSize);
		left3 = UtilityTool.scaleImage(left3, gp.titleSize, gp.titleSize);
		right0 = UtilityTool.scaleImage(right0, gp.titleSize, gp.titleSize);
		right1 = UtilityTool.scaleImage(right1, gp.titleSize, gp.titleSize);
		right2 = UtilityTool.scaleImage(right2, gp.titleSize, gp.titleSize);
		right3 = UtilityTool.scaleImage(right3, gp.titleSize, gp.titleSize);
	}
	
	public void getAttackImage() {
		attackUp1 = setup("/monster/Mad Orc/orc_attack_up_1");
		attackUp2 = setup("/monster/Mad Orc/orc_attack_up_2");
		attackDown1 = setup("/monster/Mad Orc/orc_attack_down_1");
		attackDown2 = setup("/monster/Mad Orc/orc_attack_down_2");
		attackLeft1 = setup("/monster/Mad Orc/orc_attack_left_1");
		attackLeft2 = setup("/monster/Mad Orc/orc_attack_left_2");
		attackRight1 = setup("/monster/Mad Orc/orc_attack_right_1");
		attackRight2 = setup("/monster/Mad Orc/orc_attack_right_2");
		attackUp1 = UtilityTool.scaleImage(attackUp1, gp.titleSize, gp.titleSize*2);
		attackDown1 = UtilityTool.scaleImage(attackDown1, gp.titleSize, gp.titleSize*2);
		attackLeft1 = UtilityTool.scaleImage(attackLeft1, gp.titleSize*2, gp.titleSize);
		attackRight1 = UtilityTool.scaleImage(attackRight1, gp.titleSize*2, gp.titleSize);
		attackUp2 = UtilityTool.scaleImage(attackUp2, gp.titleSize, gp.titleSize*2);
		attackDown2 = UtilityTool.scaleImage(attackDown2, gp.titleSize, gp.titleSize*2);
		attackLeft2 = UtilityTool.scaleImage(attackLeft2, gp.titleSize*2, gp.titleSize);
		attackRight2 = UtilityTool.scaleImage(attackRight2, gp.titleSize*2, gp.titleSize);
	}
	public void setAction() {
		
		if(onPath == true) {
			//Stop chase
			checkStopChasingOrNot(gp.player, 6, 100);
			if(attacking == false) {
				checkAttackOrNot(30, gp.titleSize*6, gp.titleSize*4);
			}
			// Search direction
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {	
			// Start chase
			checkStartChasingOrNot(gp.player, 9, 100);
			
			getRandomDirection();
			if(attacking == false) {
				checkAttackOrNot(30, gp.titleSize*6, gp.titleSize*4);
			}
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;
		
		if(i < 15) {
			dropItem(new OBJ_SomeCoin(gp));
		}
		if(i >= 15 && i < 50) {
			dropItem(new OBJ_BigPotion(gp));
		}
		if(i >= 50 && i < 99) {
			dropItem(new OBJ_LotCoin(gp));
		}
		if(i == 99) {
			dropItem(new OBJ_BlueShield(gp));
		}
	}
}
