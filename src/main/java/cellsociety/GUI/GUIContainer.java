package cellsociety.GUI;

import cellsociety.GUI.Grids.RectangleGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
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

  private static SliderContainer slider;
  private FileUploader uploader;

  private double animationSpeed;
  private ResourceBundle myResources;
  private static String GUI_CSS= "stylesheets/GUIContainer.css";

  public static final int GRID_SIZE = 270;

  public static final String INTERNAL_CONFIGURATION = "cellsociety.";

  private static boolean sliderChanged = false;

  private static boolean requestChanged = false;


  public GUIContainer(Stage primaryStage, String language) {
    mainStage = primaryStage;
    pane = new GridPane();
    setColumnConstraints();

    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + language);
//    pane.setGridLinesVisible(true);
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

    pane.setMaxSize(stageScene.getWidth()-50, stageScene.getHeight()-50);
    mainStage.setScene(stageScene);
    stageScene.getStylesheets().add(GUI_CSS);
    mainStage.show();
  }

  private void setColumnConstraints() {
    int[] widths = {16, 16, 16, 21, 21};
    for (int i = 0; i < 4; i++) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(widths[i]);
      column.setHgrow(Priority.ALWAYS);
      pane.getColumnConstraints().add(column);
    }
  }

  private void setUpGrid() {
    RectangleGrid grid = new RectangleGrid(10,10, GRID_SIZE);
    pane.getChildren().add(grid.getGridLayout());
    pane.setConstraints(grid.getGridLayout(), 0, 0, 3,4);
  }

  private void setUpDropDown(List<String> FileNames) {
    DropDown drop = new DropDown(FileNames, myResources.getString("DropButton"));
    pane.getChildren().add(drop.getContainer());
    pane.setConstraints(drop.getContainer(), 3,1, 2, 1);
  }

  private void setUpFileSaver() {
    FileSaver save = new FileSaver(myResources.getString("Save"));
    pane.getChildren().add(save.getButton());
    pane.setConstraints(save.getButton(), 4, 0);
    save.setFile("Test");
  }

  private void setUpFileUploader() {
    FileUploader upload = new FileUploader(myResources.getString("Upload"));
    pane.getChildren().add(upload.getButton());
    pane.setConstraints(upload.getButton(), 3,0);
  }

  private void SetUpDescriptionBox() {
    TextArea description = new TextArea();
    description.setId("TextBox");
    VBox descriptionContainer = new VBox();
    descriptionContainer.getChildren().add(description);
//    descriptionContainer.setVgrow(description, Priority.ALWAYS);
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
    requestChanged = true;
  }

  public void setUpSliderContainer(){
    slider = new SliderContainer(0, 4, 1, 1, myResources.getString("SliderCaption"));
    pane.getChildren().add(slider.getContainer());
    pane.setConstraints(slider.getContainer(), 3, 4, 2, 1);
  }

  public void updateSliderValue(){
    if(animationSpeed!= slider.getValue()){
      animationSpeed = slider.getValue();
      sliderChanged = true;
    }
  }

  public void asyncUpdate(){
    updateSliderValue();
  }

  public boolean getSpeedChanged() {
    boolean holder = sliderChanged;
    sliderChanged = false;
    return holder;
  }

  public double getAnimationSpeed(){
    return animationSpeed;
  }

  public static boolean isRequestChanged() {
    boolean holder = requestChanged;
    requestChanged = false;
    return holder;
  }

  public String getRequest() {
    return request;
  }
}
