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
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int score= 0;
	Timer alienSpawn;
	GamePanel() {
		titleFont= new Font("Arial",Font.PLAIN,48);
		infoFont= new Font("Comic Sans",Font.PLAIN,24);
		frameDraw= new Timer(1000/60,this);
		frameDraw.start();
		rocket=new Rocketship(250,700,50,50);
		object= new ObjectManager(rocket);
		loadImage("space.png");
		
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
	void updateMenuState() {
		
	}
	void updateGameState() {
		object.update();
		if(rocket.isActive==false) {
			currentState=END;
			rocket= new Rocketship(250,700,50,50);
			score=object.getScore();
			object= new ObjectManager(rocket);
		}
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
		if(gotImage) {
			g.drawImage(image,0,0,LeagueInvaders.WIDTH,LeagueInvaders.HEIGHT,null);
		}
		object.draw(g);
		g.setColor(Color.GREEN);
		g.setFont(infoFont);
		g.drawString("Score: "+object.getScore(),10,20);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("Game Over", 60, 120);
		g.setFont(infoFont);
		g.drawString("You killed "+score+" enemies", 125, 300);
		g.drawString("Press ENTER to restart", 80, 500);
	}
	void startGame(int milliseconds,ActionListener object) {
		alienSpawn= new Timer(milliseconds, object);
		alienSpawn.start();
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
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState ==END) {
				currentState=MENU;
			}
			else if(currentState==MENU) {
				currentState=GAME;
				startGame(1000,object);
			}
			else if(currentState==GAME) {
				currentState=END;
				alienSpawn.stop();
				
			}
			else {
				currentState ++;
			}
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE&& currentState==MENU) {
			JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE&& currentState==GAME) {
			object.addProjectile(rocket.getProjectile());
		}
		if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			rocket.movingUp=true;
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.movingDown=true;
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.movingRight=true;
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.movingLeft=true;
		}
		if(rocket.y>LeagueInvaders.HEIGHT-rocket.height) {
			rocket.y=LeagueInvaders.HEIGHT-rocket.height;
		}
		else if(rocket.y<0+rocket.height) {
			rocket.y=0+rocket.width;
		}
		if(rocket.x>LeagueInvaders.WIDTH-rocket.width) {
			rocket.x=LeagueInvaders.WIDTH-rocket.width;
		}
		else if(rocket.x<0+rocket.width) {
			rocket.x=0+rocket.width;
		}
	}
		
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.movingRight=false;
		}
		if(arg0.getKeyCode()== KeyEvent.VK_LEFT) {
			rocket.movingLeft=false;
		}
		if(arg0.getKeyCode()== KeyEvent.VK_UP) {
			rocket.movingUp=false;
		}
		if(arg0.getKeyCode()== KeyEvent.VK_DOWN) {
			rocket.movingDown=false;
		}
		
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}                                   
