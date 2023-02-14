package cellsociety;

import java.io.File;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 * @author Han Zhang, Changmin Shin
 */

public interface ConfigInterface {

  void readFile(File file);

  Document saveXML(String state, Map<String, Double> parameters)
      throws ParserConfigurationException;

  String getVariant();

  String getName();

  String getAuthor();

  String getDescription();


}
