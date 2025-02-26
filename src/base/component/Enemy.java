package base.component;

import java.util.Random;

import component.PlayerShip;
import gui.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;

public abstract class Enemy extends BaseComponent {
	private Image enemyImage;
	private double tagPointX;
	private double tagPointY;
	private int duration;// Ms
	private EnemyState state;
	private SpawnPos spawnFrom;
	private int GiveScore;

	private Random random = new Random();

	public Enemy(int give, double x, double y, double tagPointX, double tagPointY, SpawnPos pos, Image enemyImage) {
		super(x, y);
		this.tagPointX = tagPointX;
		this.tagPointY = tagPointY;
		this.enemyImage = enemyImage;
		state = EnemyState.SPAWN;
		spawnFrom = pos;
		this.GiveScore = give;
	}
	
	

	public boolean update() {
		move();
		if (isNear() && state == EnemyState.SPAWN) {
			this.setState(EnemyState.HOLD);
//    		System.out.println("" + this.getX() + ", " + this.getY());
		}
		if (state == EnemyState.HOLD) {
			duration -= 1;
//    		System.out.println(duration);
		}
		if (duration <= 0) {
			this.setState(EnemyState.DESPAWN);
		}

		int randomNum = random.nextInt(10);
		if (randomNum == 5) {

		}
		
		return getY() < GameLogic.getHeight();

	}

	public abstract void move();

	public abstract void moveTo();

	public abstract void moveSet();

	public abstract void moveDown();

	public void render(GraphicsContext gc) {
		gc.drawImage(enemyImage, getX(), getY(), 50, 50);
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
		if (Math.abs(this.tagPointX - this.getX()) <= 5 && Math.abs(this.tagPointY - this.getY()) <= 5) {
			return true;
		}
		return false;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getGiveScore() {
		return GiveScore;
	}

	public double getTagPointX() {
		return tagPointX;
	}

	public double getTagPointY() {
		return tagPointY;
	}

	public void setTagPointX(double tagPointX) {
		this.tagPointX = tagPointX;
	}

	public void setTagPointY(double tagPointY) {
		this.tagPointY = tagPointY;
	}

	public void holdCircle() {
		double radius = 20; // Adjust for the size of movement
		double speed = 0.006; // Rotation speed
		double time = System.currentTimeMillis() * speed; // Using time to move

		this.setX(this.getTagPointX() + radius * Math.cos(time));
		this.setY(this.getTagPointY() + radius * Math.sin(time));
	}
	
	public void holdHalfCircle() {
		double radius = 20; // Adjust for the size of movement
		double speed = 0.006; // Rotation speed
		double time = System.currentTimeMillis() * speed; // Using time to move

		this.setX(this.getTagPointX() + radius * Math.cos(time));
		this.setY(this.getTagPointY() + radius * (0 - Math.abs(Math.sin(time))));
	}

	public void holdSineWave() {
		double amplitude = 40; // Max distance from center
	    double speed = 0.005; // Movement speed
	    double time = System.currentTimeMillis() * speed;

	    this.setX(this.getTagPointX() + amplitude * Math.sin(time));
	    this.setY(this.getTagPointY() + Math.abs(amplitude/2 * Math.sin(time)));
	}
	
	public void holdJitter() {
	    double jitterRange = 10; // How much it can move in each direction randomly
	    this.setX(this.getTagPointX() + (Math.random() * jitterRange * 2 - jitterRange));
	    this.setY(this.getTagPointY() + (Math.random() * jitterRange * 2 - jitterRange));
	}

	public void holdZigzag() {
	    double amplitude = 20;  // Zigzag width
	    double speed = 0.005;    // Movement speed
	    double time = System.currentTimeMillis() * speed;
	    System.out.println("z");

	    this.setX(this.getTagPointX() + amplitude * Math.sin(time));
	    this.setY(this.getTagPointY() + amplitude * Math.cos(time));
	}

	private double wanderAngle = 0; // Stores current wandering angle
	public void holdWander() {
	    double speed = 0.2; // Movement speed
	    double radius = 30; // Max wander distance

	    wanderAngle += (Math.random() * 0.2 - 0.1); // Small random angle changes
	    this.setX(this.getX() + speed * Math.cos(wanderAngle));
	    this.setY(this.getY() + speed * Math.sin(wanderAngle));

	    // Keep the chicken near tagX, tagY
	    if (Math.hypot(this.getX() - this.getTagPointX(), this.getY() - this.getTagPointY()) > radius) {
	        wanderAngle += Math.PI; // Reverse direction if too far
	    }
	}
	
	
	private long lastJumpTime = 0;
	private double jumpInterval = 500; // Jumps every 500ms
	public void holdJumping() {
	    long currentTime = System.currentTimeMillis();
	    
	    if (currentTime - lastJumpTime > jumpInterval) {
	        double jumpDistance = 5; // How far it jumps
	        this.setX(this.getTagPointX() + (Math.random() * jumpDistance * 2 - jumpDistance));
	        this.setY(this.getTagPointY() + (Math.random() * jumpDistance * 2 - jumpDistance));
	        lastJumpTime = currentTime;
	    }
	}




}
