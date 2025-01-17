package cellsociety.GUI;

import cellsociety.ConfigInterface;
import cellsociety.Controller.SimulationController;
import cellsociety.Engine.EngineInterface;
import cellsociety.GUI.ButtonContainers.GameButtonContainer;
import cellsociety.GUI.ButtonContainers.GraphButtons;
import cellsociety.GUI.ButtonContainers.ParameterButtons;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author Han Zhang
 */
public class GUIContainer {
  public final int[] COLUMN_PERCENT = {16,16,16,21,21};

  private final GridPane pane;
  private final ResourceBundle myResources;
  public final static String GUI_CSS = "stylesheets/GUIContainer.css";

  public final static String INTERNAL_CONFIGURATION = "cellsociety.";

  public final static int WINDOW_WIDTH = 1300;
  public final static int WINDOW_HEIGHT = 700;

  //TODO say that decided to use this deisng choice, should be css, but can't figure out how to do it
  public final static int GRID_COLUMN = 0;
  public final static int GRID_ROW = 1;
  public final static int GRID_COLUMN_SPAN = 3;
  public final static int GRID_ROW_SPAN = 4;

  public final static int DROP_DOWN_COLUMN= 3;
  public final static int DROP_DOWN_ROW = 3;
  public final static int DROP_DOWN_COLUMN_SPAN = 2;
  public final static int DROP_DOWN_ROW_SPAN = 1;

  public final static int FILES_COLUMN = 3;
  public final static int FILES_ROW= 1;
  public final static int FILES_COLUMN_SPAN = 2;
  public final static int FILES_ROW_SPAN = 1;

  public final static int DESCRIPTION_BOX_COLUMN = 3;
  public final static int DESCRIPTION_BOX_ROW = 4;
  public final static int DESCRIPTION_BOX_COLUMN_SPAN = 2;
  public final static int DESCRIPTION_BOX_ROW_SPAN = 1;

  public final static int BUTTONS_COLUMN = 0;
  public final static int BUTTONS_ROW = 6;
  public final static int BUTTONS_COLUMN_SPAN = 5;
  public final static int BUTTONS_ROW_SPAN = 1;

  public final static int SLIDER_COLUMN = 3;
  public final static int SLIDER_ROW = 5;

  public final static int SLIDER_COLUMN_SPAN = 2;
  public final static int SLIDER_ROW_SPAN = 1;

  public final static int PARAM_COLUMN = 0;
  public final static int PARAM_ROW = 0;
  public final static int PARAM_COLUMN_SPAN = 5;
  public final static int PARAM_ROW_SPAN = 1;

  public final static int GRAPH_COLUMN = 0;
  public final static int GRAPH_ROW = 5;
  public static final String CELL_COLOR = "stylesheets/CellColor.css";
  public GUIContainer(Stage primaryStage, String language, ConfigInterface config, EngineInterface simulationEngine, SimulationController controller, VisualGrid grid, VisualGraphInterface graph) {
    pane = new GridPane();
    setColumnConstraints();
    Scene stageScene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

    myResources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION + language);
    primaryStage.setTitle(myResources.getString("GameWindowTitle"));
    pane.setId("pane");

    setUpButtons(simulationEngine, controller, myResources);
    setUpSliderContainer(controller);
    SetUpDescriptionBox(config);

    setUpFilesButtons(config, controller, simulationEngine);
    setUpGrid(grid);

    setUpDropDownContainer(config, controller);
    setUpParamButtons(simulationEngine);

    setupGraphButtons(myResources, graph);

