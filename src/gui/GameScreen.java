package gui;

import application.GameApp;
import base.component.Enemy;
import component.Bullet;
import component.PlayerShip;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;

public class GameScreen {
	private Pane pane;
	private Canvas canvas;
	private GraphicsContext gc;
	private PlayerShip playerShip;
	private AnimationTimer gameLoop;

	public static boolean isPaused = false;
	private Button resumeButton;

	public GameScreen(GameApp gameApp) {
		pane = new Pane();
		pane.setStyle("-fx-background-color: lightblue;");

		// Set up canvas for rendering
		canvas = new Canvas(GameLogic.getWidth(), GameLogic.getHeight());
		gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);

		// Initialize PlayerShip
		playerShip = new PlayerShip(GameLogic.getWidth() / 2 - 50, GameLogic.getHeight() - 120);

		// Create Resume Button (Hidden Initially)
		resumeButton = new Button("Resume");
		resumeButton.setLayoutX(GameLogic.getWidth() / 2 - 50);
		resumeButton.setLayoutY(GameLogic.getHeight() / 2);
		resumeButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
		resumeButton.setVisible(false);
		resumeButton.setOnAction(e -> resumeGame());
		pane.getChildren().add(resumeButton);

		// Start Game Loop
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				render();
			}
		};
		gameLoop.start();
	}

	public Scene getScene() {
		Scene scene = new Scene(pane, GameLogic.getWidth(), GameLogic.getHeight());

		// Handle Key Events
		scene.setOnKeyPressed(event -> {
			if (event.getCode().toString().equals("ESCAPE")) {
				togglePause();
			} else if (!isPaused) {
				playerShip.handleKeyPress(event.getCode());
			}
		});

		scene.setOnKeyReleased(event -> {
			if (!isPaused) {
				playerShip.handleKeyRelease(event.getCode());
			}
		});

		return scene;
	}

	private void update() {
		if (isPaused) {
			return;
		}
		playerShip.update();
		GameLogic.updateBullets();
		GameLogic.updateEnemies();
		GameLogic.checkCollisions();
		GameLogic.updateEndLevel();
		
	}

	private void render() {
		gc.clearRect(0, 0, GameLogic.getWidth(), GameLogic.getHeight()); // Clear the screen
		playerShip.render(gc); // Render player

		// Render all animated bullets
		for (Bullet bullet : GameLogic.getBullets()) {
			bullet.render(gc);
		}

		// Render all enemies
		for (Enemy enemy : GameLogic.getEnemies()) {
			enemy.render(gc);
		}

		if (isPaused) {
			gc.setFill(Color.rgb(0, 0, 0, 0.4)); // Semi-transparent black overlay
			gc.fillRect(0, 0, GameLogic.getWidth(), GameLogic.getHeight());

			gc.setFill(Color.WHITE);
			gc.setFont(new Font(40));
			gc.fillText("PAUSED", GameLogic.getWidth() / 2 - 60, GameLogic.getHeight() / 2 - 50);
		}
	}

	private void togglePause() {
		isPaused = !isPaused;
		resumeButton.setVisible(isPaused);
		if(isPaused==false) {
			GameLogic.spawnThread.interrupt();
		}
	}

	private void resumeGame() {
		isPaused = false;
		resumeButton.setVisible(false);
	}

	public Pane getPane() {
		return pane;
	}
}
