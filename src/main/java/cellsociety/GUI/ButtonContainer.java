package cellsociety.GUI;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonContainer {

    HBox container;
    List<Button> Buttons;
    public ButtonContainer(List<String> Commands){
        container = new HBox();
        container.setId("Container-HBox");
        for(String Command: Commands){
            container.getChildren().add(createButton(Command));
        }
    }
    public Button createButton(String word) {
        Button newButton = new Button(word);
        Buttons.add(newButton);
        newButton.setId("Button");
        return newButton;
    }

}
