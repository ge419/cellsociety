package cellsociety.simulations;

import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;
import cellsociety.Cells.FireCell;

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

    public String getUpdatedCellStatus(FireCell cell, List<Cell> neighbors) {
        int burning = countNeighbors(neighbors, burningState);
        return toggleCell(cell, burning);
    }

    private String toggleCell(FireCell cell, int numBurning) {
        double randDouble = RAND_NUM_GEN.nextDouble();
        if (!cell.getStatus().equals(getAliveString())) {
            return getDeadString();
        }
        if (randDouble < probCatch) {
            return burningState;
        }
        return getAliveString();
    }

    @Override
    public String toString() {
        return "Spreading Fire simulation.";
    }
}
