package cellsociety.simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import cellsociety.Cells.WatorCell;

/*
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
                fishCells.add(hold);
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

    private boolean reproducing(boolean isFish, WatorCell cell) {
        return (isFish && cell.reproduce(fishBreedingTime)) ||
                (!isFish && cell.reproduce(sharkBreedingTime));
    }

    private boolean moveToEmpty(boolean isFish, List<WatorCell> empty, List<WatorCell> fish) {
        return (isFish && empty.size() > 0) || (!isFish && fish.size() == 0 && empty.size() > 0);
    }

    private void swapCells(WatorCell a, WatorCell b) {
        int aX = a.getX(), aY = a.getY();
        a.move(b.getX(), b.getY());
        b.move(aX, aY);
    }

    public WatorCell randomize(HashMap<String, Double> parameters, int xCoordinate, int yCoordinate) {
        double empty = parameters.get("perEmpty");
        double shark = parameters.get("perShark");
        WatorCell cell = new WatorCell(xCoordinate, yCoordinate);
        if (RAND_NUM_GEN.nextDouble() < empty) {
            cell.setStatus(getDeadString());
        } else if (RAND_NUM_GEN.nextDouble() < shark) {
            cell.setStatus(sharkString);
            cell.setEnergy(sharkEnergy);
        } else {
            cell.setStatus(fishString);
        }
        return cell;
    }
}