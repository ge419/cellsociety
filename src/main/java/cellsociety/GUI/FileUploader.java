package cellsociety.GUI;

import cellsociety.ConfigInterface;
import cellsociety.Controller.SimulationController;
import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * @author Han Zhang
 */

public class FileUploader {
  private File uploaded;
  private final Button button;

  /**
   * Used this Chat GPT chat to help me build the constructor https://sharegpt.com/c/4ny1y2x
   * @param label Text to be displayed on upload button
   * @param config The config object that is passed through the GUIContainer
   */
  public FileUploader(String label, ConfigInterface config, SimulationController controller){
    Button uploadButton = new Button(label);
    button = uploadButton;
    uploadButton.setId("files-button");
    uploadButton.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choose a file");
      File selectedFile = fileChooser.showOpenDialog(null);
      if (selectedFile != null) {
        uploaded = selectedFile;
        config.readFile(uploaded);
        controller.setNewFile(true);
      }
    });
  }
  public Button getButton() {
    return button;
  }
}
