import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
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
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void update () {
		rocket.update();
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for ( int i = 0; i< aliens.size(); i++) {
			aliens.get(i).update();
		}
	}
	void draw (Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for ( int i = 0; i< aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
	}
	void checkCollisions () {
		for (int i = 0; i<aliens.size();i++) {
			for (int j = 0; j<projectiles.size(); j++) {
				aliens.get(i).collisionBox.intersects(projectiles.get(j).collisionBox);
			}
			aliens.get(i).collisionBox.intersects(rocket.collisionBox);
		}

	}
}
