package logic;

import java.util.Random;

import base.component.SpawnPos;

public class SpawnPosUtil {
    private static final Random RANDOM = new Random();

    public static SpawnPos getRandomSpawnPos() {
        SpawnPos[] positions = SpawnPos.values(); // Get all enum values
        return positions[RANDOM.nextInt(positions.length)]; // Pick a random one
    }
}
