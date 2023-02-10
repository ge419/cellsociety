package cellsociety.simulations;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;

/**
 * Conway's Game of Life
 *
 * @author Brandon Weiss
 */
public class Life extends Simulation {

  public static final Random RAND_NUM_GEN = new Random();

  /**
   * @param deadString  The string representing a cell in the dead state
   * @param aliveString The string representing a cell in the alive state
   */
  public Life(String deadString, String aliveString) {
    super(deadString, aliveString);
  }

  /**
   * @param cell      A cell for which to calculate the next state
   * @param neighbors A list of neighbor cells that are next to cell
   * @return The next state of a cell according to the rules of Game of Life
   */
  @Override
  public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
    int alive = countNeighbors(neighbors, getAliveString());
    return toggleCell(cell, alive);
  }

  /**
   * Game of Life rules: if there are 2 alive neighbors, the state does not change if there are 3
   * alive neighbors, the cell's next state is alive if there are less than 2 or more than 3 alive
   * neighbors, the cell's next state is dead
   *
   * @param cell     A cell for which to calculate the next state
   * @param numAlive The number of neighbors that are in the alive state
   * @return The next state of a cell
   */
  private String toggleCell(Cell cell, int numAlive) {
    if (numAlive == 2) {
      return cell.getStatus();
    }
    if (numAlive == 3) {
      return getAliveString();
    }
    return getDeadString();
  }

  /**
   * @see cellsociety.simulations.Simulation#randomize(java.util.HashMap, int, int) parameters used:
   * perAlive - fraction of cells to initialize as alive
   */
  public Cell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
    double alive = parameters.get("perAlive");
    Cell cell = new Cell(xCoordinate, yCoordinate);
    if (RAND_NUM_GEN.nextDouble() < alive) {
      cell.setStatus(getAliveString());
    } else {
      cell.setStatus(getDeadString());
    }
    return cell;
  }
}
