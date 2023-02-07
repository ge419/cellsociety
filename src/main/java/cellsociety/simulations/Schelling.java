package cellsociety.simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.Cell;

/*
 * @author Brandon Weiss
 */
public class Schelling extends Simulation {
    public static final Random RAND_NUM_GEN = new Random();
    private double threshold;
    private String stateAString;
    private String stateBString;
    private List<Cell> empty;
    private List<String> move;

    public Schelling(String emptyString, String stateAString, String stateBString, double threshold) {
        super(emptyString, "");
        setThreshold(threshold);
        this.stateAString = stateAString;
        this.stateBString = stateBString;
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

    private String toggleCell(Cell cell, double ratio) {
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

    public Cell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
        double empty = parameters.get("perEmpty");
        double stateA = parameters.get("perStateOne");
        Cell cell = new Cell(xCoordinate, yCoordinate);
        if (RAND_NUM_GEN.nextDouble() < empty) {
            cell.setStatus(getDeadString());
        } else if (RAND_NUM_GEN.nextDouble() < stateA) {
            cell.setStatus(stateAString);
        } else {
            cell.setStatus(stateBString);
        }
        return cell;
    }

}
