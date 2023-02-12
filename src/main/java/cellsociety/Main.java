package cellsociety;

import cellsociety.Controller.GameLoopManager;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.SimulationEngine;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
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
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private LifeEngine engine;
  private HashMap<String, Double> param;

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    String language = "english";
    GameLoopManager manager = new GameLoopManager(primaryStage, language);
  }

  private void step(double secondDelay) {

  }

  //TODO put this in Grid class eventually, doesn't belong in main
//
//  private void animationSpeedUpdate() {
//    if (container.getSpeedChanged()) {
//      multiplier = container.getAnimationSpeed();
//      if (multiplier != 0) {
//        multiplier = 1 / multiplier;
//      } else {
//        multiplier = Integer.MAX_VALUE;
//      }
//    }
//  }

  private void timer(double multiplier, boolean pause) {

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
