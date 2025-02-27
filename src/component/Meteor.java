package component;

import base.component.Enemy;
import gui.GameScreen;
import javafx.scene.image.Image;
import logic.GameLogic;

public class Meteor extends Enemy {
	private double speed = 3;
	private double drift = Math.random() * 2;

	public Meteor(double x, double y) {
		super(5, x, y, 0, 0,30, new Image("Banana.png")); // Size of meteor
	}
	
	public boolean update() {
//        if (GameScreen.playerShip == null) return;

        double playerX = GameScreen.playerShip.getX() + 70 / 2;
        double direction = Math.signum(playerX - this.getX()) * 0.5; // Adjust towards player

        this.setX(getX() + drift + direction); // Semi-homing effect
        this.setY(getY() + speed);

        // Remove meteor if it goes off-screen
        return this.getY() < GameLogic.getHeight();
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveTo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveSet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

}
