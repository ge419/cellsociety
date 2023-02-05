package cellsociety.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class FileSaver {

  File saved;

  PrintWriter fileWriter;
  Button button;
  public FileSaver() {
    button = new Button("Save to File");
    button.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      saved = fileChooser.showSaveDialog(button.getScene().getWindow());
      if (saved != null) {
        try (PrintWriter writer = new PrintWriter(saved)) {
          fileWriter = writer;
        } catch (FileNotFoundException ex) {
          System.out.println("Error writing to file: " + ex.getMessage());
        }
      }
    });
  }

  public Button getButton() {
    return button;
  }
  public void setFile(String str){
    fileWriter.println(str);
  }
}
