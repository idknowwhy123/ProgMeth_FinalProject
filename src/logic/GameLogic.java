package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import base.component.BaseComponent;
import base.component.Breakable;
import base.component.Enemy;
import base.component.EventSpawn;
import base.component.HoldingMoveSet;
import base.component.SpawnPos;
import component.Boss;
import component.Bullet;
import component.EnemyBullet;
import gui.GameScreen;
import javafx.scene.image.Image;

public class GameLogic {
	static Random random = new Random();
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;

	private static int score = 0;
	private static int level = 1;

	private static List<Bullet> bullets = new ArrayList<>();
	private static List<Enemy> enemies = new ArrayList<>();
	private static Enemy boss;
//	private static List<EnemyBullet> enemyBullets = new ArrayList<>();

	private static boolean gameOver = false;

	public static void init() {
		GameLogic.setScore(0);
		GameLogic.setGameOver(false);
		GameLogic.bullets.clear();
		GameLogic.enemies.clear();
		GameLogic.isSpawning = false;
		GameLogic.level = 1;
	}

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
	
//	Rain : enemies.addAll(Spawner.spawnRainMeteor());
//	Square : addAllEnemyByLine(Spawner.spawnTriangle(randomSpawnPos, randomDuration, randomHolding));
//	Triangle : addAllEnemyByLine(Spawner.spawnSquare(randomDelay, randomSpawnPos, randomDuration, randomHolding));

	public static boolean inLevel = false;
	public static boolean isSpawning = false;

	public static void spawnLevel() {

		if (inLevel == false && isSpawning == false && GameLogic.getLevel() == 5) {
			inLevel = true;
			isSpawning = true;
			
			System.out.println("progress Level");
			System.out.println("=======  BOSS LEVEL " + level + "  =======");
			System.out.println("Boss health : " + GameLogic.getLevel() * 100);
			System.out.println("Score : " + GameLogic.getLevel() * 100);
			System.out.println("==============================");

			boss = new Boss(level * 100, 800, 200, new Image("Banana.png"));
			enemies.add(boss);
			level++;
//////////////////////////////////////////////////////////
			Thread delayTime = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						isSpawning = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			delayTime.run();
		} else if (inLevel == false && isSpawning == false) {
			inLevel = true;
			isSpawning = true;
			
			System.out.println("progress Level");

			// RANDOM EVENT
			EventSpawn event = GameUtil.getRandomEventSpawn();

			///////////////////// level control////////////////////////
			int randomDelay = random.nextInt(3);
			int randomDuration = random.nextInt(4) + 2;
			SpawnPos randomSpawnPos = GameUtil.getRandomSpawnPos();
			HoldingMoveSet randomHolding = GameUtil.getRandomHoldingMoveSet();

			System.out.println("=======  LEVEL " + level + "  =======");
			
			switch (event) {
			case TRIANGLE:
				System.out.println("Event : " + event);
				System.out.println("Deley : " + randomDelay);
				System.out.println("Duration : " + randomDuration);
				System.out.println("Spawn from : " + randomSpawnPos);
				System.out.println("Holding set : " + randomHolding);
				System.out.println("number of chicken : " + enemies.size());
				System.out.println("=========================");
				System.out.println("");
				addAllEnemyByLine(Spawner.spawnTriangle(randomSpawnPos, randomDuration, randomHolding));
				break;
			case SQUARE:
				System.out.println("Event : " + event);
				System.out.println("Deley : " + randomDelay);
				System.out.println("Duration : " + randomDuration);
				System.out.println("Spawn from : " + randomSpawnPos);
				System.out.println("Holding set : " + randomHolding);
				System.out.println("number of chicken : " + enemies.size());
				System.out.println("=========================");
				System.out.println("");
				addAllEnemyByLine(Spawner.spawnSquare(randomDelay, randomSpawnPos, randomDuration, randomHolding));
				break;
			case RAIN:
				System.out.println("Event : " + event);
				System.out.println("Deley : " + randomDelay);
				System.out.println("Duration : " + randomDuration);
				System.out.println("Spawn from : " + randomSpawnPos);
				System.out.println("Holding set : " + randomHolding);
				System.out.println("number of chicken : " + enemies.size());
				System.out.println("=========================");
				System.out.println("");
				enemies.addAll(Spawner.spawnRainMeteor());
				break;

			default:
				System.out.println("Event : " + event);
				System.out.println("number of meteor : " + enemies.size());
				System.out.println("=========================");
				break;
			}
			level++;
			//////////////////////////////////////////////////////////
			Thread delayTime = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						isSpawning = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			delayTime.run();
		}

	}

