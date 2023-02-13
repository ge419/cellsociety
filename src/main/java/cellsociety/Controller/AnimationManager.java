package cellsociety.Controller;

/**
 * @Author Han Zhang
 */

public class AnimationManager implements AnimationInterface{
  private int frameNum;
  private double multiplier;
  private boolean pause;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public AnimationManager() {
    frameNum = 0;
    multiplier = 0;
    pause = false;
  }
  @Override
  public void stepAnimation() {
    frameNum += FRAMES_PER_SECOND*multiplier;
  }

  public boolean isNewFrame(){
    return (frameNum >= FRAMES_PER_SECOND*multiplier && !pause);
  }

  @Override
  public void incrementFrame() {
    frameNum++;
  }

  @Override
  public void pauseToggle() {
    pause = !pause;
  }
  @Override
  public void setAnimationSpeed(double value) {
    multiplier = value;
  }
}
