package cellsociety.Cells;

/*
 * @author Brandon Weiss
 */
public class WatorCell extends Cell{
    private int breedingStage;
    private int energy;
    public WatorCell(int xCoordinate, int yCoordinate){
        super(xCoordinate, yCoordinate);
        resetLifeCycle();
    }

    public void resetLifeCycle(){
        breedingStage = 0;
        energy = 0;
    }

    public boolean reproduce(int breedingTime){
        if (breedingStage >= breedingTime){
            return true;
        }
        return false;
    }

    public void move(int xCoordinate, int yCoordinate){
        energy--;
        setX(xCoordinate);
        setY(yCoordinate);
    }

    public int getEnergy(){
        return energy;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }

    public void age(){
        breedingStage++;
    }
}
