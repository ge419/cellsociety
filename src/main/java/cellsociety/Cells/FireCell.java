package cellsociety.Cells;

public class FireCell extends Cell{
  private static final String DEFAULT_STATUS = "dead";

  public FireCell(String cellStatus, int xCoordinate, int yCoordinate) {
    super(xCoordinate, yCoordinate);
    super.setStatus(DEFAULT_STATUS);
  }
}
