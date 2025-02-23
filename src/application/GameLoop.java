package application;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;

public class GameLoop implements Runnable {

    private GraphicsContext gc;
    
    private volatile boolean running = true;
    
    private Thread thread;
    private Runnable onGameOver; // Callback for game over

    public GameLoop(GraphicsContext gc, Runnable onGameOver) {
        this.gc = gc;
        this.onGameOver = onGameOver;
    }

    public void start() {
    	running = true;
    	GameLogic.setGameOver(false);
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        final int FPS = 60;
        final long frameTime = 1_000_000_000 / FPS;

        while (running) {
        	
            long startTime = System.nanoTime();

            update();
            Platform.runLater(this::render);

            // Check if the player lost the game
            if (GameLogic.isGameOver()) {  
                stop();
                Platform.runLater(onGameOver); // Show Game Over screen on UI thread
                return;
            }

            long elapsedTime = System.nanoTime() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1_000_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void update() {
        GameLogic.update();
    }

    private void render() {
        GameLogic.render(gc);
    }
    

}
