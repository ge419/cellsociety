package cellsociety.Controller;

import cellsociety.Config;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.EngineInterface;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Han Zhang, Changmin Shin
 */
public class GameLoopManager extends Application {
  private Config config;
  private Grid grid;
  private Grid initGrid; // stores initial state of Grid
  private VisualGrid visualGrid;

  private AnimationInterface animationManager;
  private int width;
  private int height;
  private EngineInterface engine;
  private GUIContainer container;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  public GameLoopManager(Stage primaryStage, String language) throws Exception {
    this.config = new Config();
    //TODO: Need to read in XML file before initializing new Grid and VisualGrid
    width = config.getWidth();
    height = config.getHeight();
    this.grid = new Grid(width, height);
    this.initGrid = new Grid(width, height);
    this.visualGrid = new RectangleVisualGrid(width, height);
    this.animationManager = new AnimationManager();
    // initialize SimEngine
    startEngine(config.getVariant());
    this.container = new GUIContainer(primaryStage, language, config, engine, animationManager, visualGrid);
  }
  //TODO: Create a method that determines the simulation type and starts the corresponding engine
  // REFACTOR --> not using if/switch statements?
  public void startEngine(String simType) throws Exception {
    if (simType.equals("Game of Life")) {
      engine = new LifeEngine(visualGrid, config.getInitState(), grid, initGrid,
          config.getSimParam());
    }
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }
  private void step(double secondDelay){
    if(animationManager.isNewFrame()){
      animationManager.incrementFrame();
      this.visualGrid.updateEntireGrid(grid);
    }
  }
}
