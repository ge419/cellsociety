package cellsociety.simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;
import cellsociety.Cells.SchellingCell;

/**
 * Schelling's model of segregation
 *
 * @author Brandon Weiss
 */
public class Schelling extends Simulation {
  public static final Random RAND_NUM_GEN = new Random();
  private double threshold;
  private String stateAString;
  private String stateBString;
  private List<Cell> empty;
  private List<String> move;

  /**
   * @param emptyString  The string representing an empty cell
   * @param stateAString The string representing a cell in state A
   * @param stateBString The string representing a cell in state B
   * @param threshold    The minimum fraction of neighbors needed to be the same
   *                     type for a cell to not move
   */
  public Schelling(String emptyString, String stateAString, String stateBString, double threshold) {
    super(emptyString, "");
    setThreshold(threshold);
    this.stateAString = stateAString;
    this.stateBString = stateBString;
    empty = new ArrayList<>();
    move = new ArrayList<>();
  }

  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  public void addEmptyCell(Cell cell) {
    empty.add(cell);
  }

  /**
   * @param cell      A cell for which to calculate the next state
   * @param neighbors A list of neighbor cells that are next to cell
   * @return The next state of a cell according to the rules of Schelling's model
   *         of segregation
   */
  @Override
  public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
    int sameCell = countNeighbors(neighbors, cell.getStatus());
    int totalNeighbors = neighbors.size() - countNeighbors(neighbors, getDeadString());
    return toggleCell(cell, ((double) sameCell) / totalNeighbors);
  }

  /**
   * Schelling's model of segregation rules:
   * if the ratio of neighbor cells that are the same type as a given cell to the
   * total number of neighbors is less than the threshold value, then the cell
   * moves to an empty space.
   * 
   * @param cell  A cell for which to calculate the next state
   * @param ratio The ratio of occupied neighbor cells that are the same type as
   *              cell
   * @return The next state of a cell
   */
  private String toggleCell(Cell cell, double ratio) {
    if (cell.getStatus().equals(getDeadString()) || ratio >= threshold) {
      return cell.getStatus();
    }
    move.add(cell.getStatus());
    addEmptyCell(cell);
    return getDeadString();
  }

  /**
   * Update the statuses of empty cells that have another cell "move into" that
   * space
   */
  public void moveCells() {
    if (empty.size() < move.size()) {
      return;
    }
    Cell hold;
    for (String s : move) {
      hold = empty.get(0);
      hold.setStatus(s);
      empty.remove(hold);
    }
    move.removeAll(move);
  }

  /**
   * @see cellsociety.simulations.Simulation#randomize(HashMap, int, int)
   *      perEmpty - fraction of cells to initialize as empty
   *      perStateOne - of non-empty cells, fraction that should be in stateA
   */
  public Cell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
    double empty = parameters.get("perEmpty");
    double stateA = parameters.get("perStateOne");
    Cell cell = new SchellingCell(xCoordinate, yCoordinate);
    if (RAND_NUM_GEN.nextDouble() < empty) {
      cell.setStatus(getDeadString());
    } else if (RAND_NUM_GEN.nextDouble() < stateA) {
      cell.setStatus(stateAString);
    } else {
      cell.setStatus(stateBString);
    }
    return cell;
  }
}
