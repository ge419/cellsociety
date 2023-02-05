package cellsociety.Cells;

/*
 * @author Changmin Shin, Brandon Weiss
 */

public abstract class Cell {
  private String status;
  private int xPos;
  private int yPos;

  public Cell(int xCoordinate, int yCoordinate) {
    setX(xCoordinate);
    setY(yCoordinate);
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

  public void setX(int x) {
    xPos = x;
  }

  public void setY(int y) {
    yPos = y;
  }
}
