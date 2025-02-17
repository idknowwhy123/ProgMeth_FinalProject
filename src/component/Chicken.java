package component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Chicken {
    private double x, y;

    public Chicken(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean update(List<Bullet> bullets) {
        for (Bullet b : bullets) {
            if (b != null && x < b.getX() + 35 && x + 50 > b.getX() && y < b.getY() + 35 && y + 50 > b.getY()) {
                return true;
            }
        }
        return false;
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
        gc.setFill(Color.RED);
        gc.fillOval(x, y, 50, 50);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50); // A 35x35 rectangle representing the bullet's area
    }
}
