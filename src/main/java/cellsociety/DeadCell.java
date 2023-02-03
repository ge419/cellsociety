package cellsociety;

public class DeadCell extends Cell{
  private static final String DEAD_STATUS = "dead";

  public DeadCell(String cellStatus, int xCoordinate, int yCoordinate, double cellSize) {
    super(xCoordinate, yCoordinate, cellSize);
    super.setStatus(DEAD_STATUS);
  }
}
