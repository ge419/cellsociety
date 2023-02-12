package cellsociety;

import cellsociety.Cells.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Changmin Shin
 */
public class Grid{

  private List<List<Cell>> grid;

  public Grid(int width, int height) {
    initializeGrid(width, height);
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

  private void initializeGrid(int width, int height) {
    grid = new ArrayList<>();
    for (int r = 0; r < width; r++) {
      List<Cell> row = new ArrayList<>();
      for (int c = 0; c < height; c++) {
        row.add(c, new Cell(r, c));
      }
      grid.add(r, row);
    }
  }
}

