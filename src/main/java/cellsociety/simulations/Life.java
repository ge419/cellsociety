package cellsociety.simulations;

import java.util.List;
import cellsociety.Cells.Cell;
import cellsociety.Cells.LifeCell;

/*
 * @author Brandon Weiss
 */
public class Life extends Simulation {

    public Life(String deadString, String aliveString) {
        super(deadString, aliveString);
    }

    public String getUpdatedCellStatus(LifeCell cell, List<Cell> neighbors) {
        int alive = countNeighbors(neighbors, getAliveString());
        return toggleCell(cell, alive);
    }

    private String toggleCell(LifeCell cell, int numAlive) {
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
