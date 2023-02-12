package cellsociety.Engine;

import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;
import java.util.HashMap;

/**
 * @author Changmin Shin
 */

public class SegEngine extends SimEngine {

  private static final String SEG_EMPTY = NAMES_FILE.getString("SegEmpty");
  private static final String SEG_A = NAMES_FILE.getString("SegA");
  private static final String SEG_B = NAMES_FILE.getString("SegB");

  private Simulation sim;
  private boolean corners;

  public SegEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      HashMap<String, Double> params)
      throws Exception {
    super(visualGrid, initState, grid, initGrid, params);
    // setup initial state grid for both updating grid and initial grid
    listToGrid(strToGrid(initState), grid);
    listToGrid(strToGrid(initState), initGrid);
    init(params);
  }

  @Override
  String statusIntToStr(int status) throws Exception {
    if (status == 0) {
      return SEG_EMPTY;
    } else if (status == 1) {
      return SEG_A;
    } else if (status == 2) {
      return SEG_B;
    } else {
      //TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(HashMap<String, Double> params) {
    sim = new Schelling(SEG_EMPTY, SEG_A, SEG_B, params.get("change"));
    corners = true;
  }
}

