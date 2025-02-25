package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import base.component.BaseComponent;
import base.component.Enemy;
import component.Bullet;
import gui.GameScreen;

public class GameLogic {
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;

	private static List<Bullet> bullets = new ArrayList<>();
	private static List<Enemy> enemies = new ArrayList<>();

	public static void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public static List<Bullet> getBullets() {
		return bullets;
	}

	// for testing
	public static void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public static boolean inLevel = false;
	public static boolean isSpawning = false;

	public static void spawnLevel() {

		if (inLevel == false && isSpawning == false) {
			inLevel = true;
			isSpawning = true;
			System.out.println("progress");
			addAllEnemyByLine(Spawner.spawnSquare());

			
			
			
		}

	}
	
	public static Thread spawnThread;
	public static void addAllEnemyByOne(List<Enemy> toAddEnemies) {

		// use thread for time control
		spawnThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (Enemy enemy : toAddEnemies) {
					enemies.add(enemy);
					try {
						Thread.sleep(450);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		spawnThread.start();
	}

	public static void addAllEnemyByLine(List<List<Enemy>> toAddEnemies) {

		// use thread for time control
		spawnThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (List<Enemy> enemyLine : toAddEnemies) {
					for(Enemy enemy:enemyLine) {
						
						// For pause game
						if(GameScreen.isPaused == true) {
							try {
								Thread.sleep(3600000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						///////////////
						
						enemies.add(enemy);
						try {
							Thread.sleep(450);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				isSpawning = false;
			}
		});
		spawnThread.start();
	}

	public static List<Enemy> getEnemies() {
		return enemies;
	}

	public static void updateBullets() {
		bullets.removeIf(bullet -> !bullet.update()); // Remove bullets off-screen
	}

	public static void updateEnemies() {
		enemies.removeIf(enemy -> !enemy.update()); // Remove enemies off-screen
	}

	public static void checkCollisions() {
		Iterator<Bullet> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = bulletIterator.next();
			Iterator<Enemy> enemyIterator = enemies.iterator();

			while (enemyIterator.hasNext()) {
				Enemy enemy = enemyIterator.next();

				if (isColliding(bullet, enemy)) {
					bulletIterator.remove(); // Remove bullet
					enemy.setHp(enemy.getHp() - bullet.getDmg());
//					enemyIterator.remove(); // Remove enemy
					break; // Move to the next bullet
				}
				if (enemy.getHp() == 0) {
					enemyIterator.remove();
				}
			}
		}
	}

	private static boolean isColliding(BaseComponent a, BaseComponent b) {
		return a.getX() < b.getX() + 80 && a.getX() + 55 > b.getX() && a.getY() < b.getY() + 80
				&& a.getY() + 55 > b.getY();
	}

	public static void updateEndLevel() {

		if (enemies.size()==0 && isSpawning == false) {
			inLevel = false;
			Timer timer = new Timer();
//			System.out.println("Level ended.");
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					
					GameLogic.spawnLevel();
					
					timer.cancel(); // Stop the timer after execution
				}
			}, 3000); // Delay of 3 seconds
			
		}
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

}
