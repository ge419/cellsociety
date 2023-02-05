package cellsociety.GUI;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  public void start (Stage primaryStage) {
    GUIContainer container = new GUIContainer(primaryStage);
  }
}
