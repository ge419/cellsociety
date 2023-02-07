package cellsociety.GUI;

import cellsociety.Config;
import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class FileUploader {
  private File uploaded;

  private boolean fileUploaded;
  private Button button;
  //ChatGPT chat https://chat.openai.com/chat/58c1eb7e-810d-4c1a-b4a6-26be693a9dd4
  public FileUploader(String label){
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
        Config.readFile(uploaded);
      }
    });
  }

  public boolean isFileUploaded() {
    return fileUploaded;
  }

  public void setFileUploaded(boolean state){
    fileUploaded = state;
  }

  public File getUploaded() {
    return uploaded;
  }

  public Button getButton() {
    return button;
  }
}
