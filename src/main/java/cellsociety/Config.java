package cellsociety;

import cellsociety.GUI.PopUp;
import java.util.ResourceBundle;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
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
  public static final String INTERNAL_CONFIGURATION = "cellsociety.";
  private ResourceBundle myResources;

  private static String simType;
  private static String configName;
  private static String author;
  private static String description;
  private static int width;
  private static int height;
  private static List<Integer> currState;
  private static Element root;
  public static HashMap<String, Double> simParam;
  public static HashMap<String, Double> viewParam;
  public static HashSet<String> simNames;

  public Config() {
    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + "filesandstates");
    simNames = new HashSet<>();
    simNames.add(myResources.getString("LifeName"));
    simNames.add(myResources.getString("FireName"));
    simNames.add(myResources.getString("SegName"));
    simNames.add(myResources.getString("WTName"));
    simNames.add(myResources.getString("PercolName"));
  }

  /**
   * Reads XML file, if XML file is valid, upload info
   */
  public static void readFile(File xmlFile) {
    if (checkValidXML(xmlFile)) {
      uploadXML(root);
      if (!simNames.contains(getTextValue(root, "sim_type"))) {
        showMessage(AlertType.ERROR, "Invalid simulation name");
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
      simParam.put("probCatch", Double.parseDouble(getTextValue(root, "probCatch")));
      simParam.put("change", Double.parseDouble(getTextValue(root, "change")));
      simParam.put("eShark", Double.parseDouble(getTextValue(root, "eShark")));
      simParam.put("ePerFish", Double.parseDouble(getTextValue(root, "ePerFish")));
      simParam.put("fishBT", Double.parseDouble(getTextValue(root, "fishBT")));
      simParam.put("sharkBT", Double.parseDouble(getTextValue(root, "sharkBT")));
      viewParam.put("perAlive", Double.parseDouble(getTextValue(root, "perAlive")));
      viewParam.put("perTree", Double.parseDouble(getTextValue(root, "perTree")));
      viewParam.put("perFire", Double.parseDouble(getTextValue(root, "perFire")));
      viewParam.put("perEmpty", Double.parseDouble(getTextValue(root, "perEmpty")));
      viewParam.put("perStateOne", Double.parseDouble(getTextValue(root, "perStateOne")));
      viewParam.put("perShark", Double.parseDouble(getTextValue(root, "perShark")));
      viewParam.put("perBlocked", Double.parseDouble(getTextValue(root, "perBlocked")));


      // List of integers(or list of list of integers) for init_state
  }

  /**
   * Creates new XML file and saves current state of simulation.
   * Refined code from https://www.javaguides.net/2018/10/how-to-create-xml-file-in-java-dom-parser.html
   *  --> instead of creating new XML file, update currState?
   * @param simType
   * @param configName
   * @param author
   * @param description
   * @param width
   * @param height
   * @param currState
   */
  public void saveXML(String simType, String configName, String author, String description, int width, int height, List<Integer> currState) {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();

          Element rootElement = doc.createElement("data");
          doc.appendChild(rootElement);

          Element sim_type = doc.createElement("sim_type");
          sim_type.appendChild(doc.createTextNode(simType));
          rootElement.appendChild(sim_type);

          Element config_name = doc.createElement("config_Name");
          config_name.appendChild(doc.createTextNode(configName));
          rootElement.appendChild(config_name);

          Element auth = doc.createElement("author");
          auth.appendChild(doc.createTextNode(author));
          rootElement.appendChild(auth);

          Element descript = doc.createElement("description");
          descript.appendChild(doc.createTextNode(description));
          rootElement.appendChild(sim_type);

          Element wid = doc.createElement("width");
          wid.appendChild(doc.createTextNode(String.valueOf(width)));
          rootElement.appendChild(sim_type);

          Element hei = doc.createElement("height");
          hei.appendChild(doc.createTextNode(String.valueOf(height)));
          rootElement.appendChild(sim_type);

          Element curr_state = doc.createElement("curr_state");
          //curr_state.appendChild(doc.createTextNode(currState));   --> List<Integer> into String
          rootElement.appendChild(curr_state);

          Element params = doc.createElement("params");
          Element probCatch = doc.createElement("probCatch");
          probCatch.appendChild(doc.createTextNode(String.valueOf("probCatch")));
          params.appendChild(probCatch);

          Element change = doc.createElement("change");
          change.appendChild(doc.createTextNode(String.valueOf("change")));
          params.appendChild(change);

          Element eShark = doc.createElement("eShark");
          eShark.appendChild(doc.createTextNode(String.valueOf("eShark")));
          params.appendChild(eShark);

          Element ePerFish = doc.createElement("ePerFish");
          ePerFish.appendChild(doc.createTextNode(String.valueOf("ePerFish")));
          params.appendChild(ePerFish);

          Element fishBT = doc.createElement("fishBT");
          fishBT.appendChild(doc.createTextNode(String.valueOf("fishBT")));
          params.appendChild(fishBT);

          Element sharkBT = doc.createElement("sharkBT");
          sharkBT.appendChild(doc.createTextNode(String.valueOf("sharkBT")));
          params.appendChild(sharkBT);

          Element perAlive = doc.createElement("perAlive");
          perAlive.appendChild(doc.createTextNode(String.valueOf("perAlive")));
          params.appendChild(perAlive);

          Element perTree = doc.createElement("perTree");
          perTree.appendChild(doc.createTextNode(String.valueOf("perTree")));
          params.appendChild(perTree);

          Element perFire = doc.createElement("perFire");
          perFire.appendChild(doc.createTextNode(String.valueOf("perFire")));
          params.appendChild(perFire);

          Element perEmpty = doc.createElement("perEmpty");
          perEmpty.appendChild(doc.createTextNode(String.valueOf("perEmpty")));
          params.appendChild(perEmpty);

          Element perStateOne = doc.createElement("perStateOne");
          perStateOne.appendChild(doc.createTextNode(String.valueOf("perStateOne")));
          params.appendChild(perStateOne);

          Element perShark = doc.createElement("perShark");
          perShark.appendChild(doc.createTextNode(String.valueOf("perShark")));
          params.appendChild(perShark);

          Element perBlocked = doc.createElement("perBlocked");
          perBlocked.appendChild(doc.createTextNode(String.valueOf("perBlocked")));
          params.appendChild(perBlocked);

          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File("*.xml"));
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