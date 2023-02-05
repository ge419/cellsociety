package cellsociety;

public class GameOfLifeCell extends Cell{
  private static final String DEFAULT_STATUS = "dead";

  public GameOfLifeCell(int xCoordinate, int yCoordinate) {
    super(xCoordinate, yCoordinate);
    super.setStatus(DEFAULT_STATUS);
  }
}