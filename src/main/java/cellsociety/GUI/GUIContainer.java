package cellsociety.GUI;

import cellsociety.GUI.Grids.RectangleGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author Han Zhang
 */
public class GUIContainer {
  private Stage mainStage;
  private GridPane pane;

  private String request;
  private FileUploader uploader;

  private ResourceBundle myResources;
  private static String GUI_CSS= "stylesheets/GUIContainer.css";

  public static final String INTERNAL_CONFIGURATION = "cellsociety.";


  public GUIContainer(Stage primaryStage, String language) {
    mainStage = primaryStage;
    pane = new GridPane();

    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + language);
    pane.setGridLinesVisible(true);
    pane.setId("pane");

    setUpButtons();
    setUpSliderContainer();
    SetUpDescriptionBox();

    setUpFileUploader();

    setUpFileSaver();

    setUpGrid();

    List<String> FileNames = new ArrayList<>();
    FileNames.add("test");
    FileNames.add("test1");
    setUpDropDown(FileNames);


    Scene stageScene = new Scene(pane, 1000, 700);
    mainStage.setScene(stageScene);
    stageScene.getStylesheets().add(GUI_CSS);
    mainStage.show();
  }

  private void setUpGrid() {
    RectangleGrid grid = new RectangleGrid(10,10);
    pane.getChildren().add(grid.getGridLayout());
    pane.setConstraints(grid.getGridLayout(), 0, 0, 3,4);
  }

  private void setUpDropDown(List<String> FileNames) {
    DropDown drop = new DropDown(FileNames);
    pane.getChildren().add(drop.getContainer());
    pane.setConstraints(drop.getContainer(), 3,1, 2, 1);
  }

  private void setUpFileSaver() {
    FileSaver save = new FileSaver();
    pane.getChildren().add(save.getButton());
    pane.setConstraints(save.getButton(), 4, 0);
    save.setFile("Test");
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
    Button step = createButton(myResources.getString("Step"), 0, 5);
    Button reset = createButton(myResources.getString("Reset"), 1, 5);
    Button go = createButton(myResources.getString("Go/Pause"),2, 5);
    Button clear = createButton(myResources.getString("Clear"), 3, 5);
    Button random = createButton(myResources.getString("Random"), 4, 5);

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().addAll(step, reset, go, clear, random);
  }

  public Button createButton(String word, int x, int y){
    Button newButton = new Button(word);
    newButton.setId("Button");
    newButton.setOnAction(e -> saveCommand(newButton.getText()));
    pane.setConstraints(newButton, x, y);
    return newButton;
  }

  public void saveCommand(String string){
    request = string;
  }

  public void setUpSliderContainer(){
    SliderContainer container = new SliderContainer(0, 50, 25, 5, myResources.getString("SliderCaption"));
    pane.getChildren().add(container.getContainer());
    pane.setConstraints(container.getContainer(), 3, 4, 2, 1);
  }
}
