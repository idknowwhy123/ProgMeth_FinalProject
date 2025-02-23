package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverScreen {
    private Stage stage;
    private Scene scene;
    private Runnable onReturnToStart;

    public GameOverScreen(Stage stage, Runnable onReturnToStart) {
        this.stage = stage;
        this.onReturnToStart = onReturnToStart;
        createUI();
    }

    private void createUI() {
        // Background Image
        ImageView background = new ImageView(new Image("file:gameover.jpg")); // Replace with actual image path
        background.setFitWidth(800);
        background.setFitHeight(600);

        // "Game Over" Title
        Text title = new Text("GAME OVER");
        title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.ttf"), 60)); // Arcade-style font
        title.setFill(Color.RED);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(3);

        // Restart Button (Return to Start Screen)
        Button restartButton = new Button("Back to Menu");
        styleButton(restartButton);
        restartButton.setOnAction(e -> onReturnToStart.run()); // Go back to Start Screen

        // Exit Button
        Button exitButton = new Button("Exit");
        styleButton(exitButton);
        exitButton.setOnAction(e -> stage.close());

        // Layout
        VBox layout = new VBox(20, title, restartButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(background, layout);
        scene = new Scene(root, 800, 600);
    }

    private void styleButton(Button button) {
        button.setFont(Font.font(24));
        button.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: red; -fx-text-fill: black; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
    }

    public void show() {
        stage.setScene(scene);
        stage.show();
    }
}
