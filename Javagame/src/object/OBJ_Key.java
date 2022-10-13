package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "key";
		down0 = setup("/objects/SilverKey");

	}
}
