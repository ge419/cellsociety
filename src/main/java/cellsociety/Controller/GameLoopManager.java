package cellsociety.Controller;

import cellsociety.Config;
import cellsociety.Engine.FireEngine;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.EngineInterface;
import cellsociety.Engine.SegEngine;
import cellsociety.Engine.WatorEngine;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import java.io.File;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Han Zhang, Changmin Shin, Brandon Weiss
 */
public class GameLoopManager extends Application {
  public static final String INIT_FILE_LOCATION = "data/Init.xml";
  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
  private static final String SEG_NAME = NAMES_FILE.getString("SegName");
  private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
  private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
  private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
  //private static final String PERC_NAME = NAMES_FILE.getString("PercolName");

  private String language;
  private Config config;
  private Grid grid;
  private Grid initGrid; // stores initial state of Grid
  private VisualGrid visualGrid;
  private SimulationController animationManager;
  private int width;
  private int height;
  private EngineInterface engine;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 10.0 / FRAMES_PER_SECOND;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.language = "english";
    this.config = new Config();
    //Test
    File file = new File(INIT_FILE_LOCATION);
    config.readFile(file);
    setUpFromConfig(primaryStage);
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step(primaryStage);
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }));
    animation.play();
  }

  private void setUpFromConfig(Stage primaryStage) throws Exception {
    width = config.getWidth();
    height = config.getHeight();
    this.grid = new Grid(width, height, config.getVariant());
    this.initGrid = new Grid(width, height, config.getVariant());
    this.visualGrid = new RectangleVisualGrid(width, height);
    this.animationManager = new SimulationManager();
    startEngine(config.getVariant());
    new GUIContainer(primaryStage, language, config, engine, animationManager,
        visualGrid);
  }

  private void step(Stage primaryStage) throws Exception {
    gameStateUpdates();
    checkNewFiles(primaryStage);
  }

  private void checkNewFiles(Stage primaryStage) throws Exception {
    if(animationManager.isNewFile()){
      setUpFromConfig(primaryStage);
    }
  }
  private void gameStateUpdates() {
    if (animationManager.isNewFrame()) {
      if (animationManager.isPaused()){
        if(animationManager.isStep()){
          this.engine.updateGameState();
        }
      }
      else {
        this.engine.updateGameState();
      }
      animationManager.setStep();
      this.visualGrid.updateEntireGrid(grid);
      animationManager.resetFrameNum();
    }
    animationManager.incrementFrame();
  }

  //TODO: REFACTOR --> not using if/switch statements?

  /**
   * Determines the simulation and starts the corresponding SimEngine
   * @param simType
   * @throws Exception
   */
  public void startEngine(String simType) throws Exception {
    if (simType.equals(LIFE_NAME)) {
      engine = new LifeEngine(visualGrid, config.getInitState(), grid, initGrid,
          config.getSimParam());
    } else if (simType.equals(FIRE_NAME)) {
      engine = new FireEngine(visualGrid, config.getInitState(), grid, initGrid,
          config.getSimParam());
    } else if (simType.equals(SEG_NAME)) {
      engine = new SegEngine(visualGrid, config.getInitState(), grid, initGrid,
          config.getSimParam());
    } else if (simType.equals(WATOR_NAME)) {
      engine = new WatorEngine(visualGrid, config.getInitState(), grid, initGrid,
          config.getSimParam());
    }
  }
}
