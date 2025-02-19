package logic;

import java.util.List;
import component.Bullet;
import component.Chicken;

public class CollisionManager {

    
    public static void checkCollisions(List<Bullet> bullets, List<Chicken> chickens) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < chickens.size(); j++) {
                Chicken chicken = chickens.get(j);
                if (isColliding(bullet, chicken)) {
                    bullets.remove(i); 
                    chickens.remove(j); 
                    i--; 
                    break; 
                }
            }
        }
    }

    private static boolean isColliding(Bullet bullet, Chicken chicken) {
        return (bullet.getX() < chicken.getX() + 40 && 
                bullet.getX() + 35 > chicken.getX() &&
                bullet.getY() < chicken.getY() + 40 &&
                bullet.getY() + 35 > chicken.getY());
    }
}
