import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font infoFont;
	Timer frameDraw;
	Rocketship rocket;
	ObjectManager object;
	GamePanel() {
		titleFont= new Font("Arial",Font.PLAIN,48);
		infoFont= new Font("Arial",Font.PLAIN,24);
		frameDraw= new Timer(1000/60,this);
		frameDraw.start();
		rocket=new Rocketship(250,700,50,50);
		object= new ObjectManager(rocket);
	}
	@Override
	public void paintComponent(Graphics g) {
		if(currentState==MENU) {
			drawMenuState(g);
		}
		else if(currentState==GAME) {
			drawGameState(g);
		}
		else if(currentState==END) {
			drawEndState(g);
		}
	}
	void updateMenuState() {
		
	}
	void updateGameState() {
		
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS ", 20, 120);
		g.setFont(infoFont);
		g.drawString("Press ENTER to start",125, 300);
		g.drawString("Press SPACE for instructions",80, 500);
	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		object.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("Game Over", 50, 120);
		g.setFont(infoFont);
		g.drawString("You killed ____ enemies", 125, 300);
		g.drawString("Press ENTER to restart", 80, 500);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(currentState==MENU) {
			updateMenuState();
		}
		else if(currentState==GAME) {
			updateGameState();
		}
		else if(currentState==END) {
			updateEndState();
		}
		System.out.println("action");
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState ==END) {
				currentState=MENU;
			}
			else {
				currentState ++;
			}
		}
		if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			rocket.up();
			System.out.println("UP");
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.down();
			System.out.println("DOWN");
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.right();
			System.out.println("RIGHT");
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.left();
			System.out.println("LEFT");
		}
		if(rocket.y>LeagueInvaders.HEIGHT) {
			rocket.y=LeagueInvaders.HEIGHT;
		}
		else if(rocket.y<0) {
			rocket.y=0;
		}
		if(rocket.x>LeagueInvaders.WIDTH) {
			rocket.x=LeagueInvaders.WIDTH;
		}
		else if(rocket.x<0) {
			rocket.x=0;
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
