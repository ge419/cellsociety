package cellsociety.GUI;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author Han Zhang
 */
public class GUIContainer {
  Stage mainStage;
  GridPane pane;

  FileUploader uploader;
  private static String GUI_CSS= "stylesheets/GUIContainer.css";
  public GUIContainer(Stage primaryStage) {
    mainStage = primaryStage;
    pane = new GridPane();

    pane.setGridLinesVisible(true);
    pane.setId("pane");

    setUpButtons();
    setUpSliderContainer();
    SetUpDescriptionBox();

    setUpFileUploader();

    FileSaver save = new FileSaver();
    pane.getChildren().add(save.getButton());
    pane.setConstraints(save.getButton(), 4, 0);
    save.setFile("Test");

    Scene stageScene = new Scene(pane, 1000, 700);
    mainStage.setScene(stageScene);
    stageScene.getStylesheets().add(GUI_CSS);
    mainStage.show();
  }

  private void setUpFileUploader() {
    FileUploader upload = new FileUploader();
    pane.getChildren().add(upload.getButton());
    pane.setConstraints(upload.getButton(), 3,0);
  }

  private void SetUpDescriptionBox() {
    TextArea description = new TextArea();
    VBox descriptionContainer = new VBox();
    descriptionContainer.getChildren().add(description);
    descriptionContainer.setVgrow(description, Priority.ALWAYS);
    pane.getChildren().add(descriptionContainer);
    pane.setConstraints(descriptionContainer, 3,3, 2, 1);
  }

  private void setUpButtons() {
    Button step = createButton("Step", 0, 5);
    Button reset = createButton("Reset", 1, 5);
    Button go = createButton("Go/Pause",2, 5);
    Button clear = createButton("Clear", 3, 5);
    Button random = createButton("Random", 4, 5);

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().addAll(step, reset, go, clear, random);
  }

  public Button createButton(String word, int x, int y){
    Button newButton = new Button(word);
    newButton.setId("Button");
    pane.setConstraints(newButton, x, y);
    return newButton;
  }

  public void setUpSliderContainer(){
    SliderContainer container = new SliderContainer(0, 50, 25, 5, "Animation Speed");
    pane.getChildren().add(container.getContainer());
    pane.setConstraints(container.getContainer(), 3, 4, 2, 1);
  }
}
