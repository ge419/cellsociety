package cellsociety.GUI;

import cellsociety.Config;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class DescriptionBox {
  private VBox descriptionContainer;
  public DescriptionBox(Config config){
    TextArea description = new TextArea();
    setUpDescription(config, description);
    descriptionContainer = new VBox();
    descriptionContainer.getChildren().add(description);
  }

  private static void setUpDescription(Config config, TextArea description) {
    description.setId("TextBox");
    String text = config.getVariant() + "\n" + config.getName() + "\n" + config.getAuthor() + "\n" + config.getDescription();

    description.setText(text);
  }

  public VBox getDescriptionContainer() {
    return descriptionContainer;
  }
}
