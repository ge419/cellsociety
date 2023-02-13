package cellsociety.Engine;

/**
 * @Author Han Zhang
 */
public interface EngineInterface {
  public void randomizeStart() throws Exception;

  public void blankStart();

  public void reset();
  void updateGameState();
}