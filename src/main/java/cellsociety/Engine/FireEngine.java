package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Fire;
import cellsociety.simulations.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Changmin Shin, Brandon Weiss
 */

public class FireEngine extends SimEngine {

  private static final String FIRE_EMPTY = NAMES_FILE.getString("FireEmpty");
  private static final String FIRE_TREE = NAMES_FILE.getString("FireTree");
  private static final String FIRE_BURNING = NAMES_FILE.getString("FireBurning");

  public FireEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      Map<String, Double> params)
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
      return FIRE_EMPTY;
    } else if (status == 1) {
      return FIRE_TREE;
    } else if (status == 2) {
      return FIRE_BURNING;
    } else {
      // TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(Map<String, Double> params) {
    double probCatch = 0.5;
    try {
      probCatch = params.get("probCatch");
    } catch (Exception e) {
    }
    sim = new Fire(FIRE_EMPTY, FIRE_TREE, FIRE_BURNING, probCatch);
  }

  @Override
  public void updateGameState() {
    saveNextState();
    updateNextState();
  }

  public List<Cell> findNeighbors(Cell cell) {
    List<Cell> neighbors = new ArrayList<>();
    if (cell.getX() != 0) {
      neighbors.add(getCell(cell.getX() - 1, cell.getY()));
    }
    if (cell.getX() != getGrid().getRowNum() - 1) {
      neighbors.add(getCell(cell.getX() + 1, cell.getY()));
    }
    if (cell.getY() != 0) {
      neighbors.add(getCell(cell.getX(), cell.getY() - 1));
    }
    if (cell.getY() != getGrid().getColNum() - 1) {
      neighbors.add(getCell(cell.getX(), cell.getY() + 1));
    }
    return neighbors;
  }

  @Override
  public String gridToStr(Grid grid) {
    List<List<String>> strGrid = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<String> row = new ArrayList<>();
      for (int j = 0; j <width; j++) {
        String status = grid.getCell(i, j).getStatus();
        if (status.equals(FIRE_EMPTY)) {
          row.add(j, "0");
        }
        else if (status.equals(FIRE_TREE)) {
          row.add(j, "1");
        }
        else if(status.equals(FIRE_BURNING)) {
          row.add(j, "2");
        }
      }
      strGrid.add(i, row);
    }
    return arrListToStr(strGrid);
  }

  @Override
  public void setParamValue(String param, Double newValue) {
    super.setParamValue(param, newValue);
    init(params);
  }
}
