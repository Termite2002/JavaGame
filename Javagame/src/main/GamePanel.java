package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {						// kế thừa class JPanel
	// SCREEN SETTINGS
    final int originalTitleSize = 16;											// 16 x 16
    final int scale = 3;
    
    public final int titleSize = originalTitleSize * scale;							// 48 x 48
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 15;
    public final int screenWidth = titleSize * maxScreenCol; 							// chiều ngang cửa sổ window
    public final int screenHeight = titleSize * maxScreenRow;							// chiều dọc cửa sổ window
    
    // WORLD SETTING
    public final int maxWorldCol = 22;
    public final int maxWorldRow = 15;
    public final int worldWidth = maxWorldCol * titleSize;
    public final int worldHeight = maxWorldRow * titleSize;
    
    // FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    
    
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));		// thiết lập kích thước cho class JPanel
        this.setBackground(Color.black);										// tạo màu nền
        this.setDoubleBuffered(true);											// Để true, mọi hoạt ảnh của thành phần này đều được hoàn thành trong bộ đệm chạy ngầm
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
    	gameThread = new Thread(this);											// Khởi tạo luồng game, this hiểu đơn giản là class Panel đưa vào luồng
    	gameThread.start();
    }

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		// Core: Game loop
		while(gameThread != null) {
			
			
			// 1 UPDATE: Update information in game
			update();
			
			// 2 DRAW: Draw updated information on screen
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime = System.nanoTime() + drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {									// paintComponent là 1 hàm dựng sẵn trong java dùng để vẽ trên JPanel truyền vào lớp Graphics(1 lớp có nhiều hàm vẽ)
		super.paintComponent(g);												// super dùng để gọi method của class JPanel(superclass) mà GamePanel(subclass) kế thừa. paintComponent, phương thức này được override từ lớp JPanel		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		
		g2.dispose();
	}
}

