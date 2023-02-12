package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import java.util.List;
import java.util.ResourceBundle;

public abstract class SimEngine {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandstates";
  public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);

  private String simType;
  private VisualGrid visualGrid;
  private int width;
  private int height;
  private String initState;
  private Grid grid;

  public SimEngine(String simType, VisualGrid visualGrid, String initState, Grid grid) {
    this.simType = simType;
    this.visualGrid = visualGrid;
    this.width = visualGrid.getWidth();
    this.height = visualGrid.getHeight();
    this.initState = initState;
    this.grid = grid;
  }

  abstract String statusIntoStr(int status) throws Exception;

}
