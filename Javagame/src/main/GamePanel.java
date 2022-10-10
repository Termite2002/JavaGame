package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {						// kế thừa class JPanel
	// SCREEN SETTINGS
    final int originalTitleSize = 16;											// 16 x 16
    final int scale = 3;
    
    public final int titleSize = originalTitleSize * scale;							// 48 x 48
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 14;
    public final int screenWidth = titleSize * maxScreenCol; 							// chiều ngang cửa sổ window
    public final int screenHeight = titleSize * maxScreenRow;							// chiều dọc cửa sổ window
    
    // WORLD SETTING
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * titleSize;
    public final int worldHeight = maxWorldRow * titleSize;
    
    // FPS
    int FPS = 50;
    
    // SYSTEM
    TileManager tileM = new TileManager(this);										
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity npc[] = new Entity[10];
    public SuperObject obj[] = new SuperObject[10];
    
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    
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
//    	playMusic(0);
    	gameState = titleState;
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
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
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
			ui.draw(g2);
		}
		// OTHERS
		else {
			// TILE
			tileM.draw(g2);
			
			// OBJECT
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null)
					obj[i].draw(g2, this);
			}
			
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// PLAYER
			player.draw(g2);
			ui.draw(g2);
		}
		

		
		g2.dispose();															// Vứt bỏ graphic, giải phóng hệ thống tài nguyên đang dùng 
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

