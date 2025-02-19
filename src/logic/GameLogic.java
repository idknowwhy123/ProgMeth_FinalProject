package logic;

import java.util.ArrayList;
import java.util.List;
import component.Bullet;
import component.Chicken;
import component.PlayerShip;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameLogic {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static PlayerShip player;
	public static List<Bullet> bullets = new ArrayList<>();
	private static List<Chicken> chickens = new ArrayList<>();
	private static Spawner spawner = new Spawner();
	
	private static int enemySpawnTimer = 0;

	public static void init(PlayerShip playerShip) {
		player = playerShip;
		chickens.addAll(spawner.spawnEnemies(5, WIDTH));
	}

	public static void update() {

		player.update();
		bullets.removeIf(b -> !b.update());
		for(Chicken x : chickens) {
			x.update();
		}
		CollisionManager.checkCollisions(bullets, chickens);

		enemySpawnTimer++;
		if (enemySpawnTimer > 120) {
			chickens.addAll(spawner.spawnEnemies(3, WIDTH));
			enemySpawnTimer = 0;
		}
		
	}

	public static void render(GraphicsContext gc) {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(gc);
		bullets.forEach(b -> b.render(gc));
		chickens.forEach(c -> c.render(gc));
	}

	public static void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public static void updateBullets() {
		bullets.removeIf(bullet -> !bullet.update()); // Remove bullets that are off-screen
	}

	public static void renderBullets(GraphicsContext gc) {
		for (Bullet bullet : bullets) {
			bullet.render(gc);
		}
	}

	public static List<Bullet> getBullets() {
		return bullets;
	}

	public static List<Chicken> getChickens() {
		return chickens;
	}
}
