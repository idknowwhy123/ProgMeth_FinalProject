package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import component.Chicken;

public class Spawner {
	
    private Random random = new Random();

    public List<Chicken> spawnEnemies(int count, int width) {
        List<Chicken> newChickens = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newChickens.add(new Chicken(random.nextInt(width - 50), random.nextInt(200)));
        }
        return newChickens;
    }
}
