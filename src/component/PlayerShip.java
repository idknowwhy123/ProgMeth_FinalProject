package component;

import base.component.BaseComponent;
import base.component.Collidable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import logic.Spawner;

public class PlayerShip extends BaseComponent{
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
		super(x, y);
		this.shipImage = new Image("MonkeyShip.png");
	}

	public void update() {
		if (left)
			setX(getX()-5);
		if (right)
			setX(getX()+5);
		if (up)
			setY(getY()-5);
		if (down)
			setY(getY()+5);

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
		if (key == KeyCode.LEFT || key == KeyCode.A)
			left = true;
		if (key == KeyCode.RIGHT || key == KeyCode.D)
			right = true;
		if (key == KeyCode.W || key == KeyCode.UP)
			up = true;
		if (key == KeyCode.S || key == KeyCode.DOWN)
			down = true;
		
		if(key == KeyCode.EQUALS) {
			GameLogic.getChickens().addAll(Spawner.spawnWave());
			GameLogic.setGameOver(true);
		}
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
