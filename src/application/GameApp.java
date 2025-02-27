package application;

import gui.GameOverScreen;
import gui.GameScreen;
import gui.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.GameLogic;

public class GameApp extends Application {
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showStartScreen();  // Start with the main menu
        primaryStage.setTitle("Invader Game");
        primaryStage.show();
    }

    public void showStartScreen() {
        StartScreen startScreen = new StartScreen(this);
        primaryStage.setScene(new Scene(startScreen.getPane(), GameLogic.getWidth(), GameLogic.getHeight()));
    }

    public void showGameScreen() {
    	GameLogic.init();
        GameScreen gameScreen = new GameScreen(this);
        primaryStage.setScene(gameScreen.getScene());
    }

    public void showGameOverScreen() {
        GameOverScreen gameOverScreen = new GameOverScreen(this);
        primaryStage.setScene(new Scene(gameOverScreen.getPane(), GameLogic.getWidth(), GameLogic.getHeight()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}