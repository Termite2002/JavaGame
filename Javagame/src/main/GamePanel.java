package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {						// kế thừa class JPanel
	// SCREEN SETTINGS
    final int originalTitleSize = 16;											// 16 x 16
    final int scale = 3;
    
    final int titleSize = originalTitleSize * scale;							// 48 x 48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));		// thiet lap kich thuoc cho class JPanel
        this.setBackground(Color.black);										// mau nen
        this.setDoubleBuffered(true);											// Để true, mọi hoạt ảnh của thành phần này đều được hoàn thành trong bộ đệm chạy ngầm
        
    }
    
    public void startGameThread() {
    	gameThread = new Thread(this);											// Khởi tạo luồng game, this hiểu đơn giản là class Panel đưa vào luồng
    	gameThread.start();
    }

	@Override
	public void run() {
		// Core: Game loop
		while(gameThread != null) {
			// 1 UPDATE: Update information in game
			update();
			
			// 2 DRAW: Draw updated information on screen
			repaint();
			
		}
	}
	public void update() {
		
	}
	public void paintComponent(Graphics g) {									// paintComponent là 1 hàm dựng sẵn trong java dùng để vẽ trên JPanel truyền vào lớp Graphics(1 lớp có nhiều hàm vẽ)
		super.paintComponent(g);												// super dùng để gọi method của class JPanel(superclass) mà GamePanel(subclass) kế thừa. paintComponent, phương thức này được override từ lớp JPanel
		
		
		
	}
}

