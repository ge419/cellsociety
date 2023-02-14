package cellsociety.Engine;

import cellsociety.Cells.Cell;
import cellsociety.Cells.WatorCell;
import cellsociety.GUI.VisualGrid;
import cellsociety.Grid;
import cellsociety.simulations.Simulation;
import cellsociety.simulations.WaTor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Changmin Shin, Brandon Weiss
 */

public class WatorEngine extends SimEngine {

  private static final String WATOR_EMPTY = NAMES_FILE.getString("WTEmpty");
  private static final String WATOR_SHARK = NAMES_FILE.getString("WTShark");
  private static final String WATOR_FISH = NAMES_FILE.getString("WTFish");

  public WatorEngine(VisualGrid visualGrid, String initState, Grid grid, Grid initGrid,
      Map<String, Double> params)
      throws Exception {
    super(visualGrid, initState, grid, initGrid, params);
    // setup initial state grid for both updating grid and initial grid
    listToGrid(strToGrid(initState), grid);
    listToGrid(strToGrid(initState), initGrid);
    // initialize simulation
    init(params);
  }

  @Override
  String statusIntToStr(int status) throws Exception {
    if (status == 0) {
      return WATOR_EMPTY;
    } else if (status == 1) {
      return WATOR_FISH;
    } else if (status == 2) {
      return WATOR_SHARK;
    } else {
      // TODO: create a new exception class, also return e
      throw new Exception("Invalid input for status");
    }
  }

  @Override
  void init(Map<String, Double> params) {
    double sharkEnergy = 5;
    double energyPerFish = 3;
    double fishBreedingTime = 4;
    double sharkBreedingTime = 4;
    try {
      sharkEnergy = params.get("eShark");
      energyPerFish = params.get("ePerFish");
      fishBreedingTime = params.get("fishBT");
      sharkBreedingTime = params.get("sharkBT");
    } catch (Exception e) {
    }
    sim = new WaTor(WATOR_EMPTY, WATOR_FISH, WATOR_SHARK, fishBreedingTime, sharkBreedingTime,
        sharkEnergy, energyPerFish);
  }

  @Override
  public void updateGameState() {
    for (WatorCell fish : ((WaTor) sim).getFishCells()) {
      ((WaTor) sim).moveCell(fish, findNeighbors(fish));
    }
    for (WatorCell shark : ((WaTor) sim).getSharkCells()) {
      ((WaTor) sim).moveCell(shark, findNeighbors(shark));
    }
  }

  @Override
  public List<Cell> findNeighbors(Cell cell) {
    List<Cell> neighbors = new ArrayList<>();
    if (cell.getX() == 0) {
      neighbors.add(getCell(getGrid().getRowNum() - 1, cell.getY()));
    }
    if (cell.getY() == 0) {
      neighbors.add(getCell(cell.getX(), getGrid().getColNum() - 1));
    }
    if (cell.getX() == getGrid().getRowNum() - 1) {
      neighbors.add(getCell(0, cell.getY()));
    }
    if (cell.getX() == getGrid().getColNum() - 1) {
      neighbors.add(getCell(cell.getX(), 0));
    }
    if (cell.getX() != 0) {
      neighbors.add(getCell(cell.getX() - 1, cell.getY()));
    }
    if (cell.getX() != getGrid().getRowNum() - 1) {
      neighbors.add(getCell(cell.getX() + 1, cell.getY()));
    }
    if (cell.getY() != 0) {
      neighbors.add(getCell(cell.getX(), cell.getY() - 1));
    }
    if (cell.getY() != getGrid().getColNum() - 1) {
      neighbors.add(getCell(cell.getX(), cell.getY() + 1));
    }
    return neighbors;
  }

  @Override
  public void setParamValue(String param, Double newValue) {
    super.setParamValue(param, newValue);
    init(params);
  }
}
