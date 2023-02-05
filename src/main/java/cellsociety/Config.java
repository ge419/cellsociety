package cellsociety;

import cellsociety.GUI.PopUp;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @Author Changmin Shin
 */

public class Config {

  private static String simType;
  private static String configName;
  private static String author;
  private static String description;
  private static int width;
  private static int height;
  private static List<Integer> currState;
  private static Element root;

  /**
   * Reads XML file, if XML file is valid, upload info
   */
  public static void readFile(File xmlFile) {
    if (checkValidXML(xmlFile)) {
      // code checking if the simType is in the list of simType names
      uploadXML(root);
      switch (getTextValue(root, "sim_type")) {
        case "Game of Life":
          // initiate Game of Life
          break;
        case "Spreading Fire":
          // initiate Spreading Fire
          break;
        case "Schelling's Model of Segregation":
          // initiate Schelling's Model of Segregation
          break;
        case "Wa-Tor World Model of Predator-Prey Relationships":
          // initiate Wa-Tor World Model of Predator-Prey Relationships
          break;
        case "Percolation":
          // initiate Percolation
          break;
        default:
          // popup for incorrect sim_type
          //PopUp.showPopUp();
          resetTagValues();
      }
    }
  }

  private static void resetTagValues() {
    simType = "";
    configName = "";
    author = "";
    description = "";
    width = 0;
    height = 0;
    //currState = empty list of integers
  }

  /**
   * Checks if the XML file is valid
   */
  public static boolean checkValidXML(File xmlFile) {
    try {
      Document xmlDocument =
          DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
      root = xmlDocument.getDocumentElement();
    } catch (NumberFormatException e) {
      showMessage(AlertType.ERROR, "Invalid number given in data");
      return false;
    } catch (ParserConfigurationException e) {
      showMessage(AlertType.ERROR, "Invalid XML Configuration");
      return false;
    } catch (SAXException | IOException e) {
      showMessage(AlertType.ERROR, "Invalid XML Data");
      return false;
    }
    return true;
  }

  private static String getTextValue(Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    } else {
      // FIXME: empty string or exception? In some cases it may be an error to not
      // find any text
      return "";
    }
  }

  private static void showMessage(AlertType type, String message) {    // Is PopUp class necessary?
    new Alert(type, message).showAndWait();
  }

  /**
   * Saves values in each tag into variables in Config class.
   * @param root
   */

  public static void uploadXML(Element root) {
      simType = getTextValue(root, "sim_type");
      configName = getTextValue(root,"config_Name");
      author = getTextValue(root, "author");
      description = getTextValue(root, "description");
      width = Integer.parseInt(getTextValue(root, "width"));
      height = Integer.parseInt(getTextValue(root, "height"));

      // List of integers(or list of list of integers) for init_state
  }

  /**
   * Creates new XML file and saves current state of simulation.
   * Refined code from https://www.javaguides.net/2018/10/how-to-create-xml-file-in-java-dom-parser.html
   * @param simType
   * @param configName
   * @param author
   * @param description
   * @param width
   * @param weight
   * @param currState
   */
  public void saveXML(String simType, String configName, String author, String description, int width, int weight, List<Integer> currState) {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();

          Element rootElement = doc.createElement("data");
          doc.appendChild(rootElement);

//          rootElement.appendChild(createStatus(doc, "sim_type", RuleBook.getSimType())); // from GUI not RuleBook
//          rootElement.appendChild(createStatus(doc, "config_Name", RuleBook.getConfigName()));
//          rootElement.appendChild(createStatus(doc, "author", RuleBook.getAuthor()));
//          rootElement.appendChild(createStatus(doc, "description", RuleBook.getDescription()));
//          rootElement.appendChild(createStatus(doc, "width", RuleBook.getWidth()));
//          rootElement.appendChild(createStatus(doc, "height", RuleBook.getHeight()));
//          rootElement.appendChild(createStatus(doc, "curr_state", RuleBook.getCurrState())); // or init_state

          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File("file.xml")); // assign names?
          transformer.transform(source, result);
        } catch (Exception e) {
          e.printStackTrace();
        }
  }

  /**
   * Helper method to saveXML(), creates and appends new tag and values to the XML file.
   * @param doc
   * @param tagName
   * @param value
   */

  private org.w3c.dom.Node createStatus(Document doc, String tagName, String value) {
    Element node = doc.createElement(tagName);
    doc.appendChild(doc.createTextNode(value));
    return node;
  }

  public String getVariant() {
    return simType;
  }
}