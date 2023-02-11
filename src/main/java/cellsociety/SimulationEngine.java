package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import cellsociety.Cells.Cell;
import cellsociety.Cells.WatorCell;
import cellsociety.GUI.VisualGrid;
import cellsociety.simulations.Fire;
import cellsociety.simulations.Life;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;
import cellsociety.simulations.WaTor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Brandon Weiss, Changmin Shin
 */
public class SimulationEngine implements SimulationController{

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
  private static final String SEG_NAME = NAMES_FILE.getString("SegName");
  private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
  private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
  private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
  private static final String PERC_NAME = NAMES_FILE.getString("PercolName");
  private static final String LIFE_ALIVE = NAMES_FILE.getString("LifeAlive");
  private static final String LIFE_DEAD = NAMES_FILE.getString("LifeDead");
  private static final String FIRE_EMPTY = NAMES_FILE.getString("FireEmpty");
  private static final String FIRE_TREE = NAMES_FILE.getString("FireTree");
  private static final String FIRE_BURNING = NAMES_FILE.getString("FireBurning");
  private static final String SEG_EMPTY = NAMES_FILE.getString("SegEmpty");
  private static final String SEG_A = NAMES_FILE.getString("SegA");
  private static final String SEG_B = NAMES_FILE.getString("SegB");
  private static final String WATOR_EMPTY = NAMES_FILE.getString("WTEmpty");
  private static final String WATOR_SHARK = NAMES_FILE.getString("WTShark");
  private static final String WATOR_FISH = NAMES_FILE.getString("WTFish");
  private static final String PERC_BLOCK = NAMES_FILE.getString("PercolBlock");
  private static final String PERC_OPEN = NAMES_FILE.getString("PercolOpen");
  private static final String PERC_PERC = NAMES_FILE.getString("PercolPerc");

  // Potential Bug grid object here is not same grid object
  private Simulation sim;
  private String simType;
  private int width;
  private int height;
  private String initState;
  private List<List<Cell>> cells;
  private boolean corners;
  private Grid grid;

  /**
   * @param simType    The string representing which of the cellular automata to run
   * @param params     A HashMap of parameters and values for each simulation type
   * @param grid       The class that contains grid data
   */
  public SimulationEngine(String simType, HashMap<String, Double> params, Grid grid,
      String state) {
    init(simType, params);
    this.simType = simType;
    this.grid = grid;
    this.width = grid.getWidth();
    this.height = grid.getHeight();
    this.initState = state;
    blankStart();
    // Decodes String status and creates Grid
    listToGrid(strToGrid(state));
  }

  // TODO: replace string literals in params.get() calls with strings from
  // properties file

  /**
   * @param simType The string representing which of the cellular automata to run
   * @param params  A HashMap of parameters and values for each simulation type
   */
  public void init(String simType, HashMap<String, Double> params) {
    if (simType.equals(LIFE_NAME)) {
      sim = new Life(LIFE_DEAD, LIFE_ALIVE);
      corners = true;
      // cells = Grid
    } else if (simType.equals(FIRE_NAME)) {
      sim = new Fire(FIRE_EMPTY, FIRE_TREE, FIRE_BURNING, params.get("probCatch"));
      corners = false;
    } else if (simType.equals(SEG_NAME)) {
      sim = new Schelling(SEG_EMPTY, SEG_A, SEG_B, params.get("change"));
      corners = true;
    } else if (simType.equals(WATOR_NAME)) {
      sim = new WaTor(WATOR_EMPTY, WATOR_FISH, WATOR_SHARK, params.get("eShark"),
          params.get("ePerFish"), params.get("fishBT"), params.get("sharkBT"));
      corners = false;
    } else if (simType.equals(PERC_NAME)) {
      // sim = new Percolation()
      corners = true;
    }
  }

