package component;

import base.component.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class Boss extends Enemy {
	private int attackCooldown = 100; // Delay between attacks
	private int moveDirection = 1; // 1 for right, -1 for left

	public Boss(int give, double x, double y, Image enemyImage) {
		// give is for score and health
		super(give, x, y, x, y, 20, enemyImage);
		this.setHp(give);
	}

	private void shoot() {
		// Boss fires a bullet downward
		Enemy bossBullet = new EnemyBullet(this.getX(), this.getY() + 50, 3, 10);
		GameLogic.addEnemy(bossBullet);
	}

	@Override
	public boolean update() {
		// Move left and right
		this.setX(this.getX() + moveDirection * 3);

		// Reverse direction at screen edges
		if (this.getX() <= 50 || this.getX() >= 1550) {
			moveDirection *= -1;
		}

		// Attack logic
//        System.out.println(attackCooldown);
		if (attackCooldown <= 0) {
			shoot();
			attackCooldown = 100; // Reset cooldown
		} else {
			attackCooldown--;
		}

		return this.getHp() > 0;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(this.getEnemyImage(), getX(), getY(), 200, 200);

		// HIT BOX
		gc.setStroke(Color.RED); // Set border color
		gc.setLineWidth(2); // Set border thickness
		gc.strokeRect(getX(), getY(), 200, 200); // Draw rectangle
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
