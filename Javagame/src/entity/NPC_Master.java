package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_Master extends Entity{
	
	public NPC_Master(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		ava = setup("/npc/master/Faceset");
		up0 = setup("/npc/master/up0");
		up1 = setup("/npc/master/up1");
		up2 = setup("/npc/master/up2");
		up3 = setup("/npc/master/up3");
		down0 = setup("/npc/master/down0");
		down1 = setup("/npc/master/down1");
		down2 = setup("/npc/master/down2");
		down3 = setup("/npc/master/down3");
		left0 = setup("/npc/master/left0");
		left1 = setup("/npc/master/left1");
		left2 = setup("/npc/master/left2");
		left3 = setup("/npc/master/left3");
		right0 = setup("/npc/master/right0");
		right1 = setup("/npc/master/right1");
		right2 = setup("/npc/master/right2");
		right3 = setup("/npc/master/right3");
	}
	
	public void setDialogue() {
		dialogues[0] = "1 2 3 4, con có đánh rơi nhịp nào khum? Mùa đông\nvề rồi con đã có người yêu chưa?";
		dialogues[1] = "Mày thích gì";
		dialogues[2] = "À, tao biết rồi!!!";
		dialogues[3] = "MÀY MUỐN ĐÁNH NHAU ĐÚNG KHÔNG?";
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
	public void speak() {
		
		// Do some special
		
		super.speak();
	}
}
