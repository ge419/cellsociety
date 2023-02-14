package cellsociety.GUI.Grids;

import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import javafx.scene.layout.Pane;

public class GraphVisualGrid extends VisualGrid {


  public GraphVisualGrid(int x, int y){
    super(x, y);
  }
  @Override
  public void updateCell(int x, int y, String state) {

  }

  @Override
  public Pane getGridLayout() {
    return null;
  }
  @Override
  public void updateEntireGrid(Grid grid) {

  }

}
