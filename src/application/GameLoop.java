package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class GameLoop {
    private Timeline timeline;
    private GraphicsContext gc;
    private Main game;

    public GameLoop(Main game, GraphicsContext gc) {
        this.game = game;
        this.gc = gc;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            update();
            render();
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        this.timeline.play();
    }

    private void update() {
        game.update();
    }

    private void render() {
        game.render(gc);
    }
}
