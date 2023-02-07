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
 * @author Brandon Weiss, Changmin Shin
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
            sim = new Fire("empty", "tree", "burning", params.get("probCatch"));
        } else if (simType.equals(SEG_NAME)) {
            sim = new Schelling("empty", "a", "b", params.get("change"));
        } else if (simType.equals(WATOR_NAME)) {
            sim = new WaTor("sea", "fish", "shark", params.get("eShark"),
                    params.get("ePerFish"), params.get("fishBT"), params.get("sharkBT"));
        } else if (simType.equals(PERC_NAME)) {
            //sim = new Percolation()
        }
    }

    //TODO
    //for each cell, call sim.randomize(parameters, xcoord of cell, ycoord of cell)
    //I dont know what to return
    // Loop through each cell, calls randomize in Simulation class --> which class should this belong to?
    public void randomizeStart(HashMap<String, Double> parameters, String simType){ // use viewParam in Config
        cells = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            ArrayList<Cell> column = new ArrayList<>();
            for (int j = 0; j < cells.get(i).size(); i++) {
                column.add(sim.randomize(parameters, i, j));
            }
            cells.add(column);
        }
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
        List<Cell> neighbors = new ArrayList<>();
        Cell currCell = cells.get(cell.getX()).get(cell.getY());
        if (corners) {
            // check width and height, see which corner it's in
        }
        else if(wrap) {

        }
        else {
            neighbors.add(cells.get(currCell.getX() - 1).get(currCell.getY()));
            neighbors.add(cells.get(currCell.getX()).get(currCell.getY() - 1));
            neighbors.add(cells.get(currCell.getX() + 1).get(currCell.getY()));
            neighbors.add(cells.get(currCell.getX()).get(currCell.getY() + 1));
            neighbors.add(cells.get(currCell.getX() - 1).get(currCell.getY() - 1));
            neighbors.add(cells.get(currCell.getX() - 1).get(currCell.getY() + 1));
            neighbors.add(cells.get(currCell.getX() + 1).get(currCell.getY() - 1));
            neighbors.add(cells.get(currCell.getX() + 1).get(currCell.getY() + 1));
        }
        return neighbors;
    };

    //TODO: public String getCellStatus(xCoord, yCoord)
}
