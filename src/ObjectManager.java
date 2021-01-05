import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	public int score;

	ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
		score = 0;
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH - 60) + 10, 0, 50, 50));
	}

	void update() {
		rocket.update();
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}
	}

	int getScore() {
		return score;

	}

	void draw(Graphics g) {
		rocket.draw(g);
		//System.out.println(aliens.size());
		//System.out.println(projectiles.size());
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
	}

	void checkCollisions() {
		OUTER: for (int i = 0; i < aliens.size(); i++) {
			for (int j = 0; j < projectiles.size(); j++) {
				if (aliens.get(i).collisionBox.intersects(projectiles.get(j).collisionBox)) {
					score++;
					aliens.remove(i);
					i--;
					projectiles.remove(j);
					continue OUTER;
				}

			}
			if (aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				rocket.isActive = false;
			}

		}
	}

	void purgeObjects() {

		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).y >= LeagueInvaders.HEIGHT) {
				aliens.remove(i);
				i--;
				rocket.isActive = false;
				break;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).y <= 0) {
				projectiles.remove(i);
				i--;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

//1,2,3,4,5
