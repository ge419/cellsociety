package cellsociety.GUI;

import cellsociety.SimulationController;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonContainer {

    HBox container;
    List<Button> Buttons;
    public ButtonContainer(List<String> Commands, SimulationController simulationEngine) throws IllegalStateException{
        if(Commands.size()==0){
            throw new IllegalStateException("Container contains no buttons");
        }
        container = new HBox();
        Buttons = new ArrayList<>();
        container.setId("Container-HBox");
        for(String Command: Commands){
            container.getChildren().add(createButton(Command));
        }
        Buttons.get(0).setOnAction(e -> );
    }
    public Button createButton(String word) {
        Button newButton = new Button(word);
        Buttons.add(newButton);
        newButton.setId(word);
        return newButton;
    }

    public HBox getContainer() {
        return container;
    }
    public List<Button> getButtons() {
        return Buttons;
    }
}
