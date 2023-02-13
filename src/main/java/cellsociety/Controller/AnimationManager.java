package cellsociety.Controller;

/**
 * @Author Han Zhang
 */

public class AnimationManager implements AnimationInterface{
  private int frameNum;
  private double multiplier;
  private boolean pause;

  private boolean step;
  public static final int FRAMES_PER_SECOND = 60;
  public AnimationManager() {
    frameNum = 0;
    multiplier = 0;
    pause = true;
  }
  @Override
  public void stepAnimation() {
    step = true;
  }

  public boolean isNewFrame(){
    return (frameNum >= (FRAMES_PER_SECOND * multiplier)) || step;
  }

  @Override
  public void incrementFrame() {
    frameNum++;
  }

  @Override
  public boolean isPaused() {
    return pause;
  }
  public void resetFrameNum(){
    frameNum = 0;
  }
  @Override
  public void pauseToggle() {
    pause = !pause;
  }

  public void setStep() {
    step = false;
  }
  public boolean isStep(){
    return step;
  }
  @Override
  public void setAnimationSpeed(double value) {
    multiplier = value;
    System.out.println(multiplier*FRAMES_PER_SECOND);
  }
}
