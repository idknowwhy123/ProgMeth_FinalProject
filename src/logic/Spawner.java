package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.component.Enemy;
import base.component.SpawnPos;
import component.Chicken;

public class Spawner {

	private static final Random random = new Random();

	public static List<Enemy> spawnWave() {
		List<Enemy> newChickens = new ArrayList<>();
		int tmp = random.nextInt(200);
		int tmp2 = random.nextInt(50);
		for (int i = 1; i <= 10; i++) { // More chickens each wave
			newChickens.add(new Chicken(0, 0, i * 50 + tmp2, tmp, SpawnPos.LEFT));
		}
		return newChickens;
	}

	public static List<List<Enemy>> spawnSquare() {
		List<List<Enemy>> squareChickens = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			List<Enemy> lineChickens = new ArrayList<>();
			for (int j = 10; j >= 1; j--) {
				lineChickens.add(new Chicken(0, i * 60, j * 70, i * 60, SpawnPos.LEFT));
			}
			
			squareChickens.add(lineChickens);
		}
		return squareChickens;

	}
}
