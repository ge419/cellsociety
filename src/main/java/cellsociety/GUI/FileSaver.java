package cellsociety.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class FileSaver {

  private File saved;

  private static String data;
  private static PrintWriter fileWriter;
  private static Button button;
  public FileSaver(String label, VisualGrid grid) {
    button = new Button(label);
    button.setId("files-button");
    button.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
      fileChooser.getExtensionFilters().add(xmlFilter);
      fileChooser.setSelectedExtensionFilter(xmlFilter);
      saved = fileChooser.showSaveDialog(button.getScene().getWindow());
      if (saved != null) {
        try (PrintWriter writer = new PrintWriter(saved)) {

//          saved = Config.saveXML(grid.getStates());
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
    data = str;
  }
}
