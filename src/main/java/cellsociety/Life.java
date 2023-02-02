package cellsociety;

/*
 * @author Brandon Weiss
 */
public class Life extends Simulation {

    public Life(String deadString, String aliveString) {
        super(deadString, aliveString);
    }

    @Override
    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int alive = super.countAliveNeighbors(neighbors);
        return toggleCell(cell, alive);
    }

    public String toggleCell(Cell cell, int numAlive) {
        if (numAlive == 2) {
            return cell.getStatus();
        }
        if (numAlive == 3) {
            return super.aliveState;
        }
        return super.deadState;
    }

    @Override
    public String toString() {
        return "Conway's Game of Life.";
    }

}
