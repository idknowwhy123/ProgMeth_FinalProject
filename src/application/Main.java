package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import component.Bullet;
import component.PlayerShip;
import component.Chicken;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private GraphicsContext gc;
    private PlayerShip player;
    public static List<Bullet> bullets = new ArrayList<>();
    private List<Chicken> chickens = new ArrayList<>();
    private GameLoop gameLoop; 
    private Random random = new Random();
    private int enemySpawnTimer = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        player = new PlayerShip(WIDTH / 2 - 25, HEIGHT - 80);
        scene.setOnKeyPressed(e -> player.handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(e -> player.handleKeyRelease(e.getCode()));

        spawnEnemies(5);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                    for (Chicken chicken : chickens) {
                        chicken.move();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        gameLoop = new GameLoop(this, gc); // Initialize GameLoop
        gameLoop.start(); // Start the game loop

        primaryStage.setTitle("Chicken Invaders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void update() {
        player.update();
        bullets.removeIf(b -> !b.update());
        chickens.removeIf(c -> c.update(bullets));

        enemySpawnTimer++;
        if (enemySpawnTimer > 120) {
            spawnEnemies(3);
            enemySpawnTimer = 0;
        }
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(gc);
        bullets.forEach(b -> b.render(gc));
        chickens.forEach(c -> c.render(gc));
    }

    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            chickens.add(new Chicken(random.nextInt(WIDTH - 50), random.nextInt(200)));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