  /**
   * Randomize the starting configuration for a simulation
   *
   * @param parameters A HashMap of parameters and values for each simulation type
   * @param simType    The string representing which of the cellular automata to run
   */
  public void randomizeStart(HashMap<String, Double> parameters, String simType) {
    cells = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      ArrayList<Cell> column = new ArrayList<>();
      for (int j = 0; j < height; j++) {
        column.add(sim.randomize(parameters, i, j));
      }
      cells.add(column);
    }
  }

  /**
   * Set the starting configuration for a blank simulation
   * <p>
   * //@param simType The string representing which of the cellular automata to run
   */
  public void blankStart() {
    Cell input;
    cells = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      ArrayList<Cell> column = new ArrayList<>();
      for (int j = 0; j < height; j++) {
        input = new Cell(i, j);
        input.setStatus(sim.getDeadString());
        column.add(input);
      }
      cells.add(column);
    }
  }

  public void updateGameState() {
    if (simType.equals(WATOR_NAME)) {
      for (WatorCell fish : ((WaTor) sim).getFishCells()) {
        ((WaTor) sim).moveCell(fish, findNeighbors(fish, corners));
      }
      for (WatorCell shark : ((WaTor) sim).getSharkCells()) {
        ((WaTor) sim).moveCell(shark, findNeighbors(shark, corners));
      }
    } else {
      ArrayList<String> nextStates = new ArrayList<>();
      List<Cell> column;
      Cell hold;
      for (int i = 0; i < cells.size(); i++) {
        column = cells.get(i);
        for (int j = 0; j < column.size(); j++) {
          hold = column.get(j);
          nextStates.add(sim.getUpdatedCellStatus(hold, findNeighbors(hold, corners)));
          if (simType.equals(SEG_NAME)) {
            ((Schelling) sim).moveCells();
          }
        }
      }
      String next;
      for (int i = 0; i < cells.size(); i++) {
        for (int j = 0; j < cells.get(i).size(); j++) {
          next = nextStates.get(i * cells.get(i).size() + j);
          //getCell(i, j).setStatus(next);
          visualGrid.updateGrid(i, j, next);
        }
      }
    }
  }

  /**
   * @param cell    The cell whose neighbors are desired
   * @param corners whether to include diagonally adjacent cells as neighbors
   * @return A list of cells adjacent to cell
   */
  private List<Cell> findNeighbors(Cell cell, boolean corners) {
    List<Cell> neighbors = new ArrayList<>();
    boolean isWator = simType.equals(WATOR_NAME);
    if (isWator && cell.getX() == 0) {
      neighbors.add(getCell(width - 1, cell.getY()));
    }
    if (isWator && cell.getY() == 0) {
      neighbors.add(getCell(cell.getX(), height - 1));
    }
    if (isWator && cell.getX() == width - 1) {
      neighbors.add(getCell(0, cell.getY()));
    }
    if (isWator && cell.getX() == height - 1) {
      neighbors.add(getCell(cell.getX(), 0));
    }
    if (cell.getX() != 0) {
      neighbors.add(getCell(cell.getX() - 1, cell.getY()));
    }
    if (cell.getX() != width - 1) {
      neighbors.add(getCell(cell.getX() + 1, cell.getY()));
    }
    if (cell.getY() != 0) {
      neighbors.add(getCell(cell.getX(), cell.getY() - 1));
    }
    if (cell.getY() != height - 1) {
      neighbors.add(getCell(cell.getX(), cell.getY() + 1));
    }
    if (corners) {
      if (cell.getX() != 0 && cell.getY() != 0) {
        neighbors.add(getCell(cell.getX() - 1, cell.getY() - 1));
      }
      if (cell.getX() != width - 1 && cell.getY() != 0) {
        neighbors.add(getCell(cell.getX() + 1, cell.getY() - 1));
      }
      if (cell.getX() != 0 && cell.getY() != height - 1) {
        neighbors.add(getCell(cell.getX() - 1, cell.getY() + 1));
      }
      if (cell.getX() != width - 1 && cell.getY() != height - 1) {
        neighbors.add(getCell(cell.getX() + 1, cell.getY() + 1));
      }
    }
    return neighbors;
  }

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
        String status = statusIntToStr(simType, intGrid.get(i).get(j));
        grid.setCell(i, j, status);
      }
    }
  }

  //TODO: Refactor code --> create interface of simulation engine, create engine for each simulation

  /**
   * Takes integer value of status, returns the Cell state string according to the simType
   * @param simType The type of simulation
   * @param status  Integer value of status(read from matrix of integers)
   * @return
   */
  private String statusIntToStr(String simType, int status) {
    switch (simType) {
      case LIFE_NAME -> {
        if (status == 0) {
          return LIFE_DEAD;
        } else if (status == 1) {
          return LIFE_ALIVE;
        } else {
          //TODO: define exception here, change code accordingly
          throw new Exception(showMessage(AlertType.ERROR, "Invalid Status"), e);
        }
      }
      case FIRE_NAME -> {
        if (status == 0) {
          return FIRE_EMPTY;
        } else if (status == 1) {
          return FIRE_TREE;
        } else if (status == 2) {
          return FIRE_BURNING;
        } else {
          //TODO: define exception here, change code accordingly
          throw new Exception(showMessage(AlertType.ERROR, "Invalid Status"), e);
        }
      }
      case SEG_NAME -> {
        if (status == 0) {
          return SEG_EMPTY;
        } else if (status == 1) {
          return SEG_A;
        } else if (status == 2) {
          return SEG_B;
        } else {
          //TODO: define exception here, change code accordingly
          throw new Exception(showMessage(AlertType.ERROR, "Invalid Status"), e);
        }
      }
      case WATOR_NAME -> {
        if (status == 0) {
          return WATOR_EMPTY;
        } else if (status == 1) {
          return WATOR_FISH;
        } else if (status == 2) {
          return WATOR_SHARK;
        } else {
          //TODO: define exception here, change code accordingly
          throw new Exception(showMessage(AlertType.ERROR, "Invalid Status"), e)
        }
      }
      //TODO: define exception here, change code accordingly
      default -> throw new Exception(showMessage(AlertType.ERROR, "Invalid Simulation Name"), e)
    }

  }

  /**
   * Creates an alert with custom message
   * @param type
   * @param message
   */
  private void showMessage(AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  private Cell getCell(int x, int y) {
    return cells.get(x).get(y);
  }
}
