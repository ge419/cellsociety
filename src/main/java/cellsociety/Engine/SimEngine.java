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

  private Simulation sim;
  private String simType;
  private VisualGrid visualGrid;
  private int width;
  private int height;
  private String initState;
  private boolean corners;
  private Grid grid;

  public SimEngine(Simulation sim, String simType, VisualGrid visualGrid, int width, int height,
      String initState, boolean corners, Grid grid) {
    this.sim = sim;
    this.simType = simType;
    this.visualGrid = visualGrid;
    this.width = width;
    this.height = height;
    this.initState = initState;
    this.corners = corners;
    this.grid = grid;
  }
}
