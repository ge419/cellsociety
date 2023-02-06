package cellsociety.GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMain extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private GUIContainer container;

  public static void main(String[] args) {
    launch(args);
  }
  public void start (Stage primaryStage) {
    String english = "english";
    container = new GUIContainer(primaryStage, english);

    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double secondDelay) {
    container.update();
  }
}
