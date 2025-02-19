package component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;

public class PlayerShip {

	private double x, y;
	private boolean left, right, up, down;
	private long lastShotTime = 0;
	private static final long SHOOT_INTERVAL = 200;
	private Image shipImage;

	public PlayerShip(double x, double y) {
		this.x = x;
		this.y = y;
		this.shipImage = new Image("MonkeyShip.png");
	}

	public void update() {

		if (left)
			x -= 5;
		if (right)
			x += 5;
		if (up)
			y -= 5;
		if (down)
			y += 5;

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
		GameLogic.addBullet(new Bullet(x + 20, y));
	}

	public void handleKeyPress(KeyCode key) {
		if (key == KeyCode.LEFT || key == KeyCode.A)
			left = true;
		if (key == KeyCode.RIGHT || key == KeyCode.D)
			right = true;
		if(key == KeyCode.W || key == KeyCode.UP)
			up = true;
		if(key == KeyCode.S || key == KeyCode.DOWN)
			down = true;
	}

	public void handleKeyRelease(KeyCode key) {
		if (key == KeyCode.LEFT || key == KeyCode.A)
			left = false;
		if (key == KeyCode.RIGHT || key == KeyCode.D)
			right = false;
		if(key == KeyCode.W || key == KeyCode.UP)
			up = false;
		if(key == KeyCode.S || key == KeyCode.DOWN)
			down = false;
	}

	
}
