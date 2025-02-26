package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.component.Enemy;
import base.component.HoldingMoveSet;
import base.component.SpawnPos;
import component.Chicken;
import component.Meteor;
import gui.GameScreen;

public class Spawner {

	private static final Random random = new Random();

	// Example of use
	public static List<Enemy> spawnWave() {
		List<Enemy> newChickens = new ArrayList<>();
		int tmp = random.nextInt(200);
		int tmp2 = random.nextInt(50);
		for (int i = 1; i <= 10; i++) { // More chickens each wave
//			newChickens.add(new Chicken(0, 0, i * 50 + tmp2, tmp, SpawnPos.LEFT, 10));
		}
		return newChickens;
	}

	public static List<List<Enemy>> spawnSquare(int delay, SpawnPos pos, int duration, HoldingMoveSet holding) {

		int paddingSide = 0;
		int paddingUp = 0;
		int signx = 0;
		int signy = 0;
		if (pos == SpawnPos.LEFT) {
			paddingSide = 0 - GameLogic.getWidth();
			paddingUp = 0;
			signx = -1;
			signy = 0;
		} else if (pos == SpawnPos.RIGHT) {
			paddingSide = 0 + GameLogic.getWidth();
			paddingUp = 0;
			signx = 1;
			signy = 0;
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
				lineChickens.add(new Chicken((j * 140) + paddingSide + (signx * delayByLine * 140), // spawn point
						(i * 80) - paddingUp + (signy * delayByLine * 140), // spawn point
						(j * 140), // holding point
						(i * 80), // holding point
						pos, duration, holding));
			}
			delayByLine += delay;
			squareChickens.add(lineChickens);
		}
		return squareChickens;
	}

	public static List<List<Enemy>> spawnTriangle(SpawnPos pos, int duration, HoldingMoveSet holding) {

		int paddingSide = 0;
		int paddingUp = 0;
		int signx = 0;
		int signy = 0;
		if (pos == SpawnPos.LEFT) {
			paddingSide = 0 - GameLogic.getWidth();
			paddingUp = 0;
			signx = -1;
			signy = 0;
		} else if (pos == SpawnPos.RIGHT) {
			paddingSide = 0 + GameLogic.getWidth();
			paddingUp = 0;
			signx = 1;
			signy = 0;
		} else if (pos == SpawnPos.UP) {
			paddingSide = 0;
			paddingUp = GameLogic.getHeight();
			signy = -1;
		}
		List<List<Enemy>> squareChickens = new ArrayList<>();

		int delayByLine = 0;
		int paddingTriangle = 0;
		for (int i = 1; i <= 6; i++) {
			List<Enemy> lineChickens = new ArrayList<>();
			for (int j = 1; j <= 7 - i; j++) {
				lineChickens.add(new Chicken((j * 70) + paddingTriangle * 35 + paddingSide + (signx * delayByLine * 70), // spawn
																															// //
																															// point
						(i * 50) - paddingUp + (signy * delayByLine * 70), // spawn point
						(j * 70) + paddingTriangle * 35, // holding point
						(i * 50), // holding point
						pos, duration, holding));

			}
			paddingTriangle++;
			delayByLine += 1;
			squareChickens.add(lineChickens);
		}

		delayByLine = 0;
		paddingTriangle = 0;
		for (int i = 1; i <= 9; i++) {
			List<Enemy> lineChickens = new ArrayList<>();
			for (int j = 1; j <= 10 - i; j++) {
				lineChickens.add(
						new Chicken((j * 70) + 435 + paddingTriangle * 35 + paddingSide + (signx * delayByLine * 70), // spawn
																														// point
								(i * 50) - paddingUp + (signy * delayByLine * 70), // spawn point
								(j * 70) + paddingTriangle * 35 + 435, // holding point
								(i * 50), // holding point
								pos, duration, holding));
			}
			paddingTriangle++;
			delayByLine += 1;
			squareChickens.add(lineChickens);
		}

		delayByLine = 0;
		paddingTriangle = 0;
		for (int i = 1; i <= 6; i++) {
			List<Enemy> lineChickens = new ArrayList<>();
			for (int j = 1; j <= 7 - i; j++) {
				lineChickens.add(
						new Chicken((j * 70) + 1070 + paddingTriangle * 35 + paddingSide + (signx * delayByLine * 70), // spawn
								// point
								(i * 50) - paddingUp + (signy * delayByLine * 70), // spawn point
								(j * 70) + paddingTriangle * 35 + 1070, // holding point
								(i * 50), // holding point
								pos, duration, holding));
			}
			paddingTriangle++;
			delayByLine += 1;
			squareChickens.add(lineChickens);
		}
		return squareChickens;
	}

	public static List<Meteor> spawnRainMeteor() {
		List<Meteor> meteors = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			int x = random.nextInt(16)+1;
			meteors.add(new Meteor(100*x, 0-200*i));
		}
		return meteors;
	}
}
