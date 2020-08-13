import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
Font titleFont;
Font startFont;
Font instructionFont;
Font endFont1;
Font endFont2;
Font endFont3;
Timer frameDraw;
Rocketship rocket = new Rocketship (250, 550, 50, 50);
GamePanel (){
	titleFont = new Font ("Arial", Font.PLAIN, 48);
	startFont = new Font ("Arial", Font.PLAIN, 24);
	instructionFont = new Font ("Arial", Font.PLAIN, 20);
	endFont1 = new Font ("Arial", Font.PLAIN, 48);
	endFont2 = new Font ("Arial", Font.PLAIN, 24);
	endFont3 = new Font ("Arial", Font.PLAIN, 20);
	frameDraw = new Timer (1000/60, this);
	frameDraw.start();
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
g.setColor(Color.BLACK);
g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
rocket.draw(g);
	}

	void drawEndState(Graphics g) {
g.setColor(Color.RED);
g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
g.setFont (endFont1);
g.setColor(Color.BLACK);
g.drawString("GAME OVER", 100, 150);
g.setFont (endFont2);
g.drawString("You killed 0 enemies", 130, 300);
g.setFont (endFont3);
g.drawString("Press ENTER to play again", 127, 450);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
	 	    updateEndState();
		}
		System.out.println("action");
		repaint ();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode () == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
			}else {
				currentState ++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    System.out.println("DOWN");
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    System.out.println("RIGHT");
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    System.out.println("LEFT");
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
