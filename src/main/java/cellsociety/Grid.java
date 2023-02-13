package cellsociety;

import cellsociety.Cells.Cell;
import cellsociety.Cells.FireCell;
import cellsociety.Cells.LifeCell;
import cellsociety.Cells.SchellingCell;
import cellsociety.Cells.WatorCell;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Changmin Shin
 */
public class Grid{

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
  private static final String SEG_NAME = NAMES_FILE.getString("SegName");
  private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
  private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
  private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
  private static final String PERC_NAME = NAMES_FILE.getString("PercolName");

  private List<List<Cell>> grid;

  public Grid(int width, int height, String simType) {
    initializeGrid(width, height, simType);
  }

  public Cell getCell(int x, int y) {
    Cell c = grid.get(x).get(y);
    return c;
  }

  public void setCell(int x, int y, String status) {
    getCell(x, y).setX(x);
    getCell(x, y).setY(y);
    getCell(x, y).setStatus(status);
  }

  public int getColNum() {
    return grid.size();
    //return height;
  }

  public int getRowNum() {
    return grid.get(0).size();
    //return width;
  }

  public List<List<Cell>> getGrid() {
    return grid;
  }

  private void initializeGrid(int width, int height, String simType) {
    grid = new ArrayList<>();
    for (int r = 0; r < width; r++) {
      List<Cell> row = new ArrayList<>();
      for (int c = 0; c < height; c++) {
        row.add(c, cellType(simType, r, c));
      }
      grid.add(r, row);
    }
  }

  private Cell cellType(String simType, int r, int c) {
    if (simType.equals(LIFE_NAME)) {
      return new LifeCell(r, c);
    } else if (simType.equals(FIRE_NAME)) {
      return new FireCell(r, c);
    } else if (simType.equals(SEG_NAME)) {
      return new SchellingCell(r, c);
    } else if (simType.equals(WATOR_NAME)) {
      return new WatorCell(r, c);
    }
    else {
      //TODO: throw exception saying wrong simulation name? --> duplicate exceptions?
      return null;
    }
  }
}

