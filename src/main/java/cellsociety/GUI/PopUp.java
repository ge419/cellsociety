package cellsociety.GUI;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUp {

  private static Stage popUpStage;
  public PopUp(List<String> Issues){
    popUpStage = new Stage();
    VBox comp = new VBox();

    TextField issueField;
    Text issueText;

    Button clear = new Button("Clear");
    Button submit = new Button("Submit");

    for(String issue:Issues){
      HBox inside = new HBox();
      issueField = new TextField();
      issueText = new Text(issue);
      inside.getChildren().addAll(issueText, issueField);
      comp.getChildren().add(inside);
    }

    comp.getChildren().add(new HBox(clear, submit));
    Scene stageScene = new Scene(comp, 300, 300);
    popUpStage.setScene(stageScene);
  }
  public void showPopUp(){
    popUpStage.show();
  }
}
