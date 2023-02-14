package cellsociety.Controller;

import cellsociety.GUI.SliderContainer;

/**
 * @Author Han Zhang
 */

public class SimulationManager implements SimulationController {
  private int frameNum;
  private double multiplier;
  private boolean pause;

  private boolean step;
  private boolean newFile;
  public static final double FRAMES_PER_SECOND = 60;
  public static final double STARTING_MULTIPLIER = 1/SliderContainer.CURRENT;

  /**
   * FrameNum is starting out to be Max to increment the starting stage where the file is displayed from a blank state
   */
  public SimulationManager() {
    frameNum = Integer.MAX_VALUE;
    multiplier = STARTING_MULTIPLIER;
    pause = true;
  }
  @Override
  public void stepAnimation() {
    step = true;
  }

  public boolean isNewFrame(){
    return (frameNum >= (FRAMES_PER_SECOND * multiplier)) || step;
  }

  public int getFrameNum(){
    return frameNum;
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

  @Override
  public void setNewFile(boolean state) {
    newFile = state;
  }
  public boolean isNewFile(){
    return newFile;
  }

  @Override
  public int getFrame() {
    return frameNum;
  }

  public boolean isStep(){
    return step;
  }
  @Override
  public void setAnimationSpeed(double value) {
    if(value == 0){
      multiplier = Integer.MAX_VALUE;
      return;
    }
    multiplier = 1 / value;
  }
}
