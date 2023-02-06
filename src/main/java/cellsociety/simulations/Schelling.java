package cellsociety.simulations;

import java.util.ArrayList;
import java.util.List;
import cellsociety.Cells.Cell;
import cellsociety.Cells.SchellingCell;

/*
 * @author Brandon Weiss
 */
public class Schelling extends Simulation {
    private double threshold;
    private List<SchellingCell> empty;
    private List<String> move;

    public Schelling(String emptyString, double threshold) {
        super(emptyString, "");
        setThreshold(threshold);
        empty = new ArrayList<>();
        move = new ArrayList<>();
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void addEmptyCell(SchellingCell cell) {
        empty.add(cell);
    }

    public String getUpdatedCellStatus(SchellingCell cell, List<Cell> neighbors) {
        int sameCell = countNeighbors(neighbors, cell.getStatus());
        int totalNeighbors = neighbors.size() - countNeighbors(neighbors, getDeadString());
        return toggleCell(cell, ((double) sameCell) / totalNeighbors);
    }

    private String toggleCell(SchellingCell cell, double ratio) {
        if (cell.getStatus().equals(getDeadString()) || ratio >= threshold) {
            return cell.getStatus();
        }
        move.add(cell.getStatus());
        addEmptyCell(cell);
        return getDeadString();
    }

    public void moveCells() {
        if (empty.size() < move.size()) {
            return;
        }
        SchellingCell hold;
        for (String s : move) {
            hold = empty.get(0);
            hold.setStatus(s);
            empty.remove(hold);
        }
        move.removeAll(move);
    }

    @Override
    public String toString() {
        return "Schelling's model of segregation.";
    }
}
