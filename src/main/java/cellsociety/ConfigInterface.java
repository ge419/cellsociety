package cellsociety;

import java.io.File;

public interface ConfigInterface {
  void readFile(File file);
  File saveXML(String state);
  String getVariant();
  String getName();
  String getAuthor();
  String getDescription();


}
