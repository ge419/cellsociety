package cellsociety.GUI;

import cellsociety.Controller.SimulationController;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Han Zhang
 */

public class SliderContainer {
  private final VBox container;
  private Slider slider;
  private Text text;
  public static final int MIN = 0;
  public static final int MAX = 60;
  public static final int CURRENT = 60;

  public static final int INCREMENT = 1;
  public SliderContainer(String caption, SimulationController animation){
    sliderInit(animation);
    setText(caption);
    container = new VBox(slider, text);
    container.setId("Container-Vbox");
  }
  private void sliderInit(SimulationController animation) {
    slider = new Slider();
    slider.setMin(MIN);
    slider.setMax(MAX);
    slider.setValue(CURRENT);
    slider.setBlockIncrement(INCREMENT);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);

    //Taken inspiration from the Ed post by Jason Fitzpatrick on post "Question about magic numbers!" #27
    slider.setOnMouseReleased(e -> animation.setAnimationSpeed(slider.getValue()));
  }
  private void setText(String caption){
    text = new Text(caption);
    text.setId("SliderText");
  }
  public VBox getContainer(){
    return container;
  }
}
