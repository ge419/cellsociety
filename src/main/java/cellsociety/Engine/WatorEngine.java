package cellsociety.Engine;

import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import cellsociety.simulations.WaTor;
import java.util.HashMap;

/**
 * @author Changmin Shin
 */

public class WatorEngine extends SimEngine {

  private static final String WATOR_EMPTY = NAMES_FILE.getString("WTEmpty");
  private static final String WATOR_SHARK = NAMES_FILE.getString("WTShark");
  private static final String WATOR_FISH = NAMES_FILE.getString("WTFish");

  private Simulation sim;
  private boolean corners;

  public WatorEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      HashMap<String, Double> params)
      throws Exception {
    super(visualGrid, initState, grid, initGrid, params);
    // setup initial state grid for both updating grid and initial grid
    listToGrid(strToGrid(initState), grid);
    listToGrid(strToGrid(initState), initGrid);
    // initialize simulation
    init(params);
  }

  @Override
  String statusIntToStr(int status) throws Exception {
    if (status == 0) {
      return WATOR_EMPTY;
    } else if (status == 1) {
      return WATOR_FISH;
    } else if (status == 2) {
      return WATOR_SHARK;
    } else {
      //TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(HashMap<String, Double> params) {
    sim = new WaTor(WATOR_EMPTY, WATOR_FISH, WATOR_SHARK, params.get("eShark"),
        params.get("ePerFish"), params.get("fishBT"), params.get("sharkBT"));
    corners = false;
  }
}

