package cellsociety.simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cellsociety.Cells.Cell;
import cellsociety.Cells.WatorCell;

/**
 * @author Brandon Weiss
 */
public class WaTor extends Simulation {
    public static final Random RAND_NUM_GEN = new Random();
    private String fishString;
    private String sharkString;
    private int fishBreedingTime;
    private int sharkBreedingTime;
    private int sharkEnergy;
    private List<WatorCell> fishCells, sharkCells;
    private int energyPerFish;

    /**
     * @param emptyString       The string representing an empty cell
     * @param fishString        The string representing a fish cell
     * @param sharkString       The string representing a shark cell
     * @param fishBreedingTime  The minimum number of time steps needed for a fish
     *                          to reproduce
     * @param sharkBreedingTime The minimum number of time steps needed for a shark
     *                          to reproduce
     * @param sharkEnergy       The default number of energy units a shark starts
     *                          with
     * @param energyPerFish     The number of energy units a shark gains by eating a
     *                          fish
     */
    public WaTor(String emptyString, String fishString, String sharkString,
            double fishBreedingTime, double sharkBreedingTime, double sharkEnergy, double energyPerFish) {
        super(emptyString, "");
        this.fishString = fishString;
        this.sharkString = sharkString;
        setFishBreedingTime(fishBreedingTime);
        setSharkBreedingTime(sharkBreedingTime);
        setSharkEnergy(sharkEnergy);
        setEnergyPerFish(energyPerFish);
        fishCells = new ArrayList<>();
        sharkCells = new ArrayList<>();
    }

    public void setFishBreedingTime(double breedingTime) {
        fishBreedingTime = (int) breedingTime;
    }

    public void setSharkBreedingTime(double breedingTime) {
        sharkBreedingTime = (int) breedingTime;
    }

    public void setSharkEnergy(double energy) {
        sharkEnergy = (int) energy;
    }

    public void setEnergyPerFish(double energy) {
        energyPerFish = (int) energy;
    }

    public void addFishCell(WatorCell cell) {
        fishCells.add(cell);
    }

    public void addSharkCell(WatorCell cell) {
        sharkCells.add(cell);
    }

    public List<WatorCell> getFishCells() {
        return fishCells;
    }

    public List<WatorCell> getSharkCells() {
        return sharkCells;
    }

    public void moveCell(Cell cell, List<Cell> neighbors) {
        List<WatorCell> nb = new ArrayList<>();
        for (Cell c : neighbors) {
            nb.add((WatorCell)c);
        }
        moveCell((WatorCell)cell, nb);
    }

    /**
     * @param cell      A cell for which to calculate where to move
     * @param neighbors A list of neighbor cells that are next to cell
     */
    public void moveCell(WatorCell cell, List<WatorCell> neighbors) {
        ArrayList<WatorCell> emptyNeighbors = new ArrayList<>();
        ArrayList<WatorCell> fishNeighbors = new ArrayList<>();
        for (WatorCell n : neighbors) {
            if (n.getStatus().equals(getDeadString())) {
                emptyNeighbors.add(n);
            } else if (n.getStatus().equals(fishString)) {
                fishNeighbors.add(n);
            }
        }
        move(cell, emptyNeighbors, fishNeighbors);
    }

    /**
     * @param cell           A cell for which to calculate where to move
     * @param emptyNeighbors A list of neighbors in the empty state
     * @param fishNeighbors  A list of neighbors in the fish state
     */
    private void move(WatorCell cell, List<WatorCell> emptyNeighbors, List<WatorCell> fishNeighbors) {
        boolean isFish = cell.getStatus().equals(fishString);
        WatorCell hold;
        if (moveToEmpty(isFish, emptyNeighbors, fishNeighbors)) {
            hold = emptyNeighbors.get(RAND_NUM_GEN.nextInt(emptyNeighbors.size()));
            if (reproducing(isFish, cell)) {
                hold.setStatus(cell.getStatus());
                hold.resetLifeCycle();
                int eng = cell.getEnergy();
                cell.resetLifeCycle();
                hold.setEnergy(eng - 1);
                cell.setEnergy(sharkEnergy);
                if (isFish) {
                    fishCells.add(hold);
                } else {
                    sharkCells.add(hold);
                }
            } else {
                swapCells(cell, hold);
                cell.age();
            }
        } else if (!isFish && fishNeighbors.size() > 0) {
            hold = fishNeighbors.get(RAND_NUM_GEN.nextInt(fishNeighbors.size()));
            swapCells(cell, hold);
            cell.setEnergy(cell.getEnergy() + energyPerFish);
            cell.age();
            hold.setStatus(getDeadString());
        } else {
            cell.move(cell.getX(), cell.getY());
        }
    }

    /**
     * @param isFish boolean if a cell is a fish (true) or shark (false)
     * @param cell   cell of interest
     * @return whether or not the cell reproduces
     */
    private boolean reproducing(boolean isFish, WatorCell cell) {
        return (isFish && cell.reproduce(fishBreedingTime)) ||
                (!isFish && cell.reproduce(sharkBreedingTime));
    }

    /**
     * @param isFish boolean if a cell is a fish (true) or shark (false)
     * @param empty  A list of neighbor cells in the empty state
     * @param fish   A list of neighbor cells in the fish state
     * @return Whether a cell is moving into an empty space
     */
    private boolean moveToEmpty(boolean isFish, List<WatorCell> empty, List<WatorCell> fish) {
        return (isFish && empty.size() > 0) || (!isFish && fish.size() == 0 && empty.size() > 0);
    }

    /**
     * Swaps the positions of cells A and B
     */
    private void swapCells(WatorCell a, WatorCell b) {
        int aX = a.getX(), aY = a.getY();
        a.move(b.getX(), b.getY());
        b.move(aX, aY);
    }

    /**
     * @see cellsociety.simulations.Simulation#randomize(java.util.HashMap, int, int)
     *      parameters used: perEmpty - fraction of cells to initialize as empty
     *      perShark - fraction of non-empty cells to initialize as shark
     */
    public WatorCell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
        double empty = parameters.get("perEmpty");
        double shark = parameters.get("perShark");
        WatorCell cell = new WatorCell(xCoordinate, yCoordinate);
        if (RAND_NUM_GEN.nextDouble() < empty) {
            cell.setStatus(getDeadString());
        } else if (RAND_NUM_GEN.nextDouble() < shark) {
            cell.setStatus(sharkString);
            cell.setEnergy(sharkEnergy);
            sharkCells.add(cell);
        } else {
            cell.setStatus(fishString);
            fishCells.add(cell);
        }
        return cell;
    }
}
