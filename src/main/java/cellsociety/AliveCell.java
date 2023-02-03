package cellsociety;

public class AliveCell extends Cell{
  private static final String ALIVE_STATUS = "alive";

  public AliveCell(int xCoordinate, int yCoordinate, double cellSize) {
    super(xCoordinate, yCoordinate, cellSize);
    super.setStatus(ALIVE_STATUS);
  }
}