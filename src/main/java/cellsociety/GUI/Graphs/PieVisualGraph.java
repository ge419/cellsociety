package cellsociety.GUI.Graphs;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGraphInterface;
import cellsociety.Grid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PieVisualGraph implements VisualGraphInterface {

  public static final int CHART_SIZE = 500;
  private final Grid grid;
  private final Stage graphStage;

  private PieChart chart;
  private final Group root;


  /**
   * https://docs.oracle.com/javafx/2/charts/pie-chart.htm#:~:text=To%20create%20a%20pie%20chart,slices%20you%20want%20to%20appear.
   * @param dataGrid Input grid to update data
   */
  public PieVisualGraph(Grid dataGrid){
    graphStage = new Stage();
    grid = dataGrid;
    root = new Group();
    updateGraph();
    Scene stageScene = new Scene(root, CHART_SIZE, CHART_SIZE);
    graphStage.setScene(stageScene);
  }

  /**
   * This creates the PieChart Data and initializes the PieChart using this data
   * https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
   * This is for the Override function that was found.
   * https://stackoverflow.com/questions/35479375/display-additional-values-in-pie-chart
   */
  public void updateGraph() {
    ObservableList<Data> list = readData();
    root.getChildren().clear();
    chart = new PieChart(list) {
      @Override
      protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        if (getLabelsVisible()) {
          getData().forEach(d -> {
            Optional<Node> opTextNode = chart.lookupAll(".chart-pie-label").stream().filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName())).findAny();
            opTextNode.ifPresent(
                node -> ((Text) node).setText(d.getName() + " " + d.getPieValue()));
          });
        }
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
      }
    };

    root.getChildren().add(chart);
  }

  private ObservableList<Data> readData() {
    HashMap<String, Integer> values = new HashMap<>();
    for (List<Cell> cells : grid.getGrid()) {
      for (Cell cell : cells) {
        values.merge(cell.getStatus(), 1, Integer::sum);
      }
    }
    ObservableList<Data> list = FXCollections.observableArrayList();
    for (String state: values.keySet()){
      list.add(new Data(state, values.get(state)));
    }
    return list;
  }

  public void showGraph(){
    graphStage.show();
  }
}
