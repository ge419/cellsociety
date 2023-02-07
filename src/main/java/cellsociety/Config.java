package cellsociety;

import cellsociety.GUI.PopUp;
import java.util.ArrayList;
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
  private static ResourceBundle myResources;

  private static String simType;
  private static String configName;
  private static String author;
  private static String description;
  private static int width;
  private static int height;
  private static String initState;
  private static List<List<Integer>> currState;
  private static Element root;
  public static HashMap<String, Double> simParam;
  public static HashMap<String, Double> viewParam;
  public static HashSet<String> simNames;

  /**
   * Reads XML file, if XML file is valid, upload info
   */
  public static void readFile(File xmlFile) {
    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + "filesandstates");
    simNames = new HashSet<>();
    simNames.add(myResources.getString("LifeName"));
    simNames.add(myResources.getString("FireName"));
    simNames.add(myResources.getString("SegName"));
    simNames.add(myResources.getString("WTName"));
    simNames.add(myResources.getString("PercolName"));

    if (checkValidXML(xmlFile)) {
      updateXML(root);
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

  public static void updateXML(Element root) {
      simType = getTextValue(root, "sim_type");
      configName = getTextValue(root,"config_Name");
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
      viewParam.put("perAlive", Double.parseDouble(getTextValue(root, "perAlive")));
      viewParam.put("perTree", Double.parseDouble(getTextValue(root, "perTree")));
      viewParam.put("perFire", Double.parseDouble(getTextValue(root, "perFire")));
      viewParam.put("perEmpty", Double.parseDouble(getTextValue(root, "perEmpty")));
      viewParam.put("perStateOne", Double.parseDouble(getTextValue(root, "perStateOne")));
      viewParam.put("perShark", Double.parseDouble(getTextValue(root, "perShark")));
      viewParam.put("perBlocked", Double.parseDouble(getTextValue(root, "perBlocked")));

      List<List<String>> stateArr = new ArrayList<>();
      String[] splitInit = initState.split("\n");
      for(int i = 0; i < splitInit.length; i++) {
        List<String> row = new ArrayList<>();
        String[] rowSplit = splitInit[i].split(" ");
        for(int j = 0; j < rowSplit.length; i++) {
          row.add(i, rowSplit[i]);
        }
        stateArr.add(i, row);
      }
      currState = converter(stateArr);
  }

  private static List<List<Integer>> converter(List<List<String>> state) {
    List<List<Integer>> current = new ArrayList<>();
    for (int i = 0; i < state.size(); i++) {
      for (int j = 0; j < state.get(i).size(); j++) {
        current.get(i).add(j, Integer.parseInt(state.get(i).get(j)));
      }
    }
    return current;
  }

  /**
   * Creates new XML file and saves current state of simulation.
   * Refined code from https://www.javaguides.net/2018/10/how-to-create-xml-file-in-java-dom-parser.html
   * @param currState
   */
  public void saveXML(List<Integer> currState) {
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

          Element curr_state = doc.createElement("curr_state");
          //curr_state.appendChild(doc.createTextNode(currState));   --> List<Integer> into String
          rootElement.appendChild(curr_state);

          Element params = doc.createElement("params");
          params.appendChild(addTagParam(doc, "probCatch",simParam));
          params.appendChild(addTagParam(doc, "change",simParam));
          params.appendChild(addTagParam(doc, "eShark",simParam));
          params.appendChild(addTagParam(doc, "ePerFish",simParam));
          params.appendChild(addTagParam(doc, "fishBT",simParam));
          params.appendChild(addTagParam(doc, "sharkBT",simParam));
          params.appendChild(addTagParam(doc, "perAlive",viewParam));
          params.appendChild(addTagParam(doc, "perTree",viewParam));
          params.appendChild(addTagParam(doc, "perFire",viewParam));
          params.appendChild(addTagParam(doc, "perEmpty",viewParam));
          params.appendChild(addTagParam(doc, "perStateOne",viewParam));
          params.appendChild(addTagParam(doc, "perShark",viewParam));
          params.appendChild(addTagParam(doc, "perBlocked",viewParam));

          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File("*.xml"));
          transformer.transform(source, result);
        } catch (Exception e) {
          e.printStackTrace();
        }
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

  private org.w3c.dom.Node addTagParam(Document doc, String tagName, HashMap<String, Double> param) {
    Element node = doc.createElement(tagName);
    doc.appendChild(doc.createTextNode(String.valueOf(param.get("tagName"))));
    return node;
  }

  public String getVariant() {
    return simType;
  }
}