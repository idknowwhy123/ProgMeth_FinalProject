package gui;

import application.GameApp;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StartScreen {
    private StackPane pane;

    public StartScreen(GameApp gameApp) {
        pane = new StackPane();
        Button startButton = new Button("Start Game");
        
        startButton.setOnAction(e -> gameApp.showGameScreen());
        
        pane.getChildren().add(startButton);
    }

    public StackPane getPane() {
        return pane;
    }
}
