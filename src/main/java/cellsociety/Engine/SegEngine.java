package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Changmin Shin, Brandon Weiss
 */

public class SegEngine extends SimEngine {

  private static final String SEG_EMPTY = NAMES_FILE.getString("SegEmpty");
  private static final String SEG_A = NAMES_FILE.getString("SegA");
  private static final String SEG_B = NAMES_FILE.getString("SegB");

  public SegEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
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
      return SEG_EMPTY;
    } else if (status == 1) {
      return SEG_A;
    } else if (status == 2) {
      return SEG_B;
    } else {
      // TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(Map<String, Double> params) {
    double change = 0.3;
    try {
      change = params.get("change");
    } catch (Exception e) {
    }
    sim = new Schelling(SEG_EMPTY, SEG_A, SEG_B, change);
  }

  @Override
  public void updateGameState() {
    saveNextState();
    updateNextState();
  }

  @Override
  public void saveNextState() {
    nextStates = new ArrayList<>();
    Cell hold;
    for (int r = 0; r < getGrid().getRowNum(); r++) {
      for (int c = 0; c < getGrid().getColNum(); c++) {
        hold = getGrid().getCell(r, c);
        nextStates.add(sim.getUpdatedCellStatus(hold, findNeighbors(hold)));
        ((Schelling) sim).moveCells();
      }
    }
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

  @Override
  public void setParamValue(String param, Double newValue) {
    super.setParamValue(param, newValue);
    Double change = params.get("change");
    sim = new Schelling(SEG_EMPTY, SEG_A, SEG_B, change);
  }
}