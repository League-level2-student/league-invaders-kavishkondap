import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}

	void update() {
		super.update();
		y -= speed;
		if (y<=0) {
			isActive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
