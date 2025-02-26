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
			newChickens.add(new Chicken(0, 0, i * 50 + tmp2, tmp, SpawnPos.LEFT, 10));
		}
		return newChickens;
	}

	public static List<List<Enemy>> spawnSquare(int delay, SpawnPos pos, int duration) {

		int paddingSide = 0;
		int paddingUp = 0;
		int signx = 1;
		int signy = 1;
		if (pos == SpawnPos.LEFT) {
			paddingSide = 0 - GameLogic.getWidth();
			paddingUp = 0;
			signx = -1;
		} else if (pos == SpawnPos.RIGHT) {
			paddingSide = 0 + GameLogic.getWidth();
			paddingUp = 0;
			signx = 1;
		} else if (pos == SpawnPos.UP) {
			paddingSide = 0;
			paddingUp = GameLogic.getHeight();
			signy = -1;
		}
		
		int delayByLine = 0;
		List<List<Enemy>> squareChickens = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			List<Enemy> lineChickens = new ArrayList<>();
			for (int j = 1; j <= 10; j++) {
				lineChickens.add(
						new Chicken((j * 140) + paddingSide + (signx * delayByLine*140), //spawn point
									(i *  80) - paddingUp   + (signy * delayByLine*140), //spawn point
									(j * 140), //holding point
									(i *  80), //holding point
									pos, 
									duration));
			}
			delayByLine += delay;
			squareChickens.add(lineChickens);
		}
		return squareChickens;
	}

//	public static List<List<Enemy>> spawnSquareFromLeft() {
//		int deley = 0;
//		List<List<Enemy>> squareChickens = new ArrayList<>();
//		for (int i = 1; i <= 6; i++) {
//			List<Enemy> lineChickens = new ArrayList<>();
//			for (int j = 1; j <= 10; j++) {
//				lineChickens
//						.add(new Chicken(j * 140 - GameLogic.getWidth() + deley * 140, i * 80, j * 140, i * 80, SpawnPos.LEFT, 5));
//			}
//			deley++;
//			squareChickens.add(lineChickens);
//		}
//		return squareChickens;
//	}
}
