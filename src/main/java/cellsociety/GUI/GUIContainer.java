package cellsociety.GUI;

import cellsociety.Config;
import cellsociety.GUI.Grids.RectangleVisualGrid;
import cellsociety.Controller.AnimationInterface;
import cellsociety.Controller.SimulationController;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.Scene;
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

  private SliderContainer slider;
  private double animationSpeed;
  private VisualGrid grid;
  private final ResourceBundle myResources;
  public final static String GUI_CSS = "stylesheets/GUIContainer.css";

  public final static String INTERNAL_CONFIGURATION = "cellsociety.";
  private boolean sliderChanged = false;

  public final static int WINDOW_WIDTH = 1000;
  public final static int WINDOW_HEIGHT = 700;
  public final static int DEFAULT_GRID_SIZE = 20;

  //TODO say that decided to use this deisng choice, should be css, but can't figure out how to do it
  public final static int GRID_COLUMN = 0;
  public final static int GRID_ROW = 0;
  public final static int GRID_COLUMN_SPAN = 3;
  public final static int GRID_ROW_SPAN = 4;

  public final static int DROP_DOWN_COLUMN= 3;
  public final static int DROP_DOWN_ROW = 2;
  public final static int DROP_DOWN_COLUMN_SPAN = 2;
  public final static int DROP_DOWN_ROW_SPAN = 1;

  public final static int FILE_UPLOADER_COLUMN = 3;
  public final static int FILE_UPLOADER_ROW = 0;
  public final static int FILE_SAVER_COLUMN = 4;
  public final static int FILE_SAVER_ROW = 0;

  public final static int DESCRIPTION_BOX_COLUMN = 3;
  public final static int DESCRIPTION_BOX_ROW = 3;
  public final static int DESCRIPTION_BOX_COLUMN_SPAN = 2;
  public final static int DESCRIPTION_BOX_ROW_SPAN = 1;

  public final static int BUTTONS_COLUMN = 0;
  public final static int BUTTONS_ROW = 5;
  public final static int BUTTONS_COLUMN_SPAN = 5;
  public final static int BUTTONS_ROW_SPAN = 1;

  public final static int SLIDER_COLUMN = 3;
  public final static int SLIDER_ROW = 4;

  public final static int SLIDER_COLUMN_SPAN = 2;
  public final static int SLIDER_ROW_SPAN = 1;

  public GUIContainer(Stage primaryStage, String language, Config config, SimulationController simulationEngine, AnimationInterface controller, GridPane grid) {
    pane = new GridPane();
    setColumnConstraints();

    Scene stageScene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + language);
    pane.setGridLinesVisible(true);
    pane.setId("pane");

    setUpButtons(simulationEngine, controller, myResources);
    setUpSliderContainer();
    SetUpDescriptionBox();

    setUpFileUploader(config);
    setUpFileSaver(config);
    setUpGrid(stageScene);

    List<String> DirectoryNames = new ArrayList<>();
    List<String> FileNames = new ArrayList<>();

    DirectoryNames.add("data/Preloaded_Files");
    extractFileNames(DirectoryNames, FileNames);
    setUpDropDown(FileNames, config);

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
    grid = new RectangleVisualGrid(DEFAULT_GRID_SIZE, DEFAULT_GRID_SIZE);
    pane.getChildren().add(grid.getGridLayout());
    GridPane.setConstraints(grid.getGridLayout(), GRID_COLUMN, GRID_ROW, GRID_COLUMN_SPAN, GRID_ROW_SPAN);
  }

  private void setUpDropDown(List<String> FileNames, Config config) {
    DropDown drop = new DropDown(FileNames, myResources.getString("DropButton"), config);
    pane.getChildren().add(drop.getContainer());
    GridPane.setConstraints(drop.getContainer(), DROP_DOWN_COLUMN, DROP_DOWN_ROW, DROP_DOWN_COLUMN_SPAN, DROP_DOWN_ROW_SPAN);
  }

  private void setUpFileSaver(Config config) {
    FileSaver save = new FileSaver(myResources.getString("Save"), config);
    pane.getChildren().add(save.getButton());
    GridPane.setConstraints(save.getButton(), FILE_SAVER_COLUMN, FILE_SAVER_ROW);
    save.setFile("Test");
  }

  private void setUpFileUploader(Config config) {
    FileUploader uploader = new FileUploader(myResources.getString("Upload"), config);
    pane.getChildren().add(uploader.getButton());
    GridPane.setConstraints(uploader.getButton(), FILE_UPLOADER_COLUMN, FILE_UPLOADER_ROW);
  }

  private void SetUpDescriptionBox() {
    TextArea description = new TextArea();
    description.setId("TextBox");
    VBox descriptionContainer = new VBox();
    descriptionContainer.getChildren().add(description);
    pane.getChildren().add(descriptionContainer);
    GridPane.setConstraints(descriptionContainer, DESCRIPTION_BOX_COLUMN, DESCRIPTION_BOX_ROW, DESCRIPTION_BOX_COLUMN_SPAN, DESCRIPTION_BOX_ROW_SPAN);
  }

  private void setUpButtons(SimulationController simulationEngine, AnimationInterface controller, ResourceBundle bundle) {
    ButtonContainer buttons = new ButtonContainer(simulationEngine, controller, bundle);
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().add(buttons.getContainer());
    GridPane.setConstraints(buttons.getContainer(), BUTTONS_COLUMN,BUTTONS_ROW, BUTTONS_COLUMN_SPAN, BUTTONS_ROW_SPAN);
  }

  public void setUpSliderContainer() {
    slider = new SliderContainer(myResources.getString("SliderCaption"));
    pane.getChildren().add(slider.getContainer());
    GridPane.setConstraints(slider.getContainer(), SLIDER_COLUMN, SLIDER_ROW, SLIDER_COLUMN_SPAN, SLIDER_ROW_SPAN);
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
  public VisualGrid getGrid() {
    return grid;
  }
}
