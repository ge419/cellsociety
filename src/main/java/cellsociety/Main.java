package cellsociety;

import cellsociety.Controller.GameLoopManager;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main{

  public static void main(String[] args) throws Exception {
    String language = "english";
    Stage primaryStage = new Stage();
    GameLoopManager manager = new GameLoopManager(primaryStage, language);
  }
}
