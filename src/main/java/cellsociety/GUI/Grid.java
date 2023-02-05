package cellsociety.GUI;

import cellsociety.Cells.Cell;

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
  public abstract void updateGrid(Cell cell);
}
