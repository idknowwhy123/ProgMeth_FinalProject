package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import logic.GameLogic;

public class GameLoop {
	
    private Timeline timeline;
    private GraphicsContext gc;

    public GameLoop(GraphicsContext gc) {
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
        GameLogic.update();
    }

    private void render() {
        GameLogic.render(gc);
    }
}
