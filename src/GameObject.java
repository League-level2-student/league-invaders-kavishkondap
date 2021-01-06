import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed;
	boolean isActive;
	Rectangle collisionBox;
	GameObject(int x, double y2, int width, int height) {
		this.x = x;
		this.y = (int) y2;
		this.width = width;
		this.height = height;
		speed = 15;
		this.collisionBox = new Rectangle ( x, (int) y2, width, height);
		isActive = true;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	void draw (Graphics g) {
		
	}
}
