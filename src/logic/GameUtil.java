package logic;

import java.util.Random;

import base.component.EventSpawn;
import base.component.HoldingMoveSet;
import base.component.SpawnPos;

public class GameUtil {
    private static final Random RANDOM = new Random();

    public static SpawnPos getRandomSpawnPos() {
        SpawnPos[] positions = SpawnPos.values(); // Get all enum values
        return positions[RANDOM.nextInt(positions.length)]; // Pick a random one
    }
    
    public static HoldingMoveSet getRandomHoldingMoveSet() {
    	HoldingMoveSet[] positions = HoldingMoveSet.values(); // Get all enum values
        return positions[RANDOM.nextInt(positions.length)]; // Pick a random one
    }
    
    public static EventSpawn getRandomEventSpawn() {
    	EventSpawn[] positions = EventSpawn.values(); // Get all enum values
        return positions[RANDOM.nextInt(positions.length)]; // Pick a random one
    }
}
