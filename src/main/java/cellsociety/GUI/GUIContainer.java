package cellsociety.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIContainer {
  Stage mainStage;
  GridPane pane;
  public GUIContainer(Stage primaryStage) {
    mainStage = primaryStage;
    pane = new GridPane();

    pane.setGridLinesVisible(true);
    Button step = createButton("Step", 0, 4);
    Button reset = createButton("Reset", 1, 4);
    Button go = createButton("Go/Pause",2, 4);
    Button clear = createButton("Clear", 3, 4);
    Button random = createButton("Random", 4, 4);

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().addAll(step, reset, go, clear, random);

    Scene stageScene = new Scene(pane, 1000, 700);
    mainStage.setScene(stageScene);
    mainStage.show();
  }

  public Button createButton(String word, int x, int y){
    Button newButton = new Button(word);
    newButton.setId("SettingButtons");
    pane.setConstraints(newButton, x, y);
    return newButton;
  }
}
