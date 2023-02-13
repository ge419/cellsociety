package cellsociety.Controller;

public interface AnimationInterface {
 void stepAnimation();
  void pauseToggle();
  void setAnimationSpeed(double multiplier);
  boolean isNewFrame();

  void incrementFrame();
}
