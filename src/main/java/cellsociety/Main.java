package cellsociety;

import cellsociety.GUI.GUIContainer;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private GUIContainer container;
  private Config config;
  int frameNum;
  double multiplier;

  public boolean pause;

  public boolean newFile;

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private SimulationEngine engine;
  private HashMap<String, Double> param;

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) {
    frameNum = 1;
    multiplier = 1;
    pause = false;
    newFile = false;
    String english = "english";
    config = new Config();
    Grid grid = new Grid();
    engine = new SimulationEngine("Game of Life", config.getSimParam(), grid, config.getInitState());
//    container = new GUIContainer(primaryStage, english, config, engine);
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double secondDelay) {
    timer(multiplier, pause);
    container.asyncUpdate();
    animationSpeedUpdate();
    if (newFile) {
      ResetGridSize();
      newFile = false;
    }
  }

  //TODO put this in Grid class eventually, doesn't belong in main
  private void ResetGridSize() {
    container.getGrid().getGridLayout().getChildren().clear();
    container.getGrid().changeSize(10, 10, container.GRID_SIZE);
  }

  private void animationSpeedUpdate() {
    if (container.getSpeedChanged()) {
      multiplier = container.getAnimationSpeed();
      if (multiplier != 0) {
        multiplier = 1 / multiplier;
      } else {
        multiplier = Integer.MAX_VALUE;
      }
    }
  }

  private void timer(double multiplier, boolean pause) {
    if (frameNum >= FRAMES_PER_SECOND * multiplier) {
      frameNum = 0;
//      engine.updateGameState();

//      container.getGrid().updateGrid(4,5, "Alive");
//      container.getGrid().updateGrid(5,7, "StateA");
      //Grid update inside here
      //Game logic
    }
    frameNum++;
    if (pause) {
      frameNum = 0;
    }
  }

  /**
   * Start the program, give complete control to JavaFX.
   * <p>
   * Default version of main() is actually included within JavaFX, so this is not technically
   * necessary!
   */
  public static void main(String[] args) {
    launch(args);
  }
}
