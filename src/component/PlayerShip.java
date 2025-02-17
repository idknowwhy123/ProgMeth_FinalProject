package component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import application.*;


public class PlayerShip {
    private double x, y;
    private boolean left, right;
    private long lastShotTime = 0;
    private static final long SHOOT_INTERVAL = 200;
    private Image shipImage;

    public PlayerShip(double x, double y) {
        this.x = x;
        this.y = y;
        this.shipImage = new Image("MonkeyShip.png");
    }

    public void update() {
    	
        if (left) x -= 5;
        if (right) x += 5;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > SHOOT_INTERVAL) {
            shoot();
            lastShotTime = currentTime;
        }
        
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(shipImage, x, y, 100, 100);
    }

    private void shoot() {
        Main.bullets.add(new Bullet(x + 20, y));
    }

    public void handleKeyPress(KeyCode key) {
        if (key == KeyCode.LEFT) left = true;
        if (key == KeyCode.RIGHT) right = true;
    }

    public void handleKeyRelease(KeyCode key) {
        if (key == KeyCode.LEFT) left = false;
        if (key == KeyCode.RIGHT) right = false;
    }
}
