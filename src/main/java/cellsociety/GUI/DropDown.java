package cellsociety.GUI;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class DropDown {

  private static ComboBox<String> dropdown;

  private static String selected;

  private static Button button;

  private static VBox container;
  public DropDown(List<String> list, String buttonlabel) {

    container = new VBox();
    container.setId("Container-Vbox");
    dropdown = new ComboBox<>();
    dropdown.getItems().addAll(list);
    dropdown.setValue("Selected a Choice");

    container.getChildren().add(dropdown);

    button = new Button(buttonlabel);
    button.setId("drop-button");
//    button.setOnAction(e -> selected = dropdown.getValue());

    container.getChildren().add(button);


  }
  public String getValue(){
    return dropdown.getValue();
  }

  public Button getButton(){ return button;
  }
  public VBox getContainer(){
    return container;
  }
}
