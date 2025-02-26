package component;

import base.component.Enemy;
import base.component.HoldingMoveSet;
import base.component.SpawnPos;
import javafx.scene.image.Image;

public class Chicken extends Enemy {
	private static final double SPEED = 1;
	private double grad;
	private double intercept;
	private HoldingMoveSet holding;

	public Chicken(double x, double y, double tagX, double tagY, SpawnPos pos, int duration, HoldingMoveSet holding) {
		super(2, x, y, tagX, tagY, pos, new Image("Banana.png"));
		this.setHp(3);
		this.setDuration(duration * 1000);
		this.holding = holding;
		if (holding == HoldingMoveSet.JITTER) {
			this.setDuration(1000);
		}

		/////// calculate part for spawn move
		this.grad = (tagY - y) / (tagX - x);

		if (pos == SpawnPos.LEFT || pos == SpawnPos.RIGHT) {
			intercept = tagY - grad * tagX;
		} else if (pos == SpawnPos.UP) {
			grad = 999999;
			intercept = tagY - grad * tagX;
		}
	}

	@Override
	public void move() {
		switch (this.getState()) {
		case SPAWN:
			moveTo();
			break;
		case HOLD:
			moveSet();
			break;
		case DESPAWN:
			moveSet();
			this.setTagPointY(getTagPointY() + SPEED);
			moveDown();
			break;
		}
	}

	@Override
	public void moveTo() {
		switch (this.getSpawnFrom()) {
		case RIGHT:
			setX(getX() - SPEED);
			setY(grad * getX() + intercept);
			break;
		case LEFT:
			setX(getX() + SPEED);
			setY(grad * getX() + intercept);
			break;
		case UP:
			setY(getY() + SPEED);
			break;
		default:
			break;
		}

	}

	@Override
	public void moveSet() {

		switch (this.holding) {
		case CIRCLE:
			this.holdCircle();
			break;
		case HALF_CIRCLE:
			this.holdHalfCircle();
			break;
		case SINE_WAVE:
			this.holdSineWave();
			break;
		case JITTER:
			this.holdJitter();
			break;
		case ZIGZAG:
			this.holdZigzag();
			break;
		case WANDER:
			this.holdWander();
			break;

		default:
			this.holdSineWave();
			break;
		}
	}

	@Override
	public void moveDown() {
		setY(getY() + SPEED);
	}

}
