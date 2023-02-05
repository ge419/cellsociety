package cellsociety;

/*
 * @author Changmin Shin
 */

public abstract class Cell {
  private String status;
  private int xPos;
  private int yPos;
  private double size;

  public Cell(int xCoordinate, int yCoordinate) {
    xPos = xCoordinate;
    yPos = yCoordinate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String s) {
    status = s;
  }

  public void setSize(double cellSize) {
    size = cellSize;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }
  public void setX(int x) {
    xPos = x;
  }

  public void setY(int y) {
    yPos = y;
  }
}
