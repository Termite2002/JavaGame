package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
        JFrame window = new JFrame();                                           // tạo frame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  // đóng cửa số 
        window.setResizable(false);                                             // cửa sổ không thay đổi kích thước
        window.setTitle("WanderingNinja");                                       // tên cửa sổ
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();															// để biến window fit kích thước với JPanel

        window.setLocationRelativeTo(null);                                     // để cửa sổ xuất hiện giữa màn hình
        window.setVisible(true);                                                // cửa sổ nhìn được    
		
        gamePanel.setupGame();
        gamePanel.startGameThread();
	}
}
