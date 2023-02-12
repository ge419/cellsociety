package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Changmin Shin, Brandon Weiss
 */

public abstract class SimEngine {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);

  private VisualGrid visualGrid;
  private int width;
  private int height;
  private String initState;
  private Grid grid;
  private Grid initGrid;
  private HashMap<String, Double> params;
  private Simulation sim;
  private boolean corners;

  /**
   *
   * @param visualGrid
   * @param initState
   * @param grid
   * @param params
   */
  public SimEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid, HashMap<String, Double> params) {
    this.visualGrid = visualGrid;
    this.width = visualGrid.getWidth();
    this.height = visualGrid.getHeight();
    this.initState = initState;
    this.grid = grid;
    this.initGrid = initGrid;
    this.params = params;
  }

  abstract String statusIntToStr(int status) throws Exception;
  abstract void init(HashMap<String, Double> params);

  public List<List<Integer>> strToGrid(String initState) {
    List<List<String>> stateArr = new ArrayList<>(width);
    String[] splitInit = initState.split("\n");

    for (int i = 0; i < splitInit.length; i++) {
      List<String> row = new ArrayList<>(height);
      String[] rowSplit = splitInit[i].split(" ");
      Collections.addAll(row, rowSplit);
      for (int j = 0; j < 4; j++) {
        row.remove("");
      }
      stateArr.add(i, row);
    }
    return strIntConverter(stateArr);
  }

  public List<List<Integer>> strIntConverter(List<List<String>> stateList) {
    List<List<Integer>> current = new ArrayList<>();
    for (List<String> state : stateList) {
      List<Integer> row = new ArrayList<>();
      for (String s : state) {
        row.add(Integer.parseInt(s));
      }
      current.add(row);
    }
    return current;
  }

  public void listToGrid(List<List<Integer>> intGrid, Grid grid) throws Exception {
    for (int i = 0; i < grid.getRowNum(); i++) {
      for (int j = 0; j < grid.getColNum(); j++) {
        String status = statusIntToStr(intGrid.get(i).get(j));
        grid.setCell(i, j, status);
      }
    }
  }

  public List<Cell> findNeighbors() {
    return null;
  }

  // reset method

  // blankStart method

  // randomizeStart method

}
