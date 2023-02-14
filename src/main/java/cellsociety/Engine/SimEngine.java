package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Changmin Shin, Brandon Weiss
 */

public abstract class SimEngine implements EngineInterface {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);

  VisualGrid visualGrid;
  int width;
  int height;
  Grid grid;
  Grid initGrid;
  Map<String, Double> params;
  Simulation sim;
  List<String> nextStates;

  /**
   *
   * @param visualGrid VisualGrid object that displays the simulation
   * @param initState  String that stores the initial status of each Cell
   * @param grid       Grid data structure that stores the state of each cell as
   *                   it is updated
   * @param initGrid   Grid date structure that stores the initial status of each
   *                   Cell
   * @param params     HashMap that contains parameters
   */
  public SimEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      Map<String, Double> params) {
    this.visualGrid = visualGrid;
    this.width = visualGrid.getWidth();
    this.height = visualGrid.getHeight();
    this.grid = grid;
    this.initGrid = initGrid;
    this.params = params;
    this.sim = sim;
  }

  /**
   *
   * @param status The status of a Cell in integer
   * @return The String status of the corresponding integer
   * @throws Exception If the parameter status is not within the scope of possible
   *                   values
   */
  abstract String statusIntToStr(int status) throws Exception;

  /**
   * Converts initial state that is read as String into a matrix of integers
   * 
   * @param initState Initial state of Cells read from the XML file
   * @return Matrix of integers that represent Cell status
   */
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
      //System.out.println(stateArr);
    }
    return strIntConverter(stateArr);
  }

  /**
   * Converts the matrix of Strings that represent Cell status into matrix of
   * Integers
   * 
   * @param stateList Matrix of Strings that represent the Cell status, generated
   *                  in strToGrid()
   * @return Matrix of Integers that represent the Cell status
   */
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

  /**
   * Converts the matrix of Integers representing Cell status into a Grid data
   * structure
   * 
   * @param intGrid Matrix of Integers representing Cell status
   * @param grid    Grid data structure that is being modified
   * @throws Exception If the given status is not within the scope of possible
   *                   values
   *                   This is thrown at statusIntToStr()
   */
  public void listToGrid(List<List<Integer>> intGrid, Grid grid) throws Exception {
    for (int i = 0; i < grid.getRowNum(); i++) {
      for (int j = 0; j < grid.getColNum(); j++) {
        String status = statusIntToStr(intGrid.get(i).get(j));
        grid.setCell(i, j, status);
      }
    }
  }

  /**
   * Initializes simulation
   * 
   * @param params HashMap that contains parameters
   */
  abstract void init(Map<String, Double> params);

  /**
   * Updates the backend Grid and VisualGrid to next generation
   */
  public abstract void updateGameState();

  /**
   * Helper method for updateGameState(), loops through the grid and saves the
   * updated cell status to nextStates
   */
  public void saveNextState() {
    nextStates = new ArrayList<>();
    Cell hold;
    for (int r = 0; r < grid.getRowNum(); r++) {
      for (int c = 0; c < grid.getColNum(); c++) {
        hold = grid.getCell(r, c);
        nextStates.add(sim.getUpdatedCellStatus(hold, findNeighbors(hold)));
      }
    }
  }

  /**
   * Helper method for updateGameState(), loops through the grid and updates Grid
   */
  public void updateNextState() {
    String next;
    for (int r = 0; r < grid.getRowNum(); r++) {
      for (int c = 0; c < grid.getColNum(); c++) {
        next = nextStates.get(r * grid.getColNum() + c);
        getCell(r, c).setStatus(next);
        // visualGrid.updateGrid(r, c, next);
      }
    }
  }

  /**
   * @param cell The cell whose neighbors are to be found
   * @return ArrayList of Cells that are corner neighbors of the given cell
   */
  // TODO: REFACTOR --> removed parameter corners as this methods gets implemented
  // --> possible issue: duplicate code in multiple
  public abstract List<Cell> findNeighbors(Cell cell);

  /**
   * @param cell The cell whose corner neighbors are to be found
   * @return ArrayList of Cells that are corner neighbors of the given cell
   */
  public List<Cell> findCornerNeighbors(Cell cell) {
    List<Cell> corner = new ArrayList<>();
    if (cell.getX() != 0 && cell.getY() != 0) {
      corner.add(getCell(cell.getX() - 1, cell.getY() - 1));
    }
    if (cell.getX() != width - 1 && cell.getY() != 0) {
      corner.add(getCell(cell.getX() + 1, cell.getY() - 1));
    }
    if (cell.getX() != 0 && cell.getY() != height - 1) {
      corner.add(getCell(cell.getX() - 1, cell.getY() + 1));
    }
    if (cell.getX() != width - 1 && cell.getY() != height - 1) {
      corner.add(getCell(cell.getX() + 1, cell.getY() + 1));
    }
    return corner;
  }

  /**
   * Resets the backend Grid, (simulation configuration) to its initial state
   */
  public void reset() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        grid.setCell(i, j, initGrid.getCell(i, j).getStatus());
      }
    }
    // TODO: determine if grid.reset() should be used instead
    // --> is Grid the only thing being reset?
  }

  /**
   * Sets the backend Grid to have Dead/Empty state in all Cells as starting
   * configuration
   * TODO: make getDeadString() an abstract method in Simulation, each simulations
   * will implement differently
   */
  public void blankStart() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        grid.setCell(i, j, sim.getDeadString());
      }
    }
  }

  /**
   * Randomize the starting configuration for a simulation
   * 
   * @throws Exception
   */
  public void randomizeStart() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // TODO: Change sim.randomize to return String status, not Cell itself after
        // discussing with Brandon
        Cell random = sim.randomize((HashMap<String, Double>) params, i, j);
        grid.setCell(i, j, random.getStatus());
      }
    }
  }

  public Cell getCell(int x, int y) {
    return grid.getCell(x, y);
  }

  public Grid getGrid() {
    return grid;
  }

  public Map<String, Double> getParams() {
    return params;
  }
}
