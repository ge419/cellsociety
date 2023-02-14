package cellsociety.Engine;

import java.util.List;
import java.util.Set;

/**
 * @Author Han Zhang
 */
public interface EngineInterface {
  public void randomizeStart();

  public void blankStart();

  public void reset();
  void updateGameState();

  Set<String> getParamWords();
}