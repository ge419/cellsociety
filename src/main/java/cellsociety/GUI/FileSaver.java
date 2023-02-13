package cellsociety.GUI;

import cellsociety.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * @Author Han Zhang
 */

public class FileSaver {

  private File saved;

  private static String data;
  private static PrintWriter fileWriter;
  private static Button button;

  /**
   * Used this Chat GPT chat to help me build the constructor https://shareg.pt/dhJrFWq,
   * @param label Text to be displayed on upload button
   * @param config The config object that is passed through the GUIContainer
   */
  public FileSaver(String label, Config config) {
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
          saved = config.saveXML(saved);
        } catch (FileNotFoundException ex) {
          System.out.println("Error writing to file: " + ex.getMessage());
        }
      }
    });
  }

  public Button getButton() {
    return button;
  }
}
