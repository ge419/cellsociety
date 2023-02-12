package cellsociety.Engine;

import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Fire;
import cellsociety.simulations.Simulation;
import java.util.HashMap;

/**
 * @author Changmin Shin
 */

public class FireEngine extends SimEngine {

  private static final String FIRE_EMPTY = NAMES_FILE.getString("FireEmpty");
  private static final String FIRE_TREE = NAMES_FILE.getString("FireTree");
  private static final String FIRE_BURNING = NAMES_FILE.getString("FireBurning");

  private Simulation sim;
  private boolean corners;

  public FireEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      HashMap<String, Double> params)
      throws Exception {
    super(visualGrid, initState, grid, initGrid, params);
    // setup initial state grid for both updating grid and initial grid
    listToGrid(strToGrid(initState), grid);
    listToGrid(strToGrid(initState), initGrid);
  }

  @Override
  String statusIntToStr(int status) throws Exception {
    if (status == 0) {
      return FIRE_EMPTY;
    } else if (status == 1) {
      return FIRE_TREE;
    } else if (status == 2) {
      return FIRE_BURNING;
    } else {
      //TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(HashMap<String, Double> params) {
    sim = new Fire(FIRE_EMPTY, FIRE_TREE, FIRE_BURNING, params.get("probCatch"));
    corners = false;
  }
}

