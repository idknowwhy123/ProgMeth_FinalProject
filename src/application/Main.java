package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import component.PlayerShip;
import gui.GameOverScreen;
import gui.PauseScreen;
import gui.StartScreen;
import logic.GameLogic;

public class Main extends Application {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private GraphicsContext gc;
	private PlayerShip player;
	private GameLoop gameLoop;
	
	private void showGameOverScreen(Stage primaryStage) {
	    GameOverScreen gameOverScreen = new GameOverScreen(primaryStage, () -> start(primaryStage));
	    gameOverScreen.show();
	}

	
	public void start(Stage primaryStage) {
		GameLogic.getChickens().clear();
	    StartScreen startScreen = new StartScreen(primaryStage, () -> startGame(primaryStage));
	    startScreen.show();
	}
	
	

	public void startGame(Stage primaryStage) {
		Pane root = new Pane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		Scene scene = new Scene(root);
		player = new PlayerShip(WIDTH / 2 - 25, HEIGHT - 80);

		// handle key press
		scene.setOnKeyPressed(e -> player.handleKeyPress(e.getCode()));
		scene.setOnKeyReleased(e -> player.handleKeyRelease(e.getCode()));
		
		
		//////////////////

		GameLogic.init(player);
	    gameLoop = new GameLoop(gc, () -> showGameOverScreen(primaryStage));
		gameLoop.start();

		primaryStage.setTitle("Chicken Invaders");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}