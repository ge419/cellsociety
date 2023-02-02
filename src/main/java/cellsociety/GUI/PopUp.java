package cellsociety.GUI;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUp {

  Stage popUpStage;
  public PopUp(List<String> Issues){
    popUpStage = new Stage();
    VBox comp = new VBox();

    for(String issue:Issues){
      comp.getChildren().add(new TextField(issue));
    }
    Scene stageScene = new Scene(comp, 300, 300);
    popUpStage.setScene(stageScene);
  }
}
