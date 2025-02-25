package gui;

import application.GameApp;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GameOverScreen {
    private StackPane pane;

    public GameOverScreen(GameApp gameApp) {
        pane = new StackPane();
        Button restartButton = new Button("Restart");

        restartButton.setOnAction(e -> gameApp.showStartScreen());

        pane.getChildren().add(restartButton);
    }

    public StackPane getPane() {
        return pane;
    }
}
