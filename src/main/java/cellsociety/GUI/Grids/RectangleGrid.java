package cellsociety.GUI.Grids;

import cellsociety.Cells.Cell;
import cellsociety.GUI.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleGrid extends Grid {

  GridPane gridLayout;

  private int cellSize;

  int gridSize;

  int width;
  int height;

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandvariables";
  private static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);;
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

      if(state.equals(LIFE_ALIVE)) {return Color.LIGHTGREEN;}
      if(state.equals(LIFE_DEAD)) {return Color.WHITE;}
      if(state.equals(FIRE_EMPTY)) {return Color.YELLOW;}
      if(state.equals(FIRE_TREE)) {return Color.DARKGREEN;}
      if(state.equals(FIRE_BURNING)) {return Color.RED;}
      if(state.equals(SEG_EMPTY)) {return Color.WHITE;}
      if(state.equals(SEG_A)) {return Color.RED;}
      if(state.equals(SEG_B)) {return Color.BLUE;}
      if(state.equals(WATOR_EMPTY)) {return Color.DARKBLUE;}
      if(state.equals(WATOR_FISH)) {return Color.GREENYELLOW;}
      if(state.equals(WATOR_SHARK)) {return Color.SKYBLUE;}
      if(state.equals(PERC_OPEN)) {return Color.WHITE;}
      if(state.equals(PERC_BLOCK)) {return Color.BLACK;}
      if(state.equals(PERC_PERC)) {return Color.LIGHTBLUE;}

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
