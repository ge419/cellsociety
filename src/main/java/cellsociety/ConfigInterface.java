package cellsociety;

import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

public interface ConfigInterface {
  void readFile(File file);
  Document saveXML(String state) throws ParserConfigurationException;
  String getVariant();
  String getName();
  String getAuthor();
  String getDescription();


}
