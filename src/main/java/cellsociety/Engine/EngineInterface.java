package cellsociety.Engine;

import cellsociety.Grid;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Han Zhang, Changmin Shin
 */
public interface EngineInterface {

  /**
   * Randomizes all cells inside of an Engine's grid
   */
  public void randomizeStart();

  /**
   * Sets all cells to a default or dead state inside the Engine
   */
  public void blankStart();

  /**
   * Resets the grid to the initial Configuration of the simulation
   */
  public void reset();

  /**
   * updates the backend grid to the next iteration/state it should be in,
   */
  void updateGameState();

  /**
   * Is used to create button for all parameters
   * @return Set<String> that is all the Parameters in String form
   */
  Set<String> getParamWords();

  /**
   * Returns Grid that the engine is modifying
   * @return Grid that containes current state
   */
  Grid getGrid();

  /**
   * Converts the grid to a String format for convince
   * @param grid, current grid that contains state of game
   * @return A String that represents the grid in a String format
   */
  String gridToStr(Grid grid);

  /**
   * Returns all the parametrs and their values, used to allow access from frontend of params and to edit them
   * @return map of parametrs and their values
   */
  Map<String, Double> getParams();

  /**
   * Allows modification of param values from View.
   * @param param String representation of param, same as xml file
   * @param newValue Double value of corresponding param
   */
  void setParamValue(String param, Double newValue);
}