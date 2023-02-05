package cellsociety.GUI.Grids;

import cellsociety.Cell;
import cellsociety.GUI.Grid;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleGrid extends Grid {

  GridPane gridLayout;

  public final int GRID_SIZE = 400;

  private int cellSize;

  int width;
  int height;

  public RectangleGrid(int Width, int Height){
    super(Width, Height);
    width = Width;
    height = Height;
    gridLayout = new GridPane();
    gridLayout.setGridLinesVisible(true);

    cellSize = Math.min(GRID_SIZE/width , GRID_SIZE/height);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Rectangle cell = new Rectangle(cellSize, cellSize);
        cell.setStroke(Color.BLACK);
        cell.setFill(Color.WHITE);
        gridLayout.add(cell, i, j);
      }
    }
  }
  @Override
  public void updateGrid(Cell cell) {

  }

  public GridPane getGridLayout() {
    return gridLayout;
  }
}
