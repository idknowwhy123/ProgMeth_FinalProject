package component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;

public class PlayerShip {

	private double x, y;
	private boolean left, right, up, down;
	private long lastShotTime = 0;
	private Image shipImage;
	
	private static final long SHOOT_INTERVAL = 200;
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private static final int SPEED = 5;

	private static final int SCENE_WIDTH = GameLogic.getWidth();
	private static final int SCENE_HEIGHT = GameLogic.getHeight();


	public PlayerShip(double x, double y) {
		this.x = x;
		this.y = y;
		this.shipImage = new Image("MonkeyShip.png");
	}

	public void update() {
		// Check boundaries before updating position
		if (left && x > 0)
			x -= SPEED;
		if (right && x < SCENE_WIDTH - WIDTH)
			x += SPEED;
		if (up && y > 0)
			y -= SPEED;
		if (down && y < SCENE_HEIGHT - HEIGHT)
			y += SPEED;

		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShotTime > SHOOT_INTERVAL) {
			shoot();
			lastShotTime = currentTime;
		}
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(shipImage, x, y, WIDTH, HEIGHT);
	}

	private void shoot() {
		GameLogic.addBullet(new Bullet(x + WIDTH / 2, y));
	}

	public void handleKeyPress(KeyCode key) {
		if (key == KeyCode.LEFT || key == KeyCode.A)
			left = true;
		if (key == KeyCode.RIGHT || key == KeyCode.D)
			right = true;
		if (key == KeyCode.W || key == KeyCode.UP)
			up = true;
		if (key == KeyCode.S || key == KeyCode.DOWN)
			down = true;
	}

	public void handleKeyRelease(KeyCode key) {
		if (key == KeyCode.LEFT || key == KeyCode.A)
			left = false;
		if (key == KeyCode.RIGHT || key == KeyCode.D)
			right = false;
		if (key == KeyCode.W || key == KeyCode.UP)
			up = false;
		if (key == KeyCode.S || key == KeyCode.DOWN)
			down = false;
	}
}
