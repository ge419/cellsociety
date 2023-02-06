package cellsociety.Cells;

public class LifeCell extends Cell{
  private static final String DEFAULT_STATUS = "dead";

  public LifeCell(int xCoordinate, int yCoordinate) {
    super(xCoordinate, yCoordinate);
    super.setStatus(DEFAULT_STATUS);
  }
}