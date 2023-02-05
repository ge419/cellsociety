package cellsociety.simulations;

import java.util.ArrayList;
import java.util.List;
import cellsociety.Cells.Cell;

public class Schelling extends Simulation {
    private String stateA;
    private String stateB;
    private double threshold;
    private List<Cell> empty;
    private List<String> move;

    public Schelling(String emptyString, String stateAString, String stateBString, double threshold) {
        super(emptyString, "");
        stateA = stateAString;
        stateB = stateBString;
        setThreshold(threshold);
        empty = new ArrayList<>();
        move = new ArrayList<>();
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void addEmptyCell(Cell cell) {
        empty.add(cell);
    }

    public String getUpdatedCellStatus(Cell cell, List<Cell> neighbors) {
        int sameCell = countNeighbors(neighbors, cell.getStatus());
        int totalNeighbors = neighbors.size() - countNeighbors(neighbors, getDeadString());
        return toggleCell(cell, ((double) sameCell) / totalNeighbors);
    }

    public String toggleCell(Cell cell, double ratio) {
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
        Cell hold;
        for (String s : move) {
            hold = empty.get(0);
            hold.setStatus(s);
            empty.remove(hold);
        }
        move.removeAll(move);
    }
}
