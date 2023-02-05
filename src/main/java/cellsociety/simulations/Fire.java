package cellsociety.simulations;

import java.util.List;
import java.util.Random;
import cellsociety.Cell;

public class Fire extends Simulation {
    public static final Random RAND_NUM_GEN = new Random();
    private String burningState;
    private double probCatch;

    public Fire(String emptyString, String treeString, String burningString, double probCatch){
        super(emptyString, treeString);
        burningState = burningString;
        this.probCatch = probCatch;
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int burning = super.countNeighbors(neighbors, burningState);
        return toggleCell(cell, burning);
    }

    public String toggleCell(Cell cell, int numBurning){
        double randDouble = RAND_NUM_GEN.nextDouble();
        if (!cell.getStatus().equals(getAliveString())){
            return getDeadString();
        }
        if (randDouble < probCatch){
            return burningState;
        }
        return getAliveString();
    }

    @Override
    public String toString() {
        return "Spreading Fire simulation.";
    }
}
