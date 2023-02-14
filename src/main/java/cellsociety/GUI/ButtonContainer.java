package cellsociety.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class ButtonContainer {

  private HBox container;

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
  public Button createButton(String word, Pane container) {
    Button newButton = new Button(word);
    container.getChildren().add(newButton);
    newButton.setId("Param-Button");
    return newButton;
  }
  public HBox getContainer() {
    return container;
  }
}
