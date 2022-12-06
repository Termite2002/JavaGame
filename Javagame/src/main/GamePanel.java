package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {						// kế thừa class JPanel
	// SCREEN SETTINGS
    final int originalTitleSize = 16;											// 16 x 16
    final int scale = 3;
    
    public final int titleSize = originalTitleSize * scale;							// 48 x 48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 14;
    public final int screenWidth = titleSize * maxScreenCol; 							// chiều ngang cửa sổ window
    public final int screenHeight = titleSize * maxScreenRow;							// chiều dọc cửa sổ window
    
    // WORLD SETTING
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;
    public final int worldWidth = maxWorldCol * titleSize;
    public final int worldHeight = maxWorldRow * titleSize;
    
    // FOR FULL SCREEN 
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    
    public boolean appearAvatar = false;
    public boolean active = false;
    // FPS
    int FPS = 50;
    
    // SYSTEM
    public TileManager tileM = new TileManager(this);										
    public KeyHandler keyH = new KeyHandler(this);
    public UtilityTool uT = new UtilityTool();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this, appearAvatar);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    Thread gameThread;
    
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH, uT);
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity obj[][] = new Entity[maxMap][30];
    public Entity monster[][] = new Entity[maxMap][20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int instructionState = 5;
    public final int optionState = 6;
    public final int gameOverState = 7;
    public final int transitionState = 8;
    public final int tradeState = 9;
    public final int endState = 10;
    
    // FACESET
    public int face;
    public final int suphu = 0;
    public final int tg = 1;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));		// thiết lập kích thước cho class JPanel
        this.setBackground(Color.black);										// tạo màu nền
        this.setDoubleBuffered(true);											// Để true, mọi hoạt ảnh của thành phần này đều được hoàn thành trong bộ đệm chạy ngầm
        this.addKeyListener(keyH);
        this.setFocusable(true);												// Hàm này giúp GamePanel tập trung vào key input
    }
     
    public void setupGame() {
    	aSetter.setObject();
    	aSetter.setNPC();
    	aSetter.setMonster();
    	playMusic(14);
    	gameState = titleState;
    	
    	tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    	g2 = (Graphics2D)tempScreen.getGraphics();
//    	setFullScreen();
    }
    public void retry() {
    	player.setDefaultPositions();
    	player.restoreLifeandMana();
    	aSetter.setNPC();
    	aSetter.setMonster();
    	playMusic(15);
    }
    public void restart() {
    	player.setDefaultValues();
    	player.setItems();
    	aSetter.setObject();
    	aSetter.setNPC();
    	aSetter.setMonster();
    	playMusic(14);
    }
    public void setFullScreen() {
    	
//    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//    	GraphicsDevice gd = ge.getDefaultScreenDevice();
//    	gd.setFullScreenWindow(Main.window);
    	
    	screenWidth2 = Main.window.getWidth();
    	screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread() {
    	gameThread = new Thread(this);											// Khởi tạo luồng game, this hiểu đơn giản là class Panel đưa vào luồng
    	gameThread.start();														// Khởi động luồng, tự động gọi hàm run phía dưới
    }

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;									// Thời gian một khung hình
		double nextDrawTime = System.nanoTime() + drawInterval;					// Tính thời điểm khung hình tiếp theo (nanoTime() là hàm lấy tg hiện tại)
		
		// Core: Game loop
		while(gameThread != null) {
			
			
			// 1 UPDATE: Update information in game
			update();
			
			// 2 DRAW: Draw updated information on screen
			repaint();
//			drawToTempScreen();
//			drawToScreen();
			// SLEEP METHOD
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);								// Chỉ nhận milis
				nextDrawTime = System.nanoTime() + drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() {
		
		if(gameState == playState) {
			// Player
			player.update();
			// NPC
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}
		}
		if(gameState == pauseState) {
			// nope
		}
	}

	public void paintComponent(Graphics g) {									// paintComponent là 1 hàm dựng sẵn trong java dùng để vẽ trên JPanel truyền vào lớp Graphics(1 lớp có nhiều hàm vẽ)
		super.paintComponent(g);												// super dùng để gọi method của class JPanel(superclass) mà GamePanel(subclass) kế thừa. paintComponent, phương thức này được override từ lớp JPanel		
		Graphics2D g2 = (Graphics2D)g;											// Class Graphics2D kế thừa từ class Graphic, dùng để kiểm soát hình học, tọa độ, màu sắc, bố cục text
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2,false);
		}
		// OTHERS
		else {
			// TILE
			tileM.draw(g2);
			
			// ADD ENTITIES TO THE LIST
			entityList.add(player);
			
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			
			for(int i = 0; i < obj[1].length; i++) {
				if(obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}
			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					entityList.add(monster[currentMap][i]);
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			
			// Sort entities
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			// Draw entities
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			entityList.clear();
			
//			// OBJECT
//			for(int i = 0; i < obj.length; i++) {
//				if(obj[i] != null)
//					obj[i].draw(g2, this);
//			}
//			
//			// NPC
//			for(int i = 0; i < npc.length; i++) {
//				if(npc[i] != null) {
//					npc[i].draw(g2);
//				}
//			}
			
			// PLAYER
//			player.draw(g2);
			ui.draw(g2, appearAvatar);
		}
		

		
		g2.dispose();															// Vứt bỏ graphic, giải phóng hệ thống tài nguyên đang dùng 
	}
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}

