package base.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Monster extends BaseComponent{
    private Image enemyImage;
    
    public Monster(double x, double y,Image enemyImage) {
    	super(x, y);
    	this.enemyImage = enemyImage;
    }

    public void update() {
    	move();
    }

	public abstract void move();

    public void render(GraphicsContext gc) {
        gc.drawImage(enemyImage, getX(), getY(),50,50);
    }
    
}
