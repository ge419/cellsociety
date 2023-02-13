package cellsociety.Engine;

import cellsociety.Grid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import cellsociety.Cells.Cell;
import cellsociety.Cells.WatorCell;
import cellsociety.GUI.VisualGrid;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;
import cellsociety.simulations.WaTor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Brandon Weiss, Changmin Shin
 */
public abstract class SimulationEngine implements EngineInterface {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
  private static final String SEG_NAME = NAMES_FILE.getString("SegName");
  private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
  private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
  private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
  private static final String PERC_NAME = NAMES_FILE.getString("PercolName");
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

  private Simulation sim;
  private String simType;
  private VisualGrid visualGrid;
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
    //init(simType, params);
    this.simType = simType;
    this.visualGrid = visualGrid;
    this.width = visualGrid.getWidth();
    this.height = visualGrid.getHeight();
    this.initState = state;
    blankStart();
    // Decodes String status and creates Grid
    //listToGrid(strToGrid(state));
  }

  /**
   * @param simType The string representing which of the cellular automata to run
   * @param params  A HashMap of parameters and values for each simulation type
   */
//  public void init(String simType, HashMap<String, Double> params) {
//    if (simType.equals(LIFE_NAME)) {
//      sim = new Life(LIFE_DEAD, LIFE_ALIVE);
//      corners = true;
//    } else if (simType.equals(FIRE_NAME)) {
//      sim = new Fire(FIRE_EMPTY, FIRE_TREE, FIRE_BURNING, params.get("probCatch"));
//      corners = false;
//    } else if (simType.equals(SEG_NAME)) {
//      sim = new Schelling(SEG_EMPTY, SEG_A, SEG_B, params.get("change"));
//      corners = true;
//    } else if (simType.equals(WATOR_NAME)) {
//      sim = new WaTor(WATOR_EMPTY, WATOR_FISH, WATOR_SHARK, params.get("eShark"),
//          params.get("ePerFish"), params.get("fishBT"), params.get("sharkBT"));
//      corners = false;
//    } else if (simType.equals(PERC_NAME)) {
//      // sim = new Percolation()
//      corners = true;
//    }
//  }

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
  //TODO implement a reset back to starting condition
  @Override
  public void reset() {

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
        //input = new Cell(i, j);
        //input.setStatus(sim.getDeadString());
        //column.add(input);
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
//          visualGrid.updateCell(i, j, next);
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
