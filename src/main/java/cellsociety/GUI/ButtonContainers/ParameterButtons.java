package cellsociety.GUI.ButtonContainers;

import cellsociety.Engine.EngineInterface;
import cellsociety.GUI.ButtonContainer;
import java.util.Set;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ParameterButtons extends ButtonContainer {

  HBox container;
  public ParameterButtons(EngineInterface engine){
    super();
    Set<String> Params = engine.getParamWords();
    for(String param: Params){
      VBox miniContainer = new VBox();
      miniContainer.setId("Mini-Container");
      createButton(param, miniContainer);
      createTextField(miniContainer);
      container.getChildren().add(miniContainer);
    }
  }
  private void createTextField(VBox miniContainer){
    TextField newTextBox = new TextField();
    newTextBox.setId("Param-TextBox");
    miniContainer.getChildren().add(newTextBox);
  }
}
