package application;

import gui.GameOverScreen;
import gui.GameScreen;
import gui.PauseScreen;
import gui.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showStartScreen();  // Start with the main menu
        primaryStage.setTitle("JavaFX Game");
        primaryStage.show();
    }

    public void showStartScreen() {
        StartScreen startScreen = new StartScreen(this);
        primaryStage.setScene(new Scene(startScreen.getPane(), 800, 600));
    }

    public void showGameScreen() {
        GameScreen gameScreen = new GameScreen(this);
        primaryStage.setScene(gameScreen.getScene());
    }



    public void showPauseScreen() {
        PauseScreen pauseScreen = new PauseScreen(this);
        primaryStage.setScene(new Scene(pauseScreen.getPane(), 800, 600));
    }

    public void showGameOverScreen() {
        GameOverScreen gameOverScreen = new GameOverScreen(this);
        primaryStage.setScene(new Scene(gameOverScreen.getPane(), 800, 600));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
