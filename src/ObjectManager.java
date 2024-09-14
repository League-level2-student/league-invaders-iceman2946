import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList <Projectile> projectile;
	ArrayList <Alien> aliens;
	Random random;
	ObjectManager(Rocketship rocket){
		this.rocket=rocket;
		projectile= new ArrayList<Projectile>();
		aliens= new ArrayList<Alien>();
		random= new Random();
	}
	void addProjectile(Projectile p) {
		projectile.add(p);
	}
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	void update() {
		for(int i=0; i<aliens.size();i++) {
			aliens.get(i).update();
			if(aliens.get(i).y>LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive=false;
			}
		}
		for(int i=0; i<projectile.size();i++) {
			projectile.get(i).update();
			if(projectile.get(i).y>LeagueInvaders.HEIGHT) {
				projectile.get(i).isActive=false;
			}
		}
	}
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i=0; i<aliens.size();i++) {
			aliens.get(i).draw(g);
		}
		for(int i=0; i<projectile.size();i++) {
			projectile.get(i).draw(g);
		}
	}
	void purgeObjects() {
		for(int i=0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive=false) {
				aliens.remove(i);
			}
		}
		for(int i=0; i<projectile.size(); i++) {
			if(projectile.get(i).isActive=false) {
				projectile.remove(i);
			}
		}
	}
}
