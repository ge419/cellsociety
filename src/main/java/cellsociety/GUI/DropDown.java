package cellsociety.GUI;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class DropDown {

  private static ComboBox<String> dropdown;

  private static String selected;

  private static Button button;

  private static VBox container;
  public DropDown(List<String> list){

    container = new VBox();
    container.setId("Container-Vbox");
    dropdown = new ComboBox<>();

    container.getChildren().add(dropdown);

    button = new Button("Get Selection");
    button.setId("drop-button");

    container.getChildren().add(button);
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        selected = dropdown.getValue();
      }
    });
  }
  public String getValue(){
    return selected;
  }
  public VBox getContainer(){
    return container;
  }
}
