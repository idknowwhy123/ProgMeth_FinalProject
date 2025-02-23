package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PauseScreen {
    private Stage stage;
    private Scene scene;
    private Runnable onResume;
    private Runnable onReturnToMenu;

    public PauseScreen(Stage stage, Runnable onResume, Runnable onReturnToMenu) {
        this.stage = stage;
        this.onResume = onResume;
        this.onReturnToMenu = onReturnToMenu;
        createUI();
    }

    private void createUI() {
        // "Paused" Title
        Text title = new Text("PAUSED");
        title.setFont(Font.font(50));
        title.setFill(Color.YELLOW);

        // Resume Button
        Button resumeButton = new Button("Resume");
        styleButton(resumeButton);
        resumeButton.setOnAction(e -> onResume.run());

        // Menu Button
        Button menuButton = new Button("Back to Menu");
        styleButton(menuButton);
        menuButton.setOnAction(e -> onReturnToMenu.run());

        // Layout
        VBox layout = new VBox(20, title, resumeButton, menuButton);
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(layout);
        scene = new Scene(root, 800, 600);
    }

    private void styleButton(Button button) {
        button.setFont(Font.font(24));
        button.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: blue; -fx-text-fill: black; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: darkblue; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
    }

    public void show() {
        stage.setScene(scene);
        stage.show();
    }
}
