package cellsociety.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIContainer {
  Stage mainStage;
  public GUIContainer(Stage primaryStage) {
    mainStage = primaryStage;

    VBox vertical = new VBox();
    Button step = createButton("Step");
    Button reset = createButton("Reset");
    Button go = createButton("Go/Pause");
    Button clear = createButton("Clear");
    Button random = createButton("Random");
    vertical.getChildren().addAll(step, reset, go, clear, random);

    Scene stageScene = new Scene(vertical, 1000, 700);
    mainStage.setScene(stageScene);
    mainStage.show();
  }

  public Button createButton(String word){
    Button newButton = new Button(word);
    newButton.setId("SettingButtons");
    return newButton;
  }
}
