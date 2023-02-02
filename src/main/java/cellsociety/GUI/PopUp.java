package cellsociety.GUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUp {

  private static Stage popUpStage;
  private static Set<TextField> TextFields = new HashSet<>();

  private static List<String> UserInput= new ArrayList<>();
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
      TextFields.add(issueField);
      inside.getChildren().addAll(issueText, issueField);
      comp.getChildren().add(inside);
    }
    clear.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        for(TextField field: TextFields){
          field.clear();
        }
      }
    });

    submit.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        for(TextField field: TextFields){
          UserInput.add(field.getText());
        }
        returnInput();
      }
    });

    comp.getChildren().add(new HBox(clear, submit));
    Scene stageScene = new Scene(comp, 300, 300);
    popUpStage.setScene(stageScene);
  }
  public void showPopUp(){
    popUpStage.show();
  }

  //TODO, properly return after discussion with Changmin
  public static List<String> returnInput(){
    System.out.println(UserInput);
    return UserInput;
  }
}
