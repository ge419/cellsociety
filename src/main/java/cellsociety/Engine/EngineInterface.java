package cellsociety.Engine;

import cellsociety.Grid;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Han Zhang, Changmin Shin
 */
public interface EngineInterface {
  public void randomizeStart();

  public void blankStart();

  public void reset();
  void updateGameState();

  Set<String> getParamWords();

  Grid getGrid();
  String gridToStr(Grid grid);

  Map<String, Double> getParams();

  void setParamValue(String param, Double newValue);
}