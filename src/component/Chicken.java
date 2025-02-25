package component;

import base.component.Enemy;
import base.component.SpawnPos;
import javafx.scene.image.Image;

public class Chicken extends Enemy {
	private static final double SPEED = 1;
	private double grad;
	private double intercept;

	public Chicken(double x, double y, double tagX, double tagY, SpawnPos pos) {
		super(x, y, tagX, tagY, pos, new Image("Banana.png"));
		this.setHp(3);

		/////// calculate part for spawn move
		this.grad = (tagY - y) / (tagX - x);
		switch (this.getSpawnFrom()) {
		case LEFT:
			intercept = tagY - grad*tagX;
			break;

		default:
			break;
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
			moveDown();
			break;
		}
	}

	@Override
	public void moveTo() {
		setX(getX() + SPEED);
		setY(grad * getX() + intercept);

	}

	@Override
	public void moveSet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		setY(getY() + SPEED);
	}

}
