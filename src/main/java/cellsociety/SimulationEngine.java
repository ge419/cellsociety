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

/**
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
    private boolean corners;

    /**
     * @param simType The string representing which of the cellular automata to run
     * @param params  A HashMap of parameters and values for each simulation type
     * @param grid    The grid object of the view
     */
    public SimulationEngine(String simType, HashMap<String, Double> params, Grid grid) {
        init(simType, params);
        this.simType = simType;
        this.grid = grid;
        this.width = grid.getWidth();
        this.height = grid.getHeight();
        blankStart(simType);
    }

    // TODO: replace string literals with strings from filesandstates.properties
    /**
     * @param simType The string representing which of the cellular automata to run
     * @param params  A HashMap of parameters and values for each simulation type
     */
    public void init(String simType, HashMap<String, Double> params) {
        if (simType.equals(LIFE_NAME)) {
            sim = new Life("dead", "alive");
            corners = true;
        } else if (simType.equals(FIRE_NAME)) {
            sim = new Fire("empty", "tree", "burning", params.get("probCatch"));
            corners = false;
        } else if (simType.equals(SEG_NAME)) {
            sim = new Schelling("empty", "a", "b", params.get("change"));
            corners = true;
        } else if (simType.equals(WATOR_NAME)) {
            sim = new WaTor("sea", "fish", "shark", params.get("eShark"),
                    params.get("ePerFish"), params.get("fishBT"), params.get("sharkBT"));
            corners = false;
        } else if (simType.equals(PERC_NAME)) {
            // sim = new Percolation()
            corners = true;
        }
    }

    /**
     * Randomize the starting configuration for a simulation
     * 
     * @param parameters A HashMap of parameters and values for each simulation type
     * @param simType    The string representing which of the cellular automata to
     *                   run
     */
    public void randomizeStart(HashMap<String, Double> parameters, String simType) {
        cells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<Cell> column = new ArrayList<>();
            for (int j = 0; j < height; i++) {
                column.add(sim.randomize(parameters, i, j));
            }
            cells.add(column);
        }
    }

    /**
     * Set the starting configuration for a blank simulation
     * 
     * @param simType The string representing which of the cellular automata to run
     */
    public void blankStart(String simType) {
        Cell input;
        cells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<Cell> column = new ArrayList<>();
            for (int j = 0; j < height; i++) {
                input = new Cell(i, j);
                input.setStatus(sim.getDeadString());
                column.add(input);
            }
            cells.add(column);
        }
    }

    public void updateGameState() {
        if (simType.equals(WATOR_NAME)) {
            for (WatorCell fish : ((WaTor) sim).getFishCells()) {
                ((WaTor) sim).moveCell(fish, findNeighbors(fish, corners));
            }
            for (WatorCell shark : ((WaTor) sim).getSharkCells()) {
                ((WaTor) sim).moveCell(shark, findNeighbors(shark, corners));
            }
        } else {
            ArrayList<String> nextStates = new ArrayList<>();
            List<Cell> column;
            Cell hold;
            for (int i = 0; i < cells.size(); i++) {
                column = cells.get(i);
                for (int j = 0; j < column.size(); j++) {
                    hold = column.get(j);
                    nextStates.add(sim.getUpdatedCellStatus(hold, findNeighbors(hold, corners)));
                    if (simType.equals(SEG_NAME)) {
                        ((Schelling) sim).moveCells();
                    }
                }
            }
            String next;
            for (int i = 0; i < cells.size(); i++) {
                for (int j = 0; j < cells.get(i).size(); j++) {
                    next = nextStates.get(i*cells.get(i).size() + j);
                    cells.get(i).get(j).setStatus(next);
                    grid.updateGrid(i, j, next);
                }
            }
        }
    }

    // TODO
    public List<Cell> findNeighbors(Cell cell, boolean corners) {
        List<Cell> neighbors = new ArrayList<>();
        Cell currCell = cells.get(cell.getX()).get(cell.getY());
        if (corners) {
            // check width and height, see which corner it's in
        } else if (simType.equals(WATOR_NAME)) {

        } else {
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
    }
}
