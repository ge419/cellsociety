package cellsociety.Engine;

import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import java.util.HashMap;

/**
 * @author Changmin Shin
 */

public class LifeEngine extends SimEngine {

  private static final String LIFE_ALIVE = NAMES_FILE.getString("LifeAlive");
  private static final String LIFE_DEAD = NAMES_FILE.getString("LifeDead");

  public LifeEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid, HashMap<String, Double> params)
      throws Exception {
    super(visualGrid, initState, grid, initGrid, params);
    // setup initial state grid for both updating grid and initial grid
    listToGrid(strToGrid(initState), grid);
    listToGrid(strToGrid(initState), initGrid);
  }

  @Override
  String statusIntToStr(int status) throws Exception {
    if (status == 0) {
      return LIFE_DEAD;
    } else if (status == 1) {
      return LIFE_ALIVE;
    } else {
      //TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }
}
