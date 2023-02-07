package cellsociety.simulations;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;

/*
 * @author Brandon Weiss
 */
public class Fire extends Simulation {
    public static final Random RAND_NUM_GEN = new Random();
    private String burningState;
    private double probCatch;

    public Fire(String emptyString, String treeString, String burningString, double probCatch) {
        super(emptyString, treeString);
        burningState = burningString;
        setProbCatch(probCatch);
    }

    public void setProbCatch(double probCatch) {
        this.probCatch = probCatch;
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int burning = countNeighbors(neighbors, burningState);
        return toggleCell(cell, burning);
    }

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

    public Cell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
        double trees = parameters.get("perTree");
        double burning = parameters.get("perFire");
        Cell cell = new Cell(xCoordinate, yCoordinate);
        if (RAND_NUM_GEN.nextDouble() < trees) {
            cell.setStatus(getDeadString());
        } else if (RAND_NUM_GEN.nextDouble() < burning) {
            cell.setStatus(burningState);
        } else {
            cell.setStatus(getAliveString());
        }
        return cell;
    }

    @Override
    public String toString() {
        return "Spreading Fire simulation.";
    }
}
