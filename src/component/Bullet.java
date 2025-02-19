package component;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bullet {
    
    private double x, y;
    private static final double SPEED = 5;
    private ArrayList<Image> bulletImage;
    private int imageIndex = 0;  // Track current animation frame
    private int frameCounter = 0; // Track when to switch frames
    private static final int FRAME_DELAY = 5; // Change image every 5 updates

    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
        bulletImage = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            this.bulletImage.add(new Image("MainWeapon" + i + ".png"));
        }
    }

    public boolean update() {
        y -= SPEED;
        frameCounter++;
        if (frameCounter >= FRAME_DELAY) {
            frameCounter = 0;
            imageIndex = (imageIndex + 1) % bulletImage.size(); // Loop through images
        }
        return y > 0;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(bulletImage.get(imageIndex), x, y, 55, 55);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