    pane.setMaxSize(stageScene.getWidth(), stageScene.getHeight());
    primaryStage.setScene(stageScene);
    stageScene.getStylesheets().add(GUI_CSS);
    stageScene.getStylesheets().add(CELL_COLOR);
    primaryStage.show();
  }

  private void setUpDropDownContainer(ConfigInterface config, SimulationController controller) {
    List<String> DirectoryNames = new ArrayList<>();
    List<String> FileNames = new ArrayList<>();
    DirectoryNames.add("data/Preloaded_Files");
    extractFileNames(DirectoryNames, FileNames);
    setUpDropDown(FileNames, config, controller);
  }

  /**
   *
   * @param DirectoryNames This was a list that contained the number of files, in case there are multiple Directories that need to be check
   * @param FileNames List of File Names to be added to the dropDown Files
   */
  private void extractFileNames(List<String> DirectoryNames, List<String> FileNames) {
    for(String dirc: DirectoryNames) {
      File dir = new File(dirc);
      List<String> list = Arrays.asList(Objects.requireNonNull(dir.list(
          (dir1, name) -> name.endsWith(".xml")
      )));
      FileNames.addAll(list);
    }
  }

  /**
   * Sets up the Width of each Column in terms of Column percents
   */
  private void setColumnConstraints() {
    for (int j : COLUMN_PERCENT) {
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(j);
      column.setHgrow(Priority.ALWAYS);
      pane.getColumnConstraints().add(column);
    }
  }
  private void setupGraphButtons(ResourceBundle bundle, VisualGraphInterface graph){
    GraphButtons graphButtons = new GraphButtons(bundle, graph);
    pane.getChildren().add(graphButtons.getContainer());
    GridPane.setConstraints(graphButtons.getContainer(), GRAPH_COLUMN,GRAPH_ROW);
  }

  private void setUpGrid(VisualGrid grid) {
    pane.getChildren().add(grid.getGridLayout());
    GridPane.setConstraints(grid.getGridLayout(), GRID_COLUMN, GRID_ROW, GRID_COLUMN_SPAN, GRID_ROW_SPAN);
  }

  private void setUpParamButtons(EngineInterface engine){
    ParameterButtons parameters = new ParameterButtons(engine);
    pane.getChildren().add(parameters.getContainer());
    GridPane.setConstraints(parameters.getContainer(), PARAM_COLUMN, PARAM_ROW, PARAM_COLUMN_SPAN, PARAM_ROW_SPAN);
  }
  private void setUpDropDown(List<String> FileNames, ConfigInterface config, SimulationController controller) {
    DropDown drop = new DropDown(FileNames, myResources.getString("DropButton"), config, controller);
    pane.getChildren().add(drop.getContainer());
    GridPane.setConstraints(drop.getContainer(), DROP_DOWN_COLUMN, DROP_DOWN_ROW, DROP_DOWN_COLUMN_SPAN, DROP_DOWN_ROW_SPAN);
  }

  private void setUpFilesButtons(ConfigInterface config, SimulationController controller, EngineInterface simulationEngine) {
    FileSaver save = new FileSaver(myResources.getString("Save"), config, simulationEngine);
    FileUploader uploader = new FileUploader(myResources.getString("Upload"), config, controller);
    FileButtonContainer container = new FileButtonContainer(save, uploader);
    pane.getChildren().add(container.getContainer());
    GridPane.setConstraints(container.getContainer(), FILES_COLUMN, FILES_ROW, FILES_COLUMN_SPAN, FILES_ROW_SPAN);
  }


  private void SetUpDescriptionBox(ConfigInterface config) {
    DescriptionBox TextBox = new DescriptionBox(config);
    pane.getChildren().add(TextBox.getDescriptionContainer());
    GridPane.setConstraints(TextBox.getDescriptionContainer(), DESCRIPTION_BOX_COLUMN, DESCRIPTION_BOX_ROW, DESCRIPTION_BOX_COLUMN_SPAN, DESCRIPTION_BOX_ROW_SPAN);
  }

  private void setUpButtons(EngineInterface simulationEngine, SimulationController controller, ResourceBundle bundle) {
    GameButtonContainer buttons = new GameButtonContainer(simulationEngine, controller, bundle);
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
    pane.getChildren().add(buttons.getContainer());
    GridPane.setConstraints(buttons.getContainer(), BUTTONS_COLUMN,BUTTONS_ROW, BUTTONS_COLUMN_SPAN, BUTTONS_ROW_SPAN);
  }

  public void setUpSliderContainer(SimulationController animation) {
    SliderContainer slider = new SliderContainer(myResources.getString("SliderCaption"), animation);
    pane.getChildren().add(slider.getContainer());
    GridPane.setConstraints(slider.getContainer(), SLIDER_COLUMN, SLIDER_ROW, SLIDER_COLUMN_SPAN, SLIDER_ROW_SPAN);
  }
}
