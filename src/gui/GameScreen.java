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
	public static PlayerShip playerShip;
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
				if (GameLogic.isGameOver()) {
					gameApp.showGameOverScreen();
					gameLoop.stop();
					GameLogic.setGameOver(false);
				}
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
			} else if (!isPaused || GameLogic.isGameOver()) {
				playerShip.handleKeyPress(event.getCode());
			}
		});

		scene.setOnKeyReleased(event -> {
			if (!isPaused || GameLogic.isGameOver()) {
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
		GameLogic.checkBulletCollisions();
		GameLogic.checkPlayerCollisions();
		
		GameLogic.updateEndLevel();
	}

	private double healthBarWidth = 200; // Full width of health bar

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

		// score
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(30));
		gc.fillText("Score: " + GameLogic.getScore(), 20, 40);

		// Draw Health Bar

		double healthPercentage = playerShip.getHp() / 5.0; // Assuming max health is 5
		double currentHealthWidth = healthBarWidth * healthPercentage;
		// System.out.println(currentHealthWidth);
		gc.setFill(Color.GRAY); // Background bar
		gc.fillRect(20, GameLogic.getHeight() - 40, healthBarWidth, 20);

		gc.setFill(Color.LIMEGREEN); // Health bar fill
		gc.fillRect(20, GameLogic.getHeight() - 40, currentHealthWidth, 20);

		gc.setStroke(Color.BLACK); // Border
		gc.strokeRect(20, GameLogic.getHeight() - 40, healthBarWidth, 20);

		// Draw "Pause" screen overlay
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
	}

	private void resumeGame() {
		isPaused = false;
		resumeButton.setVisible(false);
	}

	public Pane getPane() {
		return pane;
	}

	public static PlayerShip getPlayerShip() {
		return playerShip;
	}
}
