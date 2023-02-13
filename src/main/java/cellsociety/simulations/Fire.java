package cellsociety.simulations;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;
import cellsociety.Cells.FireCell;

/**
 * Spreading Fire simulation
 *
 * @author Brandon Weiss
 */
public class Fire extends Simulation {
  public static final Random RAND_NUM_GEN = new Random();
  private String burningState;
  private double probCatch;

  /**
   * @param emptyString   The string representing a cell in the empty state
   * @param treeString    The string representing a cell in the tree state
   * @param burningString The string representing a cell in the burning state
   * @param probCatch     The probability of a tree catching fire if at least 1
   *                      neighbor is burning
   */
  public Fire(String emptyString, String treeString, String burningString, double probCatch) {
    super(emptyString, treeString);
    burningState = burningString;
    setProbCatch(probCatch);
  }

  public void setProbCatch(double probCatch) {
    this.probCatch = probCatch;
  }

  /**
   * @param cell      A cell for which to calculate the next state
   * @param neighbors A list of neighbor cells that are next to cell
   * @return The next state of a cell according to the rules of Spreading Fire
   */
  @Override
  public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
    int burning = countNeighbors(neighbors, burningState);
    return toggleCell(cell, burning);
  }

  /**
   * Spreading Fire rules:
   * An empty cell stays empty
   * A burning cell becomes empty
   * If a living cell has at least 1 burning neighbor,
   * then it burns with a probability according to probCatch
   * A living cell with no burning neighbors will stay living
   * 
   * @param cell       A cell for which to calculate the next state
   * @param numBurning The number of neighbors that are in the burning state
   * @return The next state of a cell
   */
  private String toggleCell(Cell cell, int numBurning) {
    double randDouble = RAND_NUM_GEN.nextDouble();
    if (!cell.getStatus().equals(getAliveString())) {
      return getDeadString();
    }
    if (numBurning > 0 && randDouble < probCatch) {
      return burningState;
    }
    return getAliveString();
  }

  /**
   * @throws Exception
   * @see cellsociety.simulations.Simulation#randomize(HashMap, int, int)
   *      perTree - fraction of cells to initialize as not empty
   *      perFire - of non-empty cells, fraction that should be burning
   */
  public Cell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) throws Exception {
    double trees = 0.8;
    double burning = 0.1;
    try {
      trees = parameters.get("perTree");
      burning = parameters.get("perFire");
    } catch (Exception e) {
    }
    FireCell cell = new FireCell(xCoordinate, yCoordinate);
    if (RAND_NUM_GEN.nextDouble() < trees) {
      cell.setStatus(getDeadString());
    } else if (RAND_NUM_GEN.nextDouble() < burning) {
      cell.setStatus(burningState);
    } else {
      cell.setStatus(getAliveString());
    }
    return cell;
  }
}
