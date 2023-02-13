package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Life;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Changmin Shin
 */

public class LifeEngine extends SimEngine {
  private static final String LIFE_ALIVE = NAMES_FILE.getString("LifeAlive");
  private static final String LIFE_DEAD = NAMES_FILE.getString("LifeDead");

  public LifeEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
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
      return LIFE_DEAD;
    } else if (status == 1) {
      return LIFE_ALIVE;
    } else {
      // TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(Map<String, Double> params) {
    sim = new Life(LIFE_DEAD, LIFE_ALIVE);
  }

  @Override
  public void updateGameState() {
    saveNextState();
    updateNextState();
  }

  @Override
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
    neighbors.addAll(findCornerNeighbors(cell));
    return neighbors;
  }
}
