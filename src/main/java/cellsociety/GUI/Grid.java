package cellsociety.GUI;

/**
 * @Author Han Zhang
 */


public abstract class Grid {

  int width;
  int height;

  public Grid(int Width, int Height) {
    width = Width;
    height = Height;
  }
  public abstract void updateGrid(int x, int y, String state);

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}
