package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {											// Kế thừa interface 
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed; 
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();															// Hàm trả về int keyCode liên kết với key bấm
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		
		// PAUSTE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		// CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		// INSTRUCTION STATE
		else if(gp.gameState == gp.instructionState) {
			instructionState(code);
		}
		// OPTION STATE
		else if(gp.gameState == gp.optionState) {
			optionState(code);
		}
		// GAME OVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		// TRADE STATE
		else if(gp.gameState == gp.tradeState) {
			tradeState(code);
		}
	}
	
	public void titleState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.playSE(12);
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.playSE(12);
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.stopMusic();
				gp.playSE(13);
				gp.gameState = gp.playState;
				gp.playMusic(15);
			}
			if(gp.ui.commandNum == 1) {
				gp.playSE(13);
				// add later
			}
			if(gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_H) {
			gp.gameState = gp.instructionState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_L) {
			shotKeyPressed = true;
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_O) {
			gp.gameState = gp.optionState;
		}
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
			gp.appearAvatar = false;
		}
	}
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		playerInventory(code);
	}
	
	public void instructionState(int code) {
		if(code == KeyEvent.VK_H) {
			gp.gameState = gp.playState;
		}
	}
	
	public void optionState(int code) {
		if(code == KeyEvent.VK_O) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.playSE(12);
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.playSE(12);
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
				}
			}
		}
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(12);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(12);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.ui.commandNum = 0;
				gp.gameState = gp.titleState;
				gp.restart();
			}
		}
	}
	public void tradeState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(12);
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(12);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	public void playerInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.playSE(11);
				gp.ui.playerSlotRow--;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.playSE(11);
				gp.ui.playerSlotCol--;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.playSE(11);
				gp.ui.playerSlotRow++;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.playSE(11);
				gp.ui.playerSlotCol++;
			}
		}
	}
	public void npcInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.playSE(11);
				gp.ui.npcSlotRow--;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.playSE(11);
				gp.ui.npcSlotCol--;
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.playSE(11);
				gp.ui.npcSlotRow++;
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.playSE(11);
				gp.ui.npcSlotCol++;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_L) {
			shotKeyPressed = false;
		}
	}
	
}
