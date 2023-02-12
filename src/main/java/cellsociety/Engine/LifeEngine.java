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

  public LifeEngine(String simType, VisualGrid visualGrid, String initState, Grid grid, Grid initGrid, HashMap<String, Double> params) {
    super(visualGrid, initState, grid, initGrid, params);
  }

  @Override
  String statusIntoStr(int status) throws Exception {
    if (status == 0) {
      return LIFE_DEAD;
    } else if (status == 1) {
      return LIFE_ALIVE;
    } else {
      //throw exception saying invalid status
      throw new Exception("Invalid input for status");
    }

  }
}
