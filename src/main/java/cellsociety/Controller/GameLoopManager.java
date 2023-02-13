package cellsociety.Controller;

import cellsociety.Config;
import cellsociety.Engine.FireEngine;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.EngineInterface;
import cellsociety.Engine.SegEngine;
import cellsociety.Engine.WatorEngine;
import cellsociety.GUI.FileUploader;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Life;
import cellsociety.simulations.Schelling;
import java.io.File;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Han Zhang, Changmin Shin
 */
public class GameLoopManager extends Application {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
  private static final String SEG_NAME = NAMES_FILE.getString("SegName");
  private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
  private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
  private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
  private static final String PERC_NAME = NAMES_FILE.getString("PercolName");

  private String language;
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
  public static final double SECOND_DELAY = 10.0 / FRAMES_PER_SECOND;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.language = "english";
    this.config = new Config();
    //Test
    File file = new File("data/Preloaded_Files/LifeEx1.xml");
    config.readFile(file);
    //TODO: Need to read in XML file before initializing new Grid and VisualGrid
    // --> Initialize as default before a file is selected
    // --> Have a pop-up asking the user to select the xml file to get started
    width = config.getWidth();
    height = config.getHeight();
    this.grid = new Grid(width, height, config.getVariant());
    this.initGrid = new Grid(width, height, config.getVariant());
    this.visualGrid = new RectangleVisualGrid(width, height);
    this.animationManager = new AnimationManager();
    startEngine(config.getVariant());
    this.container = new GUIContainer(primaryStage, language, config, engine, animationManager,
        visualGrid);
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    animation.play();
  }

  private void step() {

    if (animationManager.isNewFrame()) {
      if (animationManager.isPaused()){
        animationManager.resetFrameNum();
        if(animationManager.isStep()){
          animationManager.setStep();
          this.engine.updateGameState();
        }
      }
      else {
        animationManager.incrementFrame();
        this.engine.updateGameState();
      }
      this.visualGrid.updateEntireGrid(grid);
    }

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
