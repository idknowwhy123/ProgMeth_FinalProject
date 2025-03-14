package component;

import java.util.ArrayList;

import base.component.BaseComponent;
import base.component.Breakable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends BaseComponent implements Breakable{
	private ArrayList<Image> bulletImage;
	private int imageIndex = 0; // Track current animation frame
	private int frameCounter = 0; // Track when to switch frames
	private static final int FRAME_DELAY = 5; // Change image every 5 updates
	private int dmg;
	private double speed;

	public Bullet(double x, double y, double speed, int damage) {
		super(x, y);
		this.dmg = damage;
		this.setSpeed(speed);
		bulletImage = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			this.bulletImage.add(new Image("MainWeapon" + i + ".png"));
		}
	}

	public boolean update() {
		this.setY(this.getY() - speed); // Move up
		
		frameCounter++;
		if (frameCounter >= FRAME_DELAY) {
			frameCounter = 0;
			imageIndex = (imageIndex + 1) % bulletImage.size(); // Loop through images
		}
		return getY() > 0;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(bulletImage.get(imageIndex), getX(), getY(), 55, 55);
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
