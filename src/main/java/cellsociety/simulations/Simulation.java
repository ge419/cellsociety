package cellsociety.simulations;

import static cellsociety.SimulationEngine.cells;

import java.util.HashMap;
import java.util.List;
import cellsociety.Cells.Cell;
import java.util.ResourceBundle;

/*
 * @author Brandon Weiss
 */
public abstract class Simulation {
    public static final String INTERNAL_CONFIGURATION = "cellsociety.filesandvariables";
    public static final ResourceBundle NAMES_FILE = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
    private static final String SEG_NAME = NAMES_FILE.getString("SegName");
    private static final String FIRE_NAME = NAMES_FILE.getString("FireName");
    private static final String LIFE_NAME = NAMES_FILE.getString("LifeName");
    private static final String WATOR_NAME = NAMES_FILE.getString("WTName");
    private static final String PERC_NAME = NAMES_FILE.getString("PercolName");

    private String deadState;
    private String aliveState;

    public Simulation(String deadString, String aliveString) {
        deadState = deadString;
        aliveState = aliveString;
    }

    public int countNeighbors(List<Cell> neighbors, String state) {
        int count = 0;
        for (Cell cell : neighbors) {
            if (cell.getStatus().equals(state)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets cell status based on the % of certain cell from HashMap params
     * TODO: finish code for determining state, decide which class this method will go to
     * @param params
     * @param simType
     * @param x
     * @param y
     */
    public void randomize(HashMap<String, Double> params, String simType, int x, int y) {
        if (simType.equals(LIFE_NAME)) {
            double perAlive = params.get("perAlive");
            // code that determines state using perAlive
            // String life =
            //cells.get(x).get(y).setStatus(life);
        } else if (simType.equals(FIRE_NAME)) {
            double perTree = params.get("perTree");
            double perFire = params.get("perFire");
            // code that determines state by perTree, perFire
            // String fire =
            //cells.get(x).get(y).setStatus(fire);
        } else if (simType.equals(SEG_NAME)) {
            double perEmpty = params.get("perEmpty");
            double perStateOne = params.get("perStateOne");
            // code that determines state using perEmpty, perStateOne
            // String seg =
            //cells.get(x).get(y).setStatus(seg);
        } else if (simType.equals(WATOR_NAME)) {
            double perEmptyWA = params.get("perEmpty");
            double perShark = params.get("perShark");
            // code that determines state using perShark, perEmptyWA
            // String wator =
            //cells.get(x).get(y).setStatus(wator);
        } else if (simType.equals(PERC_NAME)) {
            double perBlocked = params.get("perBlocked");
            // code that determines state using perBlocked
            // String perc =
            //cells.get(x).get(y).setStatus(perc);
        }

    }

    public String getAliveString(){
        return aliveState;
    }

    public String getDeadString(){
        return deadState;
    }

    public String toString() {
        return "Game of " + deadState + " and " + aliveState + " cells.";
    }
}
