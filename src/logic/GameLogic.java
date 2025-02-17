package logic;

import java.util.ArrayList;
import java.util.List;
import component.Bullet;
import component.Chicken;
import component.PlayerShip;
import javafx.scene.canvas.GraphicsContext;

public class GameLogic {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private PlayerShip player;
    public static List<Bullet> bullets;
    private List<Chicken> chickens;
    private Spawner spawner;
    private CollisionManager collisionManager;
    private int enemySpawnTimer;

    public GameLogic() {
        bullets = new ArrayList<>();
        chickens = new ArrayList<>();
        spawner = new Spawner();
        collisionManager = new CollisionManager();
        enemySpawnTimer = 0;
    }

    public void init(PlayerShip player) {
        this.player = player;
        chickens.addAll(spawner.spawnEnemies(5, WIDTH));
    }

    public void update() {
      
        player.update();
        bullets.removeIf(b -> !b.update());
        chickens.removeIf(c -> c.update(bullets));
        collisionManager.checkCollisions(bullets, chickens);

        enemySpawnTimer++;
        if (enemySpawnTimer > 120) {
            chickens.addAll(spawner.spawnEnemies(3, WIDTH));
            enemySpawnTimer = 0;
        }
    }

    public void render(GraphicsContext gc) {
   
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

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Chicken> getChickens() {
        return chickens;
    }
}
