package cellsociety.simulations;

import java.util.List;
import cellsociety.Cells.Cell;

/*
 * @author Brandon Weiss
 */
public class Life extends Simulation {

    public Life(String deadString, String aliveString) {
        super(deadString, aliveString);
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int alive = super.countNeighbors(neighbors, getAliveString());
        return toggleCell(cell, alive);
    }

    public String toggleCell(Cell cell, int numAlive) {
        if (numAlive == 2) {
            return cell.getStatus();
        }
        if (numAlive == 3) {
            return getAliveString();
        }
        return getDeadString();
    }

    @Override
    public String toString() {
        return "Conway's Game of Life.";
    }

}
