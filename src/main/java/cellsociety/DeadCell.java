package cellsociety;

public class DeadCell extends Cell{
  private static final String DEAD_STATUS = "dead";

  public DeadCell(String cellStatus, int xCoordinate, int yCoordinate) {
    super(xCoordinate, yCoordinate);
    super.setStatus(DEAD_STATUS);
  }
}
