package cellsociety.Engine;

import cellsociety.Grid;
import java.util.HashMap;

public class LifeEngine extends SimulationEngine{

  private static final String LIFE_ALIVE = NAMES_FILE.getString("LifeAlive");
  private static final String LIFE_DEAD = NAMES_FILE.getString("LifeDead");

  /**
   * @param simType The string representing which of the cellular automata to run
   * @param params  A HashMap of parameters and values for each simulation type
   * @param grid    The class that contains grid data
   * @param state
   */
  public LifeEngine(String simType,
      HashMap<String, Double> params, Grid grid, String state) {
    super(simType, params, grid, state);

  }
}
