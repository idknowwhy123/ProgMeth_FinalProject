package logic;

import java.util.List;
import component.Bullet;
import component.Chicken;

public class CollisionManager {

	public static void checkCollisions(List<Bullet> bullets, List<Chicken> chickens) {
		for (int i = bullets.size() - 1; i >= 0; i--) {
			Bullet bullet = bullets.get(i);
			for (int j = chickens.size() - 1; j >= 0; j--) {
				Chicken chicken = chickens.get(j);
				if (isColliding(bullet, chicken)) {
					bullets.remove(i);
					chickens.remove(j);
					break;
				}
			}
		}
		
		bullets.removeIf(b -> !b.update());
	}

    private static boolean isColliding(Bullet bullet, Chicken chicken) {
        return (bullet.getX() < chicken.getX() + 40 && 
                bullet.getX() + 35 > chicken.getX() &&
                bullet.getY() < chicken.getY() + 40 &&
                bullet.getY() + 35 > chicken.getY());
    }
}
