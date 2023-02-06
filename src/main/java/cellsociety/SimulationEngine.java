package cellsociety;

import cellsociety.Cells.Cell;
import java.util.ResourceBundle;

import cellsociety.simulations.Fire;
import cellsociety.simulations.Life;
import cellsociety.simulations.Schelling;
import cellsociety.simulations.Simulation;

/*
 * @author Brandon Weiss
 */
public class SimulationEngine {
    public static final String INTERNAL_CONFIGURATION = "cellsociety.english";
    public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
    private static final String SEG_NAME = NAMES_FILE.getString("SegName");
    private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
    private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");


    //TODO: create List<List<Cell>>, pass grid constructor

    //Potential Bug grid object here is not same grid object
    private Simulation sim;

    //TODO: pass in grid width and height
    public SimulationEngine(String simType, double[] params) {
        init(simType, params);
    }

    //TODO: params is not a double[]

    public void init(String simType, double[] params) {
        if (simType.equals(LIFE_NAME)) {
            sim = new Life("dead", "alive");
        } else if (simType.equals(FIRE_NAME)) {
            sim = new Fire("empty", "tree", "burning", params[0]);
        } else if (simType.equals(SEG_NAME)) {
            sim = new Schelling("empty", params[0]);
        }
    }

    public List<Cell> findNeighbors(){

    };


}
