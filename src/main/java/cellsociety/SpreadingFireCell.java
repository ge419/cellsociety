package cellsociety;

public class SpreadingFireCell extends Cell{
  private static final String DEFAULT_STATUS = "dead";

  public SpreadingFireCell(String cellStatus, int xCoordinate, int yCoordinate) {
    super(xCoordinate, yCoordinate);
    super.setStatus(DEFAULT_STATUS);
  }
}
