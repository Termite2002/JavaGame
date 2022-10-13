package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Heart extends Entity{
	
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		name = "Heart";
		image = setup("/objects/heart/heartfull");
		image2 = setup("/objects/heart/heart34");
		image3 = setup("/objects/heart/hearthalf");
		image4 = setup("/objects/heart/heart14");
		image5 = setup("/objects/heart/heartempty");

	}
}
