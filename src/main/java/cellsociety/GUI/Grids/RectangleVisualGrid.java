package cellsociety.GUI.Grids;

import cellsociety.GUI.VisualGrid;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleVisualGrid extends VisualGrid {

  public static final String CELL_COLOR_PATH = "stylesheets/CellColor.css";
  private GridPane gridLayout;

  private int width;
  private int height;
  private Scene parentScene;
  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  private static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);

  public RectangleVisualGrid(int columns, int rows, double gridSize, Scene baseScene) {
    super(columns, rows);
    parentScene = baseScene;
    parentScene.getStylesheets().add(CELL_COLOR_PATH);
    width = columns;
    height = rows;
    gridLayout = new GridPane();
    resetGrid(gridSize);
  }

  public void changeSize(int newWidth, int newHeight, int gridSize){
    width = newWidth;
    height = newHeight;
    resetGrid(gridSize);
  }
  @Override
  public void updateGrid(int x, int y, String state) {
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
