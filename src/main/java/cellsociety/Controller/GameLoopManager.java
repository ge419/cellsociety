package cellsociety.Controller;

import cellsociety.Config;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.SimEngine;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import javafx.stage.Stage;

/**
 * @author Han Zhang, Changmin Shin
 */
public class GameLoopManager {

  private Config config;
  private Grid grid;
  private Grid initGrid; // stores initial state of Grid
  private VisualGrid visualGrid;

  private AnimationInterface animationManager;
  private int width;
  private int height;
  private SimulationController engine;
  private GUIContainer container;
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
}
