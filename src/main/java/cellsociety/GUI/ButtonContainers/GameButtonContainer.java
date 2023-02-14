package cellsociety.GUI.ButtonContainers;

import cellsociety.Controller.SimulationController;
import cellsociety.Engine.EngineInterface;
import cellsociety.GUI.ButtonContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author Han Zhang
 */
public class GameButtonContainer extends ButtonContainer {
    /**
     *
     * @param simulationEngine Engine interface that the buttons can direclty access
     * @param controller AnimationInterface controller that can directly alter animation speed
     * @param bundle Resource Bundle to translate text
     */
    public GameButtonContainer(EngineInterface simulationEngine, SimulationController controller, ResourceBundle bundle){
        super();
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
}
