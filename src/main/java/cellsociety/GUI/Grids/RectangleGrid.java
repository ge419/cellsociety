package cellsociety.GUI.Grids;

import cellsociety.Cells.Cell;
import cellsociety.GUI.Grid;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleGrid extends Grid {

  GridPane gridLayout;

  private int cellSize;

  int gridSize;

  int width;
  int height;

  public RectangleGrid(int columns, int rows, double gridSize) {
    super(columns, rows);
    width = columns;
    height = rows;
    gridLayout = new GridPane();
    gridLayout.setGridLinesVisible(true);
    resetGrid(gridSize);
  }
//
//  public void cellUpdate(Cell cell) {
//    updateGrid(cell.getX(), cell.getY());
//  }
  public void changeSize(int newWidth, int newHeight, int gridSize){
    width = newWidth;
    height = newHeight;
    resetGrid(gridSize);
  }
  @Override
  public void updateGrid(int x, int y, String state) {
    Rectangle rect = (Rectangle) gridLayout.getChildren().get(x * width + y + 1);
    rect.setFill(chooseColor(state));
  }

  public Color chooseColor(String state){
    //TODO change to resource bundle for different languages later or move to property file, very ugly
    switch(state){
      case "Alive":
        return Color.LIGHTGREEN;
      case "Dead":
        return Color.WHITE;
      case "Empty":
        return Color.YELLOW;
      case "Tree":
        return Color.DARKGREEN;
      case "Burning":
        return Color.RED;
      case "Unoccupied":
        return Color.WHITE;
      case "StateA":
        return Color.RED;
      case "StateB":
        return Color.BLUE;
      case "Sea":
        return Color.DARKBLUE;
      case "Shark":
        return Color.SKYBLUE;
      case "Fish":
        return Color.GREENYELLOW;
      case "Block":
        return Color.BLACK;
      case "Open":
        return Color.WHITE;
      case "Perc":
        return Color.LIGHTBLUE;
    }
    return Color.PURPLE;
  }
  public void resetGrid(double gridSize){
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Rectangle cell = new Rectangle(gridSize / width, gridSize / height);
        cell.setStroke(Color.BLACK);
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
