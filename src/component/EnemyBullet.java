package component;

import java.util.ArrayList;

import base.component.BaseComponent;
import base.component.Breakable;
import base.component.Enemy;
import base.component.EnemyState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public class EnemyBullet extends Enemy implements Breakable{
	private ArrayList<Image> bulletImage;
	private int imageIndex = 0; // Track current animation frame
	private int frameCounter = 0; // Track when to switch frames
	private static final int FRAME_DELAY = 5; // Change image every 5 updates
	private double speed;

	public EnemyBullet(double x, double y, double speed, int damage) {
		super(10, x, y, speed, speed, 10, new Image("Banana.png"));
		this.setSpeed(speed);
		this.setHp(100);
		bulletImage = new ArrayList<>();
		this.setState(EnemyState.DESPAWN);
		for (int i = 1; i <= 4; i++) {
			this.bulletImage.add(new Image("MainWeapon" + i + ".png"));
		}
	}

	@Override
	public boolean update() {
		this.setY(this.getY() + speed); // Move down
		
		frameCounter++;
		if (frameCounter >= FRAME_DELAY) {
			frameCounter = 0;
			imageIndex = (imageIndex + 1) % bulletImage.size(); // Loop through images
		}
		return getY() < GameLogic.getHeight();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(bulletImage.get(imageIndex), getX(), getY(), 55, 55);
	}

	@Override
	public void move() {
		
	}

	@Override
	public void moveTo() {
		
	}

	@Override
	public void moveSet() {
		
	}

	@Override
	public void moveDown() {
		
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
