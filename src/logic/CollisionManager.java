package logic;

import java.util.List;
import component.Bullet;
import component.Chicken;

public class CollisionManager {

    // Check for collisions between bullets and chickens
    public void checkCollisions(List<Bullet> bullets, List<Chicken> chickens) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            
            // Check each chicken to see if it intersects with the bullet
            for (int j = 0; j < chickens.size(); j++) {
                Chicken chicken = chickens.get(j);

                // Manually check for intersection
                if (isColliding(bullet, chicken)) {
                    handleCollision(bullet, chicken);
                    bullets.remove(i); // Remove the bullet
                    chickens.remove(j); // Remove the chicken
                    i--; // Adjust the index after removing the bullet
                    break; // Exit loop after collision is handled
                }
            }
        }
    }

    // Manually check if the bullet and chicken are colliding
    private boolean isColliding(Bullet bullet, Chicken chicken) {
        // Check if the bullet's bounds intersect with the chicken's bounds
        return (bullet.getX() < chicken.getX() + 40 && 
                bullet.getX() + 35 > chicken.getX() &&
                bullet.getY() < chicken.getY() + 40 &&
                bullet.getY() + 35 > chicken.getY());
    }

    // Handle the collision, you can expand this method for further effects (e.g., play sound, score increment)
    private void handleCollision(Bullet bullet, Chicken chicken) {
        // For now, let's just print the collision
        System.out.println("Bullet hit Chicken!");
        // You can add extra logic here, like scoring or playing sound effects.
    }
}
