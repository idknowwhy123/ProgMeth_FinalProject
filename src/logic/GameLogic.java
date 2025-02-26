package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import application.GameApp;
import base.component.BaseComponent;
import base.component.Enemy;
import base.component.SpawnPos;
import component.Bullet;
import component.PlayerShip;
import gui.GameScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLogic {
	static Random random = new Random();
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;

	private static int score = 0;

	private static List<Bullet> bullets = new ArrayList<>();
	private static List<Enemy> enemies = new ArrayList<>();

	private static boolean gameOver = false;

	public static void init() {
		GameLogic.setScore(0);
		GameLogic.setGameOver(false);
		GameLogic.bullets.clear();
		GameLogic.enemies.clear();
		GameLogic.isSpawning = false;
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

	public static boolean inLevel = false;
	public static boolean isSpawning = false;

	public static void spawnLevel() {

		if (inLevel == false && isSpawning == false) {
			inLevel = true;
			isSpawning = true;
			System.out.println("progress Level");

			/////////////////////level control////////////////////////
			int randomDelay = random.nextInt(3);
			int randomDuration = random.nextInt(4) + 2;
			SpawnPos randomSpawnPos = SpawnPosUtil.getRandomSpawnPos();
			
			addAllEnemyByLine(Spawner.spawnSquare(randomDelay, randomSpawnPos , randomDuration));
			System.out.println("deley : " + randomDelay);
			System.out.println("duration : " + randomDuration);
			System.out.println("Spawn from : " + randomSpawnPos);
			
			
			
			//////////////////////////////////////////////////////////
			Thread delayTime = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						isSpawning = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
//		System.out.println(enemies.size());
		enemies.removeIf(enemy -> !enemy.update()); // Remove enemies off-screen
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
				}
			}
		}
	}

	public static void checkPlayerCollisions() {
		Iterator<Enemy> enemyIterator = enemies.iterator();
		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();

			if (isColliding(GameScreen.getPlayerShip(), enemy)) {
				enemy.setHp(enemy.getHp() - 5);
				GameScreen.getPlayerShip().setHp(GameScreen.getPlayerShip().getHp() - 1); // decrease hp 1.
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

	private static boolean isInScreen(BaseComponent bc) {
		if (bc.getX() >= 0 && bc.getX() <= GameLogic.getWidth() && bc.getY() >= 0
				&& bc.getY() <= GameLogic.getHeight()) {
			return true;
		}
		return false;
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

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		GameLogic.gameOver = gameOver;
	}

}
