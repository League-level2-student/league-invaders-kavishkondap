import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
	Font gameFont;
	Font endFont1;
	Font endFont2;
	Font endFont3;
	Timer frameDraw;
	Rocketship rocket = new Rocketship(250, 550, 50, 50);
	ObjectManager manager = new ObjectManager(rocket);
	long lastOneSpawned;
	long counter;
	long finalScore;
	boolean hasLost = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public static int hardOrEasy;
	public static boolean isHard;
	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		startFont = new Font("Arial", Font.PLAIN, 24);
		instructionFont = new Font("Arial", Font.PLAIN, 20);
		gameFont = new Font ("Arial", Font.PLAIN, 20);
		endFont1 = new Font("Arial", Font.PLAIN, 48);
		endFont2 = new Font("Arial", Font.PLAIN, 24);
		endFont3 = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("space.png");
		}
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
		manager.update();
		manager.checkCollisions();
		manager.purgeObjects();
		long time = System.currentTimeMillis();
		// figure this out
		counter++;
		//System.out.println(counter);
		if (counter % 10 == 0) {
			manager.addAlien();
		}
		if (!rocket.isActive) {
			currentState = END;
			finalScore = manager.score;
			Rocketship rocket = new Rocketship(250, 550, 50, 50);
			ObjectManager manager = new ObjectManager(rocket);
			this.rocket = rocket;
			this.manager = manager;
		}

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
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
//		String mode = JOptionPane.showInputDialog("Easy or Hard Mode?");
//		if (mode.equalsIgnoreCase("easy")) {
//			hardOrEasy = 2;
//			isHard = false;
//		}else {
//			hardOrEasy = 4;
//			isHard = true;
//		}
		g.setFont(gameFont);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: " + manager.getScore(),10 , LeagueInvaders.HEIGHT- 50);
		manager.draw(g);
		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(endFont1);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 150);
		g.setFont(endFont2);
		g.drawString("You killed " + finalScore + " enemies", 130, 300);
		g.setFont(endFont3);
		g.drawString("Press ENTER to play again", 127, 450);

	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		//System.out.println("action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState != GAME) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (currentState == END) {
					currentState = MENU;
				} else {
					currentState++;
				}
			}
		}
		if (currentState == MENU) {
			if (e.getKeyCode()== KeyEvent.VK_SPACE) {
				JOptionPane.showMessageDialog(null, "How to play: \nPress the arrow keys to move around and space to shoot \nDon't get hit by aliens or let any pass you!\nGood Luck!");
			}
		}
		if (currentState == GAME) {
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (rocket.y - 10 > 0) {
					rocket.up();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (rocket.y + 60 < LeagueInvaders.HEIGHT) {
					rocket.down();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (rocket.x + 60 < LeagueInvaders.WIDTH) {
					rocket.right();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (rocket.x - 10 > 0) {
					rocket.left();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				manager.addProjectile(new Projectile(rocket.x + rocket.width / 2 - 5, rocket.y - 5, 10, 10));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (rocket.y - 10 > 0) {
					rocket.up();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (rocket.y + 60 < LeagueInvaders.HEIGHT) {
					rocket.down();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (rocket.x + 60 < LeagueInvaders.WIDTH) {
					rocket.right();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (rocket.x - 10 > 0) {
					rocket.left();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
