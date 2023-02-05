package cellsociety.GUI;

import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @Author Han Zhang
 */

public class SliderContainer {

  VBox container;
  Slider slider;
  Text text;
  public SliderContainer(int min, int max, int current, int increment, String caption){

    sliderInit(min, max, current, increment);
    setText(caption);
    container = new VBox(slider, text);
    container.setId("Container-Vbox");
  }

  private void sliderInit(int min, int max, int current, int increment) {
    slider = new Slider();
    slider.setMin(min);
    slider.setMax(max);
    slider.setValue(current);
    slider.setBlockIncrement(increment);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
  }
  private void setText(String caption){
    text = new Text(caption);
    text.setId("SliderText");
  }
  public VBox getContainer(){
    return container;
  }
}
