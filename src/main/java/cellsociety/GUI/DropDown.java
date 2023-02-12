package cellsociety.GUI;

import cellsociety.Config;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class DropDown {

  private static ComboBox<String> dropdown;

  private static VBox container;
  public DropDown(List<String> list, String buttonLabel, Config config) {

    container = new VBox();
    container.setId("Container-Vbox");
    dropdown = new ComboBox<>();
    dropdown.getItems().addAll(list);
    dropdown.setValue("Selected a Choice");

    container.getChildren().add(dropdown);
    Button button = new Button(buttonLabel);
    button.setId("drop-button");
    button.setOnAction(e -> loadFile(config));
    container.getChildren().add(button);
  }

  private void loadFile(Config config) {
    sendFileToConfig(dropdown.getValue(), config);
  }

  private void sendFileToConfig(String fileName, Config config) {
    List<String> dirctNames = new ArrayList<>();
    dirctNames.add("data/Preloaded_Files");
    File file = new File("");
    while (!dirctNames.isEmpty()) {
      String dirctName = dirctNames.get(dirctNames.size() - 1);
      dirctNames.remove(dirctNames.size() - 1);
      file = findFile(fileName, dirctName);
      if (file != null) {
        break;
      }
    }
    config.readFile(file);
  }

  /**
   * This method is based on a CHAT GPT conversation, https://sharegpt.com/c/vSTdS6B, finds a file
   * based on inputted fileName
   *
   * @param fileName This is the name of the XML file
   * @return The return value is the file that is the file name
   */
  private File findFile(String fileName, String directoryName) {
    File file = new File(directoryName);
    File[] matchingFiles = file.listFiles((dir, name) -> name.endsWith(fileName));
    assert matchingFiles != null;
    if (matchingFiles.length == 0) {
      return null;
    }
    return matchingFiles[0];
  }

  public String getValue(){
    return dropdown.getValue();
  }
  public VBox getContainer(){
    return container;
  }
}
