package cellsociety;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import cellsociety.Cells.Cell;
import cellsociety.Cells.WatorCell;
import cellsociety.GUI.Grid;
import cellsociety.simulations.Fire;
import cellsociety.simulations.Life;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;
import cellsociety.simulations.WaTor;

/*
 * @author Brandon Weiss
 */
public class SimulationEngine {
    public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandvariables";
    public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
    private static final String SEG_NAME = NAMES_FILE.getString("SegName");
    private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
    private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
    private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
    private static final String PERC_NAME = NAMES_FILE.getString("PercolName");

    // Potential Bug grid object here is not same grid object
    private Simulation sim;
    private String simType;
    private Grid grid;
    private int width;
    private int height;
    private List<List<Cell>> cells;

    public SimulationEngine(String simType, HashMap<String, Double> params, Grid grid) {
        init(simType, params);
        this.simType = simType;
        this.grid = grid;
        this.width = grid.getWidth();
        this.height = grid.getHeight();
        cells = new ArrayList<>();
    }

    // TODO: replace string literals with strings from filesandstates.properties
    public void init(String simType, HashMap<String, Double> params) {
        if (simType.equals(LIFE_NAME)) {
            sim = new Life("dead", "alive");
        } else if (simType.equals(FIRE_NAME)) {
            sim = new Fire("empty", "tree", "burning", params.get());
        } else if (simType.equals(SEG_NAME)) {
            sim = new Schelling("empty", "a", "b", params.get());
        } else if (simType.equals(WATOR_NAME)) {
            sim = new WaTor("sea", "fish", "shark", params.get(),
                    params.get(), params.get(), params.get());
        }
    }

    //TODO
    //for each cell, call sim.randomize(parameters, xcoord of cell, ycoord of cell)
    //I dont know what to return
    public void randomizeStart(HashMap<String, Double> parameters){
        
    }

    
    public void evolve(){
        if (simType.equals(WATOR_NAME)){
            for (){
                sim.moveCell(c, findNeighbors(c, false, true));
            }
            for (){
                sim.moveCell(c, findNeighbors(c, false, true));
            }
        }
        else{
            for each cell {
                new cell state = sim.getUpdatedCellStatus();
                store new cell state
            }
            for each cell {
                cell.setStatus();
                grid.update();
            }
        }
    }
    
    //TODO
    public List<Cell> findNeighbors(Cell cell, boolean corners, boolean wrap) {

    };

    // In Wator, move each fish and then each shark
}
