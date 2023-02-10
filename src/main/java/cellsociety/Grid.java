package cellsociety;

import cellsociety.Cells.Cell;
import java.util.ArrayList;
import java.util.List;

// extend ArrayList?
public class Grid extends ArrayList{

  private List<List<Cell>> grid;

  public Grid() {
    grid = new ArrayList<>();
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
  }

  public List<List<Cell>> getGrid() {
    return grid;
  }

  public int getRowNum() {
    return grid.get(0).size();
  }
}

