package component;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {

	private double x, y;
	private static final double SPEED = 5;
	private ArrayList<Image> bulletImage;
	private int imageIndex = 0;
	private int frameCounter = 0;
	private static final int FRAME_DELAY = 5;
	private boolean active = true;

	public Bullet(double x, double y) {
		this.x = x;
		this.y = y;
		bulletImage = new ArrayList<>();
		
		for (int i = 1; i <= 4; i++) {
			this.bulletImage.add(new Image("MainWeapon" + i + ".png"));
		}
	}

	public boolean update() {
		if (!active) {
			return false;			
		}
		
		y -= SPEED;
		frameCounter++;
		
		if (frameCounter >= FRAME_DELAY) {
			frameCounter = 0;
			imageIndex = (imageIndex + 1) % bulletImage.size();
		}
		
		return y > 0;
	}

	public void render(GraphicsContext gc) {
		if (active) {
			gc.drawImage(bulletImage.get(imageIndex), x, y, 55, 55);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void deactivate() {
		active = false;
	}
}
