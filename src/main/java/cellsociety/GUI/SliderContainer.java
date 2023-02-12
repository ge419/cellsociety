package cellsociety.GUI;

import cellsociety.Controller.AnimationInterface;
import cellsociety.Controller.AnimationManager;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Han Zhang
 */

public class SliderContainer {

  VBox container;
  Slider slider;
  Text text;
  public static final int MIN = 0;
  public static final int MAX = 4;
  public static final int CURRENT = 1;

  public static final int INCREMENT = 1;
  public SliderContainer(String caption, AnimationInterface animation){

    sliderInit(animation);
    setText(caption);
    container = new VBox(slider, text);
    container.setId("Container-Vbox");
  }

  private void sliderInit(AnimationInterface animation) {
    slider = new Slider();
    slider.setMin(MIN);
    slider.setMax(MAX);
    slider.setValue(CURRENT);
    slider.setBlockIncrement(INCREMENT);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
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
