package cellsociety.Controller;

/**
 * @Author Han Zhang
 */

public interface AnimationInterface {
 void stepAnimation();
  void pauseToggle();
  void setAnimationSpeed(double multiplier);
  boolean isNewFrame();
  void incrementFrame();
  void resetFrameNum();
  boolean isPaused();
  boolean isStep();
  void setStep();
}
