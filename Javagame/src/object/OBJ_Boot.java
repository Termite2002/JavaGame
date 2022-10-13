package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boot extends Entity{
	public OBJ_Boot(GamePanel gp) {
		super(gp);
		name = "boot";
		down0 = setup("/objects/normalboot");
	}
}
