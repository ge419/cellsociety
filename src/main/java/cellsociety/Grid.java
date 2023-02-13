package cellsociety;

import cellsociety.Cells.Cell;
import cellsociety.Cells.LifeCell;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Changmin Shin
 */
public class Grid{

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
    if (simType.equals("Game of Life")) {
      return new LifeCell(r, c);
    }
    else {
      //TODO: throw exception that says invalid simulation name
      // --> duplicate exception?
      return null;
    }
  }
}

