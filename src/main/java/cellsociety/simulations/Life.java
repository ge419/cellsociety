package cellsociety.simulations;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;

/*
 * @author Brandon Weiss
 */
public class Life extends Simulation {
    public static final Random RAND_NUM_GEN = new Random();

    public Life(String deadString, String aliveString) {
        super(deadString, aliveString);
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int alive = countNeighbors(neighbors, getAliveString());
        return toggleCell(cell, alive);
    }

    private String toggleCell(Cell cell, int numAlive) {
        if (numAlive == 2) {
            return cell.getStatus();
        }
        if (numAlive == 3) {
            return getAliveString();
        }
        return getDeadString();
    }

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

    @Override
    public String toString() {
        return "Conway's Game of Life.";
    }

}
