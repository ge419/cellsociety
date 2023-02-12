package cellsociety.Controller;

import cellsociety.Config;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;

/**
 * @author Han Zhang, Changmin Shin
 */
public class GameLoopManager {

  private Config config;
  private Grid grid;
  private Grid initGrid; // stores initial state of Grid
  private VisualGrid visualGrid;
  private int width;
  private int height;

  public GameLoopManager() {
    this.config = new Config();

    //TODO: Need to read in XML file before initializing new Grid and VisualGrid
    width = config.getWidth();
    height = config.getHeight();
    this.grid = new Grid(width, height);
    this.grid = new Grid(width, height);
    this.visualGrid = new VisualGrid(); //
  }
}
