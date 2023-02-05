package cellsociety.GUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @Author Han Zhang
 */

public class PopUp {
  private static Stage popUpStage;
  private final static Set<TextField> TextFields = new HashSet<>();

  private final static List<String> UserInput= new ArrayList<>();

  private static String CSS_FILE = "stylesheets/PopUp.css";
  public PopUp(List<String> Issues){
    popUpStage = new Stage();
    VBox comp = new VBox();
    comp.setId("Vbox");

    TextField issueField;
    Text issueText;

    Button clear = new Button("Clear");
    Button submit = new Button("Submit");

    for(String issue:Issues){
      HBox inside = new HBox();
      inside.setId("Hbox");
      issueField = new TextField();
      issueText = new Text(issue);
      TextFields.add(issueField);
      inside.getChildren().addAll(issueText, issueField);
      comp.getChildren().add(inside);
    }
    //TODO Refactor into Lambda Expressions
    clear.setOnAction(event -> {
      for(TextField field: TextFields){
        field.clear();
      }
    });
    //TODO Refactor into Lambda Expressions
    submit.setOnAction(event -> {
      for(TextField field: TextFields){
        UserInput.add(field.getText());
      }
      returnInput();
    });

    comp.getChildren().add(new HBox(clear, submit));
    Scene stageScene = new Scene(comp, 300, 300);
    stageScene.getStylesheets().add(CSS_FILE);
    popUpStage.setScene(stageScene);
  }
  public void showPopUp(){
    popUpStage.show();
  }

  //TODO, properly return after discussion with Changmin, return edit tags if we have time later
  public static List<String> returnInput(){
    System.out.println(UserInput);
    return UserInput;
  }
}
