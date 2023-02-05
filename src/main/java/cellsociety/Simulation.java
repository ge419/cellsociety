package cellsociety;

/*
 * @author Brandon Weiss
 */
public abstract class Simulation {
    private String deadState;
    private String aliveState;

    public Simulation(String deadString, String aliveString) {
        deadState = deadString;
        aliveState = aliveString;
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        return cell.getStatus();
    }

    private int countAliveNeighbors(List<Cell> neighbors) {
        int count = 0;
        for (Cell cell : neighbors) {
            if (cell.getStatus().equals(aliveState)) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        return "Game of " + deadState + " and " + aliveState + " cells.";
    }
}