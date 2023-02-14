package cellsociety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @Author Changmin Shin
 */

public class Config implements ConfigInterface {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.";
  public final List<String> paramName = new ArrayList<>(
      Arrays.asList("probCatch", "change", "eShark", "ePerFish", "fishBT", "sharkBT", "perAlive",
          "perTree", "perFire", "perEmpty", "perStateOne", "perShark", "perBlocked"));
  public final List<String> strTagNames = new ArrayList<>(Arrays.asList(""));
  private static final ResourceBundle myResources = ResourceBundle.getBundle(
      INTERNAL_CONFIGURATION + "filesandstates");

  String simType;
  String configName;
  String author;
  String description;
  int width;
  int height;
  String initState;
  List<List<Integer>> currState;
  Element root;
  Map<String, Double> simParam;
  Set<String> simNames;

  public Config() {
    simNames = new HashSet<>();
  }

  /**
   * Reads the selected XML file given as a parameter, checks if the file is valid, and if true,
   * saves the values in each tag to corresponding variables in Config class, and if false, resets
   * all values to default.
   *
   * @param xmlFile The xml file that is selected by the user to be read.
   */
  public void readFile(File xmlFile) {
    simNames.add(myResources.getString("LifeName"));
    simNames.add(myResources.getString("FireName"));
    simNames.add(myResources.getString("SegName"));
    simNames.add(myResources.getString("WTName"));
    simNames.add(myResources.getString("PercolName"));
    if (checkValidXML(xmlFile)) {
      simParam = new HashMap<>();
      updateXML(root);
      if (!simNames.contains(getTextValue(root, "sim_type"))) {
        showMessage(AlertType.ERROR, "Invalid simulation name");
        resetTagValues();
      }
    }
  }

  /**
   * Resets the values in each xml tag variables to empty string, 0, or 0.0.
   */
  private void resetTagValues() {
    simType = "";
    configName = "";
    author = "";
    description = "";
    width = 0;
    height = 0;
    currState = new ArrayList<>();
    for (String s : paramName) {
      simParam.put(s, 0.0);
    }
  }

  /**
   * Checks if the XML file is valid
   */
  // TODO: Check exceptions
  public boolean checkValidXML(File xmlFile) {
    try {
      Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder()
          .parse(xmlFile);
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

  /**
   * Takes the values in the xml file as Strings
   *
   * @param e       Element in xml file that is being accessed
   * @param tagName The name of the tag
   * @return The value of the corresponding tag as String
   */
  private String getTextValue(Element e, String tagName) {
    NodeList nodeList = e.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    } else {
      // FIXME: empty string or exception? In some cases it may be an error to not
      // find any text
      return "";
    }
  }

  private void showMessage(AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  /**
   * Saves values in each tag into variables in Config class.
   *
   * @param root The root element the xml file is reading from
   */
  public void updateXML(Element root) {
    simType = getTextValue(root, "sim_type");
    configName = getTextValue(root, "config_Name");
    author = getTextValue(root, "author");
    description = getTextValue(root, "description");
    width = Integer.parseInt(getTextValue(root, "width"));
    height = Integer.parseInt(getTextValue(root, "height"));
    initState = getTextValue(root, "init_state");
    for (String s : paramName) {
      simParam.put(s, Double.parseDouble(getTextValue(root, s)));
    }
  }

  /**
   * Creates new XML file and saves current state of simulation. Refined code from
   * https://www.javaguides.net/2018/10/how-to-create-xml-file-in-java-dom-parser.html
   * https://chat.openai.com/chat/1e2e6e32-cf3e-4c72-998a-ab3e1a8183c5
   * https://mkyong.com/java/how-to-create-xml-file-in-java-dom/
   *
   * @param state      Current state of the grid which has been converted to a String
   * @param parameters Map of parameters that is taken from SimEngine
   */
  public Document saveXML(String state, Map<String, Double> parameters)
      throws ParserConfigurationException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.newDocument();
    addElements(doc, state, parameters);
    return doc;
  }

  /**
   * Creates Elements rootElement and params, and appends corresponding tag names and values to the
   * XML file
   *
   * @param doc The XML document that is being modified by the code.
   */
  private void addElements(Document doc, String state, Map<String, Double> parameters) {
    Element rootElement = doc.createElement("data");
    doc.appendChild(rootElement);
    rootElement.appendChild(addTagStr(doc, "sim_type", simType));
    rootElement.appendChild(addTagStr(doc, "config_Name", configName));
    rootElement.appendChild(addTagStr(doc, "author", author));
    rootElement.appendChild(addTagStr(doc, "description", description));
    rootElement.appendChild(addTagInt(doc, "width", width));
    rootElement.appendChild(addTagInt(doc, "height", height));
    rootElement.appendChild(addTagStr(doc, "curr_state", state));
    Element params = doc.createElement("params");
    for (String s : paramName) {
      params.appendChild(addTagParam(doc, s, parameters));
    }
    rootElement.appendChild(params);
  }

  private org.w3c.dom.Node addTagStr(Document doc, String tagName, String value) {
    Element node = doc.createElement(tagName);
    node.setTextContent(value);
    return node;
  }

  private org.w3c.dom.Node addTagInt(Document doc, String tagName, int value) {
    Element node = doc.createElement(tagName);
    node.setTextContent(String.valueOf(value));
    return node;
  }

  private org.w3c.dom.Node addTagParam(Document doc, String tagName,
      Map<String, Double> param) {
    Element node = doc.createElement(tagName);
    node.setTextContent(String.valueOf(param.get(tagName)));
    return node;
  }

  public String getVariant() {
    return simType;
  }

  public Map<String, Double> getSimParam() {
    return simParam;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getInitState() {
    return initState;
  }

  public String getDescription() {
    return description;
  }

  public String getAuthor() {
    return author;
  }

  public String getName() {
    return configName;
  }
}