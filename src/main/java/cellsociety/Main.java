package cellsociety;

import cellsociety.Controller.GameLoopManager;
import cellsociety.Engine.LifeEngine;
import cellsociety.Engine.SimulationEngine;
import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.GUI.VisualGrid;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

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
