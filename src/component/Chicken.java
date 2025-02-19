package component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Chicken {
	
    private double x, y;
    private Image enemyImage;

    public Chicken(double x, double y) {
        this.x = x;
        this.y = y;
        this.enemyImage = new Image("Banana.png");
    }

    public void update() {
    	move();
    }

    public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void move() {
        y += 2;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(enemyImage, x, y,50,50);
    }
    
}
