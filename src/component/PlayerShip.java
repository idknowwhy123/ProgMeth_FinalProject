package component;

import base.component.BaseComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import logic.Spawner;

public class PlayerShip extends BaseComponent {
	private boolean left, right, up, down;
	private long lastShotTime = 0;
	private Image shipImage;

	private static final long SHOOT_INTERVAL = 200;
	private static final int SPEED = 5;

	public PlayerShip(double x, double y) {
		super(x, y);
		this.shipImage = new Image("MonkeyShip.png");
	}

	public void update() {
		if (left && getX() - SPEED >= 0)
			setX(getX() - SPEED);
		if (right && getX() + SPEED <= GameLogic.getWidth()-100)
			setX(getX() + SPEED);
		if (up && getY() - SPEED >= 0)
			setY(getY() - SPEED);
		if (down && getY() + SPEED <= GameLogic.getHeight()-100)
			setY(getY() + SPEED);
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShotTime > SHOOT_INTERVAL) {
			shoot();
			lastShotTime = currentTime;
		}
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(shipImage, getX(), getY(), 100, 100);
	}

	private void shoot() {
		GameLogic.addBullet(new Bullet(getX() + 20, getY()));
	}

	public void handleKeyPress(KeyCode key) {
		if (key == KeyCode.A)
			left = true;
		if (key == KeyCode.D)
			right = true;
		if (key == KeyCode.W)
			up = true;
		if (key == KeyCode.S)
			down = true;
	}

	public void handleKeyRelease(KeyCode key) {
		if (key == KeyCode.A)
			left = false;
		if (key == KeyCode.D)
			right = false;
		if (key == KeyCode.W)
			up = false;
		if (key == KeyCode.S)
			down = false;
	}
}
