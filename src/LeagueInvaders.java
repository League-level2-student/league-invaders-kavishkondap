import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 650;
	GamePanel panel;

	public static void main(String[] args) {
		LeagueInvaders stuff = new LeagueInvaders();
		stuff.setup();
	}

	LeagueInvaders() {
		this.frame = new JFrame();
		this.panel = new GamePanel();
	}

	public void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
	}
}
