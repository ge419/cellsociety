package cellsociety;

/*
 * @author Changmin Shin
 */

public abstract class Cell {
  private String status;
  private int xPos;
  private int yPos;
  private double size;

  public Cell(int xCoordinate, int yCoordinate, double cellSize) {
    xPos = xCoordinate;
    yPos = yCoordinate;
    size = cellSize;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String s) {
    status = s;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }
}
