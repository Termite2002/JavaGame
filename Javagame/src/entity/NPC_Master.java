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
		
		name = "master";
		direction = "down";
		speed = 2;
		
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
	
	public void setDialogue() {
		dialogues[0] = "Con đang thuyết trình btl A* hả? Ta thấy bài thuyết trình của con thật là\nsiêu cấp vip pro!";
		dialogues[1] = "Chắc chắn được 10 điểm rồi, bảo cô giáo con thế nhé";
		dialogues[2] = "Con không nhớ gì sao?";
		dialogues[3] = "Chuyện dài lắm, ta không biết phải bắt đầu từ đâu nữa. Chúng ta đang\nchạy trốn khỏi kẻ thù nhưng " + 
				"không may thuyền gặp phải cơn bão dữ.\nMay mắn chúng ta đã dạt vào hòn đảo này.";
		dialogues[4] = "Điều đáng buồn là em gái con đã mất" + " tích khi thuyền "
				+ "ta bị sóng\ncuốn trôi!";
		dialogues[5] = "Ta xin lỗi vì đã không bảo vệ được hai đứa.";
		dialogues[6] = "Bây giờ con hãy nghỉ ngơi đi! Chúng ta sẽ bàn bạc chuyện cần làm\nkhi con khỏe trở lại.";
		dialogues[7] = "Đi đến cửa căn lều bên cạnh chúng ta đây sẽ giúp con nghỉ ngơi.";
		dialogues[8] = "Con đã nghỉ ngơi xong rồi chứ. Mỗi khi con nghỉ trong lều, quái vật\nsẽ hồi sinh trở lại.";
		dialogues[9] = "Bây giờ trên hòn đảo này chỉ có ba người: ta, con và ông thương gia.";
		dialogues[10] = "Trong lúc con bất tỉnh chúng ta đã sửa xong thuyền rồi! Tuy nhiên,\nkhông thể khởi hành vì lũ quái vật quá đông."
				+ "Ta đã phát hiện ra\nrằng hòn đảo này đang sử dụng một vòng sức mạnh bao quanh hòn\nđảo này! Nguồn năng lượng này phát ra"
				+ " từ hầm ngục phía Bắc.";
		dialogues[11] = "Ta hi vọng con sẽ xuống đó và phong ấn nguồn năng lượng đó lại.\nRồi chúng ta sẽ lên tàu và thoát khỏi đây.\nSẽ rất nguy hiểm cho con, nhưng ta đã quá già để chiến đấu rồi.";
		dialogues[12] = "Ta khuyên con nên luyện sức mạnh từ từ và xem những món trang\nbị của ông thương gia trước khi xuống hầm ngục.";
		dialogues[13] = "Ấn H để xem bản hướng dẫn ta đã viết cho con.";
		dialogues[14] = "Chúc con may mắn!!!";
	}
	
	public void setAction() {
		
		if(onPath == true) {
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.titleSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.titleSize;
			
			searchPath(goalCol, goalRow);
		}
		else {
			actionLockCounter++;
			
			if(actionLockCounter == 100) {
//				Random random = new Random();
//				int i = random.nextInt(100)+1;
				
//				if(i <= 25) {
//					direction = "up";
//				}
//				if(i > 25 && i <= 50) {
//					direction = "down";
//				}
//				if(i > 50 && i <= 75) {
//					direction = "left";
//				}
//				if(i > 75 && i <= 100) {
//					direction = "right";
//				}
//				
				if(direction == "down") {
					direction = "left";
				}
				else if(direction == "left") {
					direction = "up";
				}
				else if(direction == "up") {
					direction = "right";
				}
				else if(direction == "right") {
					direction = "down";
				}
				actionLockCounter = 0;
			}
		}

	}
	public void speak() {
		
		// Do some special
		
		super.speak();
		if (onPath == false) {
			onPath = true;
		}
		else {
			onPath = false;
		}
	}
}
