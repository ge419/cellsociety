package cellsociety.GUI;

import cellsociety.Engine.EngineInterface;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class ButtonContainer {

  private final HBox container;

  public ButtonContainer(){
    container = new HBox();
    container.setId("Container-HBox");
  }

  public Button createButton(String word){
    Button newButton = new Button(word);
    container.getChildren().add(newButton);
    newButton.setId("Button");
    return newButton;
  }
  public Button createButton(String word, Pane container, EngineInterface engine, TextField textField) {
    Button newButton = new Button(word);
    container.getChildren().add(newButton);
    newButton.setOnAction(e -> engine.setParamValue(word,Double.parseDouble(textField.getText())));
    newButton.setId("Param-Button");
    return newButton;
  }
  public HBox getContainer() {
    return container;
  }
}
