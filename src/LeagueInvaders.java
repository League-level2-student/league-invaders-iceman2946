import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int WIDTH=500;
	public static final int HEIGHT=800;
	GamePanel panel;
	LeagueInvaders(){
		frame= new JFrame();
	}
	void setup(){
		panel= new GamePanel();
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		LeagueInvaders invade= new LeagueInvaders();
		invade.setup();
	}
}
