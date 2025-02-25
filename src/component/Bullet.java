package component;

import java.util.ArrayList;

import base.component.BaseComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
	
public class Bullet extends BaseComponent {
    private static final double SPEED = 5;
    private ArrayList<Image> bulletImage;
    private int imageIndex = 0;  // Track current animation frame
    private int frameCounter = 0; // Track when to switch frames
    private static final int FRAME_DELAY = 5; // Change image every 5 updates
    private int dmg = 1;

    public Bullet(double x, double y) {
        super(x, y);
        bulletImage = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            this.bulletImage.add(new Image("MainWeapon" + i + ".png"));
        }
    }

    public boolean update() {
    	this.setY(getY()-SPEED);
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
    
}
