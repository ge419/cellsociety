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
  private static final String LIFE_ALIVE = NAMES_FILE.getString("LifeAlive");

  private static final String LIFE_DEAD = NAMES_FILE.getString("LifeDead");

  private static final String FIRE_EMPTY = NAMES_FILE.getString("FireEmpty");

  private static final String FIRE_TREE = NAMES_FILE.getString("FireTree");

  private static final String FIRE_BURNING = NAMES_FILE.getString("FireBurning");

  private static final String SEG_EMPTY = NAMES_FILE.getString("SegEmpty");

  private static final String SEG_A = NAMES_FILE.getString("SegA");

  private static final String SEG_B = NAMES_FILE.getString("SegB");

  private static final String WATOR_EMPTY = NAMES_FILE.getString("WTEmpty");

  private static final String WATOR_SHARK = NAMES_FILE.getString("WTShark");

  private static final String WATOR_FISH = NAMES_FILE.getString("WTFish");

  private static final String PERC_BLOCK = NAMES_FILE.getString("PercolBlock");

  private static final String PERC_OPEN = NAMES_FILE.getString("PercolOpen");

  private static final String PERC_PERC = NAMES_FILE.getString("PercolPerc");

  public RectangleVisualGrid(int columns, int rows, double gridSize, Scene baseScene) {
    super(columns, rows);
    parentScene = baseScene;
    parentScene.getStylesheets().add(CELL_COLOR_PATH);
    width = columns;
    height = rows;
    gridLayout = new GridPane();
    gridLayout.setGridLinesVisible(true);
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
    rect.setId("Alive");
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
