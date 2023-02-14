package cellsociety.GUI;

import cellsociety.ConfigInterface;
import cellsociety.Engine.EngineInterface;
import cellsociety.Grid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Han Zhang, Changmin Shin
 */

public class FileSaver {

  private File saved;
  private static Button button;

  /**
   * Used this Chat GPT chat to help me build the constructor <a href="https://shareg.pt/dhJrFWq">...</a>,
   * and https://chat.openai.com/chat/1e2e6e32-cf3e-4c72-998a-ab3e1a8183c5
   *
   * @param label  Text to be displayed on upload button
   * @param config The config object that is passed through the GUIContainer
   */
  public FileSaver(String label, ConfigInterface config, EngineInterface simulationEngine) {
    button = new Button(label);
    button.setId("files-button");
    button.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Current State into XML File");
      FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)",
          "*.xml");
      fileChooser.getExtensionFilters().add(xmlFilter);
      fileChooser.setSelectedExtensionFilter(xmlFilter);
      File file = fileChooser.showSaveDialog(button.getScene().getWindow());
      if (file != null) {
        try {
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          DOMSource source = new DOMSource(
              config.saveXML(simulationEngine.gridToStr(simulationEngine.getGrid()),
                  simulationEngine.getParams()));
          StreamResult result = new StreamResult(file);
          transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
          ex.printStackTrace();
        } catch (TransformerException ex) {
          ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
          ex.printStackTrace();
        }
      }
    });
  }

  public Button getButton() {
    return button;
  }
}
