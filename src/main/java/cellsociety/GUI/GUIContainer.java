package cellsociety.GUI;

import cellsociety.Config;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.SimulationController;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
 * @author Han Zhang
 */
public class GUIContainer {

  public final int[] COLUMN_PERCENT = {16,16,16,21,21};

  private final GridPane pane;

  private String request;

  private DropDown drop;
  private SliderContainer slider;
  private FileUploader uploader;
  private double animationSpeed;
  private VisualGrid grid;
  private final ResourceBundle myResources;
  public final static String GUI_CSS = "stylesheets/GUIContainer.css";

  public static final int GRID_SIZE = 300;

  public final static String INTERNAL_CONFIGURATION = "cellsociety.";
  private boolean sliderChanged = false;
  private boolean requestChanged = false;

  public final static int WINDOW_WIDTH = 1000;
  public final static int WINDOW_HEIGHT = 700;

  public final static int DEFAULT_GRID_SIZE = 20;


  public GUIContainer(Stage primaryStage, String language, Config config, SimulationController simulationEngine) {
    pane = new GridPane();
    setColumnConstraints();

    Scene stageScene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + language);
    pane.setGridLinesVisible(true);
    pane.setId("pane");

    setUpButtons(simulationEngine);
    setUpSliderContainer();
    SetUpDescriptionBox();

    setUpFileUploader(config);
    setUpFileSaver();
    setUpGrid(stageScene);

    List<String> DirectoryNames = new ArrayList<>();
    List<String> FileNames = new ArrayList<>();

    //TODO figure out a way to refactor the multiple .adds and create a proper Directory Name

    DirectoryNames.add("data/GameOfLife");
    DirectoryNames.add("data/SpreadingFire");
    DirectoryNames.add("data/Schelling");
    DirectoryNames.add("data/Wa-Tor");

    extractFileNames(DirectoryNames, FileNames);
    setUpDropDown(FileNames);

    pane.setMaxSize(stageScene.getWidth(), stageScene.getHeight());
    primaryStage.setScene(stageScene);
    stageScene.getStylesheets().add(GUI_CSS);
    primaryStage.show();
  }

  private void extractFileNames(List<String> DirectoryNames, List<String> FileNames) {
    for(String dirc: DirectoryNames) {
      File dir = new File(dirc);
      List<String> list = Arrays.asList(Objects.requireNonNull(dir.list(
          (dir1, name) -> name.endsWith(".xml")
      )));
      for(String name:list){
        name = dirc + "/" + name;
        System.out.println(name);
      }
      FileNames.addAll(list);
    }
  }

  private void setColumnConstraints() {
    for (int j : COLUMN_PERCENT) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(j);
      column.setHgrow(Priority.ALWAYS);
      pane.getColumnConstraints().add(column);
    }
  }

  private void setUpGrid(Scene scene) {
    grid = new RectangleVisualGrid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE, GRID_SIZE, scene);
//    grid.updateGrid(5,5);
    pane.getChildren().add(grid.getGridLayout());
    GridPane.setConstraints(grid.getGridLayout(), 0, 0, 3, 4);
  }

  private void setUpDropDown(List<String> FileNames) {
    drop = new DropDown(FileNames, myResources.getString("DropButton"));
    drop.getButton().setOnAction(e -> saveCommand(drop.getButton().getText()));
    pane.getChildren().add(drop.getContainer());
    GridPane.setConstraints(drop.getContainer(), 3, 1, 2, 1);
  }

  private void setUpFileSaver() {
    FileSaver save = new FileSaver(myResources.getString("Save"), grid);
    pane.getChildren().add(save.getButton());
    GridPane.setConstraints(save.getButton(), 4, 0);
    save.setFile("Test");
  }

  private void setUpFileUploader(Config config) {
    uploader = new FileUploader(myResources.getString("Upload"), config);
    pane.getChildren().add(uploader.getButton());
    GridPane.setConstraints(uploader.getButton(), 3, 0);
  }

  private void SetUpDescriptionBox() {
    TextArea description = new TextArea();
    description.setId("TextBox");
    VBox descriptionContainer = new VBox();
    descriptionContainer.getChildren().add(description);
//    descriptionContainer.setVgrow(description, Priority.ALWAYS);
    pane.getChildren().add(descriptionContainer);
    GridPane.setConstraints(descriptionContainer, 3, 3, 2, 1);
  }

  private void setUpButtons(SimulationController simulationEngine) {
    List<String> commands = new ArrayList<>();
    //TODO move all the commands into runnable methods in SimulationEngine
    commands.add(myResources.getString("Step"));
    commands.add(myResources.getString("Reset"));
    commands.add(myResources.getString("Go/Pause"));
    commands.add(myResources.getString("Clear"));
    commands.add(myResources.getString("Random"));

    ButtonContainer buttons = new ButtonContainer(commands, simulationEngine);

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().add(buttons.getContainer());
    GridPane.setConstraints(buttons.getContainer(), 0,5, 5, 1);
  }

  public void saveCommand(String string) {
    request = string;
    requestChanged = true;
  }

  public void setUpSliderContainer() {
    slider = new SliderContainer(0, 4, 1, 1, myResources.getString("SliderCaption"));
    pane.getChildren().add(slider.getContainer());
    GridPane.setConstraints(slider.getContainer(), 3, 4, 2, 1);
  }

  public void updateSliderValue() {
    if (animationSpeed != slider.getValue()) {
      animationSpeed = slider.getValue();
      sliderChanged = true;
    }
  }

  public void asyncUpdate() {
    updateSliderValue();
  }

  public boolean getSpeedChanged() {
    boolean holder = sliderChanged;
    sliderChanged = false;
    return holder;
  }

  public double getAnimationSpeed() {
    return animationSpeed;
  }

  public boolean isRequestChanged() {
    boolean holder = requestChanged;
    requestChanged = false;
    return holder;
  }

  public String getRequest() {
    return request;
  }

  public File getFile() {
    return uploader.getUploaded();
  }

  public VisualGrid getGrid() {
    return grid;
  }
  public String getDropDownSelection(){
    return drop.getValue();
  }
}
