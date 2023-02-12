package cellsociety.GUI;

import cellsociety.Controller.AnimationInterface;
import cellsociety.Engine.EngineInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonContainer {

    HBox container;
    List<Button> Buttons;

    public ButtonContainer(EngineInterface simulationEngine, AnimationInterface controller, ResourceBundle bundle){
        container = new HBox();
        Buttons = new ArrayList<>();
        container.setId("Container-HBox");

        Button btn = createButton(bundle.getString("Step"));
        btn.setOnAction(e -> controller.stepAnimation());
        btn = createButton(bundle.getString("Reset"));
        btn.setOnAction(e -> simulationEngine.reset());
        btn = createButton(bundle.getString("Go/Pause"));
        btn.setOnAction(e -> controller.pauseToggle());
        btn = createButton(bundle.getString("Clear"));
        btn.setOnAction(e -> simulationEngine.blankStart());
        btn = createButton(bundle.getString("Random"));
        btn.setOnAction(e -> simulationEngine.randomizeStart());
    }
    public Button createButton(String word) {
        Button newButton = new Button(word);
        container.getChildren().add(newButton);
        Buttons.add(newButton);
        newButton.setId("Button");
        return newButton;
    }

    public HBox getContainer() {
        return container;
    }
    public List<Button> getButtons() {
        return Buttons;
    }
}
