package cellsociety.GUI.Graphs;

import cellsociety.Cells.Cell;
import cellsociety.GUI.VisualGraphInterface;
import cellsociety.Grid;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieVisualGraph implements VisualGraphInterface {

  public static final int CHART_SIZE = 300;
  private PieChart chart;
  private Grid grid;
  private Stage graphStage;

  /**
   * https://docs.oracle.com/javafx/2/charts/pie-chart.htm#:~:text=To%20create%20a%20pie%20chart,slices%20you%20want%20to%20appear.
   * @param dataGrid Input grid to update data
   */
  public PieVisualGraph(Grid dataGrid){
    graphStage = new Stage();
    grid = dataGrid;
    updateGraph();
    Scene stageScene = new Scene(chart, CHART_SIZE, CHART_SIZE);
    graphStage.setScene(stageScene);
  }

  /**
   * This creates the PieChart Data and initializes the PieChart using this data
   * https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
   */
  public void updateGraph() {
    HashMap<String, Integer> values = new HashMap<>();
    for (List<Cell> cells : grid.getGrid()) {
      for (Cell cell : cells) {
        values.merge(cell.getStatus(), 1, Integer::sum);
      }
    }
    ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
    for (String state: values.keySet()){
      list.add(new PieChart.Data(state, values.get(state)));
    }
    chart = new PieChart(list);
  }
  public void showGraph(){
    graphStage.show();
  }
}
