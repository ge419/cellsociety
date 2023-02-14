package cellsociety;

import java.io.File;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 * @author Han Zhang, Changmin Shin
 */

public interface ConfigInterface {

  /**
   * Allows the Config to take in a file and start to parse it
   * @param file input that is being loaded into the config
   */
  void readFile(File file);

  /**
   * @param state State of simulation
   * @param parameters Parameters and their corresponding avlues
   * @return A Document that will be saved
   * @throws ParserConfigurationException When the parsing exception is thrown, Document was not able
   * to be properly built, resulting in an Exception
   */
  Document saveXML(String state, Map<String, Double> parameters)
      throws ParserConfigurationException;

  /**
   * Gets type of simulation
   * @return Variant
   */
  String getVariant();

  /**
   * Gets Name of Simulation
   * @return Name
   */
  String getName();

  /**
   * Gets Author of Simulation
   * @return author
   */
  String getAuthor();

  /**
   * gets Description of Simulation
   * @return description
   */
  String getDescription();


}
