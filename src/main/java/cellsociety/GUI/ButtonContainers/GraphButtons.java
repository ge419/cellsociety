package cellsociety.GUI.ButtonContainers;

import cellsociety.GUI.ButtonContainer;
import cellsociety.GUI.VisualGraphInterface;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * @author Han
 */
public class GraphButtons extends ButtonContainer {
  public GraphButtons(ResourceBundle bundle, VisualGraphInterface visualGraph){
    super();
    Button btn = createButton(bundle.getString("PieGraph"));
    btn.setOnAction(e -> visualGraph.showGraph());
  }
}
