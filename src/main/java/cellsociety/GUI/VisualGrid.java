package cellsociety.GUI;

/**
 * @Author Han Zhang
 */


public abstract class VisualGrid {

  int width;
  int height;

  public VisualGrid(int Width, int Height) {
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
