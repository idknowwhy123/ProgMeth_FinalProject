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

public class StartScreen {
    private Stage stage;
    private Scene scene;
    private Runnable onStart;

    public StartScreen(Stage stage, Runnable onStart) {
        this.stage = stage;
        this.onStart = onStart;
        createUI();
    }

    private void createUI() {
        // Background Image
        ImageView background = new ImageView(new Image("file:background.jpg")); // Change to your actual image path
        background.setFitWidth(800);
        background.setFitHeight(600);

        // Game Title
        Text title = new Text("Chicken Invaders");
        title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/arcade.ttf"), 50)); // Arcade-style font
        title.setFill(Color.YELLOW);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);

        // Start Button
        Button startButton = new Button("Start Game");
        styleButton(startButton);
        startButton.setOnAction(e -> onStart.run());

        // Layout
        VBox layout = new VBox(20, title, startButton);
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(background, layout);
        scene = new Scene(root, 800, 600);
    }

    private void styleButton(Button button) {
        button.setFont(Font.font(24));
        button.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: gold; -fx-text-fill: black; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 10;"));
    }

    public void show() {
        stage.setScene(scene);
        stage.show();
    }
}
