package cellsociety.GUI.Grids;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleVisualGrid extends VisualGrid {

  private final GridPane gridLayout;

  public static final int GRID_SIZE = 300;
  private int width;
  private int height;

  public RectangleVisualGrid(int columns, int rows) {
    super(columns, rows);
    width = columns;
    height = rows;
    gridLayout = new GridPane();
    resetGrid(GRID_SIZE);
  }

  public void changeSize(int newWidth, int newHeight, int gridSize){
    gridLayout.getChildren().clear();
    width = newWidth;
    height = newHeight;
    resetGrid(gridSize);
  }

  @Override
  public void updateEntireGrid(Grid grid) {
    for(List<Cell> cells: grid.getGrid()){
      for(Cell cell: cells){
        updateCell(cell.getX(), cell.getY(), cell.getStatus());
      }
    }
  }

  @Override
  public void updateCell(int x, int y, String state) {
    Rectangle rect = (Rectangle) gridLayout.getChildren().get(x * width + y + 1);
    rect.setId(state);
  }
  public void resetGrid(double gridSize){
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Rectangle cell = new Rectangle(gridSize / width, gridSize / height);
        cell.setFill(Color.WHITE);
        gridLayout.add(cell, i, j);
      }
    }
  }
  public GridPane getGridLayout() {
    return gridLayout;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

}
