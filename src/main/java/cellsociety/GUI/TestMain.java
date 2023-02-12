package cellsociety.GUI;

import cellsociety.Config;
import cellsociety.Engine.EngineInterface;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMain extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";

  private static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);

  private GUIContainer container;

  private EngineInterface engine;

  public static void main(String[] args) {
    launch(args);
  }
  public void start (Stage primaryStage) {
    //Grid grid = new Grid();

    String english = "english";
    Config config = new Config();
    //engine = new SimulationEngine("Game of Life", config.getSimParam(), grid, config.getInitState());
//    container = new GUIContainer(primaryStage, "english", config, engine);
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double secondDelay) {
    container.asyncUpdate();
    container.getGrid().updateGrid(2,4, "StateA");
    container.getGrid().updateGrid(3,7, "Shark");
  }
}
