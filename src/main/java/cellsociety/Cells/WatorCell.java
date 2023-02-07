package cellsociety.Cells;

/**
 * @author Brandon Weiss
 */
public class WatorCell extends Cell {
    private int breedingStage;
    private int energy;

    /**
     * @param xCoordinate The column number of the cell
     * @param yCoordinate The row number of the cell
     */
    public WatorCell(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        resetLifeCycle();
    }

    /**
     * Reset instance variables to 0
     */
    public void resetLifeCycle() {
        breedingStage = 0;
        energy = 0;
    }

    /**
     * @param breedingTime The minimum number of time steps needed for a fish or
     *                     shark to reproduce
     * @return Whether a fish or shark can reproduce at this time step
     */
    public boolean reproduce(int breedingTime) {
        if (breedingStage >= breedingTime) {
            return true;
        }
        return false;
    }

    /**
     * @param xCoordinate The new column number for a cell to move to
     * @param yCoordinate The new row number for a cell to move to
     */
    public void move(int xCoordinate, int yCoordinate) {
        energy--;
        setX(xCoordinate);
        setY(yCoordinate);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void age() {
        breedingStage++;
    }
}
