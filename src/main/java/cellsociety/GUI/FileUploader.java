package cellsociety.GUI;

import cellsociety.Config;
import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * @Author Han Zhang
 */

public class FileUploader {
  private File uploaded;

  private boolean fileUploaded;
  private Button button;

  /**
   * Used this Chat GPT chat to help me build the constructor https://sharegpt.com/c/4ny1y2x
   * @param label
   * @param config
   */
  public FileUploader(String label, Config config){
    Button uploadButton = new Button(label);
    button = uploadButton;
    uploadButton.setId("files-button");
    uploadButton.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choose a file");
      File selectedFile = fileChooser.showOpenDialog(null);
      if (selectedFile != null) {
        uploaded = selectedFile;
        fileUploaded = true;
        config.readFile(uploaded);
      }
    });
  }

  public boolean isFileUploaded() {
    return fileUploaded;
  }

  public File getUploaded() {
    return uploaded;
  }

  public Button getButton() {
    return button;
  }
}
