import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
Font titleFont;
Font startFont;
Font instructionFont;
Font endFont;
GamePanel (){
	titleFont = new Font ("Arial", Font.PLAIN, 48);
	startFont = new Font ("Arial", Font.PLAIN, 24);
	instructionFont = new Font ("Arial", Font.PLAIN, 20);
	endFont = new Font ("Arial", Font.PLAIN, 20);
	
}
@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 18, 150);
		g.setFont(startFont);
		g.drawString("Press ENTER to start", 125, 350);
		g.setFont(instructionFont);
		g.drawString("Press SPACE for instructions", 110, 450);
	}

	void drawGameState(Graphics g) {

	}

	void drawEndState(Graphics g) {

	}
}
