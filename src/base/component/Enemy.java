package base.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public abstract class Enemy extends BaseComponent{
    private Image enemyImage;
    private double tagPointX;
    private double tagPointY;
    private int duration;//Ms
    private EnemyState state;
    private SpawnPos spawnFrom;
    
    public Enemy(double x, double y, double tagPointX, double tagPointY,SpawnPos pos,Image enemyImage) {
    	super(x, y);
    	this.tagPointX = tagPointX;
    	this.tagPointY = tagPointY;
    	this.enemyImage = enemyImage;
    	this.duration = 1000;
    	state = EnemyState.SPAWN;
    	spawnFrom = pos;
    }

    public boolean update() {
    	move();
    	if(isNear() && state == EnemyState.SPAWN){
    		this.setState(EnemyState.HOLD);
//    		System.out.println("" + this.getX() + ", " + this.getY());
    	}
    	if(state == EnemyState.HOLD) {
    		duration -= 1;
//    		System.out.println(duration);
    	}
    	if(duration <= 0) {
    		this.setState(EnemyState.DESPAWN);
    	}
    	
    	return getY() < GameLogic.getHeight();
    }
    
	public abstract void move();
	public abstract void moveTo();
	public abstract void moveSet();
	public abstract void moveDown();

    public void render(GraphicsContext gc) {
        gc.drawImage(enemyImage, getX(), getY(),50,50);
    }

	public EnemyState getState() {
		return state;
	}

	public void setState(EnemyState state) {
		this.state = state;
	}

	public SpawnPos getSpawnFrom() {
		return spawnFrom;
	}

	public void setSpawnFrom(SpawnPos spawnFrom) {
		this.spawnFrom = spawnFrom;
	}
	
	public boolean isNear() {
		if(Math.abs(this.tagPointX-this.getX()) <= 5 && Math.abs(this.tagPointY-this.getY()) <= 5) {
			return true;
		}
		return false;
	}
    
}