	public static void addAllEnemyByLine(List<List<Enemy>> toAddEnemies) {
		for (List<Enemy> enemylines : toAddEnemies) {
			enemies.addAll(enemylines);
		}
	}

	public static List<Enemy> getEnemies() {
		return enemies;
	}

	public static void updateBullets() {
		bullets.removeIf(bullet -> !bullet.update()); // Remove bullets off-screen
	}

	public static void updateEnemies() {

		enemies.removeIf(enemy -> {
			boolean tmp = !enemy.update();
			if (tmp && !(enemy instanceof EnemyBullet)) {
				GameLogic.setScore(GameLogic.getScore() - 1);
				if (GameLogic.getScore() <= -1) {
					System.out.println("game over");
					GameLogic.setGameOver(true);
				}
				System.out.println(tmp);
			}
			return tmp;
		});
	}

	public static void checkBulletCollisions() {
		Iterator<Bullet> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = bulletIterator.next();
			Iterator<Enemy> enemyIterator = enemies.iterator();

			while (enemyIterator.hasNext()) {
				Enemy enemy = enemyIterator.next();

				if (isColliding(bullet, enemy)) {
					bulletIterator.remove(); // Remove bullet
					enemy.setHp(enemy.getHp() - bullet.getDmg());
					
					break; // Move to the next bullet
				}
				if (enemy.getHp() <= 0) {
					enemyIterator.remove(); // Remove chicken
					GameLogic.setScore(getScore() + enemy.getGiveScore());
					GameLogic.setBoss(null);
				}
			}
		}
	}

	public static void checkPlayerCollisions() {
		Iterator<Enemy> enemyIterator = enemies.iterator();
		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();

			if (isColliding(GameScreen.getPlayerShip(), enemy) && GameScreen.getPlayerShip().isCanTakeDmg()) {
				GameScreen.getPlayerShip().setCanTakeDmg(false);
				if (enemy instanceof Breakable) {
					enemy.setHp(-1);
				} else {
					enemy.setHp(enemy.getHp() - 5);
				}

				GameScreen.getPlayerShip().takeDmg(enemy.getImpactDmg());// decrease hp
				System.out.println("Remain HP : " + GameScreen.getPlayerShip().getHp());
			}

			if (GameScreen.getPlayerShip().getHp() <= 0) {
				System.out.println("game over");
				GameLogic.setGameOver(true);
			}

			if (enemy.getHp() <= 0) {
				enemyIterator.remove();
				GameLogic.setScore(getScore() + enemy.getGiveScore());
			}
		}
	}

	private static boolean isColliding(BaseComponent a, BaseComponent b) {
		return a.getX() < b.getX() + 50 && a.getX() + 50 > b.getX() && a.getY() < b.getY() + 50
				&& a.getY() + 50 > b.getY();
	}

	public static void updateEndLevel() {

		if (enemies.size() == 0) {
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

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameLogic.score = score;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		GameLogic.level = level;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		GameLogic.gameOver = gameOver;
	}

	public static Enemy getBoss() {
		if(boss == null)return null;
		return boss;
	}

	public static void setBoss(Enemy boss) {
		GameLogic.boss = boss;
	}

}
