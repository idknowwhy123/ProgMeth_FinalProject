package component;

import base.component.Enemy;
import base.component.HoldingMoveSet;
import base.component.SpawnPos;
import javafx.scene.image.Image;

public class Chicken extends Enemy {
	private double speed = 1;
	private HoldingMoveSet holding;

	public Chicken(double x, double y, double tagX, double tagY, int duration, HoldingMoveSet holding) {
		super(2, x, y, tagX, tagY,5, new Image("Banana.png"));
		this.setHp(3);
		this.setDuration(duration * 1000);
		this.holding = holding;
		if (holding == HoldingMoveSet.JITTER) {
			this.setDuration(1000);
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
			this.setTagPointY(getTagPointY() + speed);
			moveDown();
			break;
		}
	}

	@Override
	public void moveTo() {
		double dx = this.getTagPointX() - this.getX();
		double dy = this.getTagPointY() - this.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);

		if (distance > speed) { // Move only if not at the target
			this.setX(this.getX() + dx / distance * speed);
			this.setY(this.getY() + dy / distance * speed);
		}
//        else { // Snap to target when close enough
//            x = tagX;
//            y = tagY;
//        }
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
		setY(getY() + speed);
	}

}
