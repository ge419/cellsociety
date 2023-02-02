package cellsociety.GUI;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  public void start (Stage primaryStage) {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");

    PopUp test= new PopUp(list);
    test.showPopUp();
    GUIContainer container = new GUIContainer(primaryStage);
  }
}
