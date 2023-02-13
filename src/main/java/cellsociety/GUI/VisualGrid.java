package cellsociety.GUI;

import cellsociety.Grid;
import javafx.scene.layout.Pane;

/**
 * @author Han Zhang
 */

public abstract class VisualGrid {

  int width;
  int height;

  public VisualGrid(int Width, int Height) {
    width = Width;
    height = Height;
  }
  public abstract void updateCell(int x, int y, String state);

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public abstract Pane getGridLayout();

  public abstract void changeSize(int i, int i1, int gridSize);

  public abstract void updateEntireGrid(Grid grid);
}
