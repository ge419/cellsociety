package cellsociety.simulations;

import java.util.HashMap;
import java.util.List;
import cellsociety.Cells.Cell;

/**
 * @author Brandon Weiss
 */
public abstract class Simulation {

  private String deadState;
  private String aliveState;

  /**
   * @param deadString  A String to represent a dead/empty cell
   * @param aliveString A String to represent a living cell when only one kind exists
   */
  public Simulation(String deadString, String aliveString) {
    deadState = deadString;
    aliveState = aliveString;
  }

  /**
   * @param neighbors A list of neighbor cells of a given cell
   * @param state     A string representing the state to be counted in neighbors
   * @return The number of neighbors cells in with a status of state
   */
  public int countNeighbors(List<Cell> neighbors, String state) {
    int count = 0;
    for (Cell cell : neighbors) {
      if (cell.getStatus().equals(state)) {
        count++;
      }
    }
    return count;
  }

  public String getAliveString() {
    return aliveState;
  }

  public String getDeadString() {
    return deadState;
  }

  public String getUpdatedCellStatus(Cell hold, List<Cell> findNeighbors) {
    return hold.getStatus();
  }

  /**
   * @param params A HashMap of parameters for a given simulation
   * @param xPos   An int representing the x position of a cell in the grid
   * @param yPos   An int representing the y position of a cell in the grid
   * @return A new cell of a randomized state according to the parameters
   */
  public abstract Cell randomize(HashMap<String, Double> params, int xPos, int yPos);
}
