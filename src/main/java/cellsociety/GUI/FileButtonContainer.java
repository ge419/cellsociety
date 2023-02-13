package cellsociety.GUI;

import javafx.scene.layout.HBox;

public class FileButtonContainer {
  HBox container;
  public FileButtonContainer(FileSaver saver, FileUploader uploader){
    container = new HBox();
    container.setId("File-Buttons");
    container.getChildren().addAll(saver.getButton(), uploader.getButton());
  }

  public HBox getContainer() {
    return container;
  }
}
