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
  }

  abstract String statusIntoStr(int status) throws Exception;

  private List<List<Integer>> strToGrid(String initState) {
    List<List<String>> stateArr = new ArrayList<>(width);
    String[] splitInit = initState.split("\n");

    for (int i = 0; i < splitInit.length; i++) {
      List<String> row = new ArrayList<>(height);
      String[] rowSplit = splitInit[i].split(" ");
      Collections.addAll(row, rowSplit);
      for (int j = 0; j < 4; j++) {
        row.remove("");
      }
      //System.out.println(row);
      stateArr.add(i, row);
    }
    return strIntConverter(stateArr);
  }

  private List<List<Integer>> strIntConverter(List<List<String>> stateList) {
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

  private void listToGrid(List<List<Integer>> intGrid) {
    grid = new Grid();
    for (int i = 0; i < intGrid.size(); i++) {
      for (int j = 0; j < intGrid.get(0).size(); j++) {
        String status = statusIntToStr(intGrid.get(i).get(j));
        grid.setCell(i, j, status);
      }
    }
  }

}
