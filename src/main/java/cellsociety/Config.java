package cellsociety;

import cellsociety.Cells.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

//TODO: use for loops and the arraylist for repeating lines of code.
public class Config {

  public static final String INTERNAL_CONFIGURATION = "cellsociety.";
  public static final List<String> paramName = new ArrayList<>(
      Arrays.asList("probCatch", "change", "eShark", "ePerFish", "fishBT", "sharkBT", "perAlive",
          "perTree", "perFire", "perEmpty", "perStateOne", "perShark", "perBlocked"));
  private static final ResourceBundle myResources = ResourceBundle.getBundle(
      INTERNAL_CONFIGURATION + "filesandstates");

  private String simType;
  private String configName;
  private String author;
  private String description;
  private int width;
  private int height;
  private String initState;
  private List<List<Integer>> currState;
  private Element root;
  private HashMap<String, Double> simParam;
  private HashSet<String> simNames;

  /**
   * Reads XML file, if XML file is valid, upload info
   */

  public Config() {
    simNames = new HashSet<>();
  }

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

  private void resetTagValues() {
    simType = "";
    configName = "";
    author = "";
    description = "";
    width = 0;
    height = 0;
    currState = new ArrayList<>();
    for (String s: simNames) {
      simParam.put(s, 0.0);
    }
  }

  /**
   * Checks if the XML file is valid
   */
  //TODO: Check exceptions
  public boolean checkValidXML(File xmlFile) {
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

  private void showMessage(AlertType type, String message) {    // Is PopUp class necessary?
    new Alert(type, message).showAndWait();
  }

  /**
   * Saves values in each tag into variables in Config class.
   *
   * @param root
   */

  public void updateXML(Element root) {
    simType = getTextValue(root, "sim_type");
    configName = getTextValue(root, "config_Name");
    author = getTextValue(root, "author");
    description = getTextValue(root, "description");
    width = Integer.parseInt(getTextValue(root, "width"));
    height = Integer.parseInt(getTextValue(root, "height"));
    initState = getTextValue(root, "init_state");
    simParam.put("probCatch", Double.parseDouble(getTextValue(root, "probCatch")));
    simParam.put("change", Double.parseDouble(getTextValue(root, "change")));
    simParam.put("eShark", Double.parseDouble(getTextValue(root, "eShark")));
    simParam.put("ePerFish", Double.parseDouble(getTextValue(root, "ePerFish")));
    simParam.put("fishBT", Double.parseDouble(getTextValue(root, "fishBT")));
    simParam.put("sharkBT", Double.parseDouble(getTextValue(root, "sharkBT")));
    simParam.put("perAlive", Double.parseDouble(getTextValue(root, "perAlive")));
    simParam.put("perTree", Double.parseDouble(getTextValue(root, "perTree")));
    simParam.put("perFire", Double.parseDouble(getTextValue(root, "perFire")));
    simParam.put("perEmpty", Double.parseDouble(getTextValue(root, "perEmpty")));
    simParam.put("perStateOne", Double.parseDouble(getTextValue(root, "perStateOne")));
    simParam.put("perShark", Double.parseDouble(getTextValue(root, "perShark")));
    simParam.put("perBlocked", Double.parseDouble(getTextValue(root, "perBlocked")));

    //System.out.println(initState);
    //currState = strToGrid();
  }

  /**
   * Creates new XML file and saves current state of simulation. Refined code from
   * https://www.javaguides.net/2018/10/how-to-create-xml-file-in-java-dom-parser.html
   *
   * @param currState
   */
  public void saveXML(List<List<Integer>> currState) {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();

      Element rootElement = doc.createElement("data");
      doc.appendChild(rootElement);

      rootElement.appendChild(addTagStr(doc, "sim_type", simType));
      rootElement.appendChild(addTagStr(doc, "config_Name", configName));
      rootElement.appendChild(addTagStr(doc, "author", author));
      rootElement.appendChild(addTagStr(doc, "description", description));
      rootElement.appendChild(addTagInt(doc, "width", width));
      rootElement.appendChild(addTagInt(doc, "height", height));
      rootElement.appendChild(addTagStr(doc, "curr_state", intStrConverter(currState)));
      Element params = doc.createElement("params");
      params.appendChild(addTagParam(doc, "probCatch", simParam));
      params.appendChild(addTagParam(doc, "change", simParam));
      params.appendChild(addTagParam(doc, "eShark", simParam));
      params.appendChild(addTagParam(doc, "ePerFish", simParam));
      params.appendChild(addTagParam(doc, "fishBT", simParam));
      params.appendChild(addTagParam(doc, "sharkBT", simParam));
      params.appendChild(addTagParam(doc, "perAlive", simParam));
      params.appendChild(addTagParam(doc, "perTree", simParam));
      params.appendChild(addTagParam(doc, "perFire", simParam));
      params.appendChild(addTagParam(doc, "perEmpty", simParam));
      params.appendChild(addTagParam(doc, "perStateOne", simParam));
      params.appendChild(addTagParam(doc, "perShark", simParam));
      params.appendChild(addTagParam(doc, "perBlocked", simParam));

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("*.xml"));
      transformer.transform(source, result);
    } catch (Exception e) {
      //TODO: figure out the exception
      e.printStackTrace();
    }
  }

//  private void addToElement(Element e) {
//    List<String> strNames = new ArrayList<>(); // How to set up basic arraylist
//    strNames.add("sim_type");
//    e.appendChild(addTagStr());
//  }

  private String intStrConverter(List<List<Integer>> state) {
    List<List<String>> current = new ArrayList<>();
    for (int i = 0; i < state.size(); i++) {
      for (int j = 0; j < state.get(i).size(); j++) {
        current.get(i).add(j, String.valueOf(state.get(i).get(j)));
      }
    }
    List<String> toStringArr = new ArrayList<>();
    for (int k = 0; k < current.size(); k++) {
      toStringArr.add(k, String.join(" ", current.get(k)));
    }
    return String.join("\n", toStringArr);
  }

  private org.w3c.dom.Node addTagStr(Document doc, String tagName, String value) {
    Element node = doc.createElement(tagName);
    doc.appendChild(doc.createTextNode(value));
    return node;

//    Element sim_type = doc.createElement("sim_type");
//    sim_type.appendChild(doc.createTextNode(simType));
//    rootElement.appendChild(sim_type);
  }

  private org.w3c.dom.Node addTagInt(Document doc, String tagName, int value) {
    Element node = doc.createElement(tagName);
    doc.appendChild(doc.createTextNode(String.valueOf(value)));
    return node;
  }

  private org.w3c.dom.Node addTagParam(Document doc, String tagName,
      HashMap<String, Double> param) {
    Element node = doc.createElement(tagName);
    doc.appendChild(doc.createTextNode(String.valueOf(param.get("tagName"))));
    return node;
  }

  public String getVariant() {
    return simType;
  }

  public HashMap<String, Double> getSimParam() {
    return simParam;
  }

  public List<List<Integer>> getIntGrid() {
    return currState;
  }

  public String getInitState() {
    return initState;
  }
}