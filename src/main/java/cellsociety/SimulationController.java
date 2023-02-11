package cellsociety;

import java.util.HashMap;

public interface SimulationController {

  public void randomizeStart(HashMap<String, Double> parameters, String simType);

  public void blankStart();
}
