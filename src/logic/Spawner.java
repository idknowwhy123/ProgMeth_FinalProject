package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import component.Chicken;

public class Spawner {

	private static final Random random = new Random();

	public static List<Chicken> spawnEnemies(int count, int width) {
		List<Chicken> newChickens = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			newChickens.add(new Chicken(random.nextInt(width - 50), random.nextInt(200)));
		}
		return newChickens;
	}

	public static List<Chicken> spawnWave() {
		List<Chicken> newChickens = new ArrayList<>();
		int tmp = random.nextInt(200);
		for (int i = 1; i <= 10; i++) { // More chickens each wave
			newChickens.add(new Chicken(i * 50 + random.nextInt(20) , tmp));
		}
		return newChickens;
	}
}
