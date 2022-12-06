package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_End extends Entity{
	GamePanel gp;
	public OBJ_End(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = "End";
		down0 = setup("/objects/end");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
