package cellsociety.Controller;

/**
 * @Author Han Zhang
 */

public interface SimulationController {

 /**
  * Is used to stepAnimation, attached to the Step button on the GUI interface
  */
 void stepAnimation();

 /**
  * Paused the animation loop inside of GameLoopManager, freezing everything
  */
  void pauseToggle();

 /**
  * Sets the animation speed to determine how fast the gameloop updates a frame
  * @param multiplier, double that scales with game speed
  */
  void setAnimationSpeed(double multiplier);

 /**
  * Determines if a new frame should be implented inside the step function
  * @return a boolean to determine if a new frame should be pushed
  */
  boolean isNewFrame();

 /**
  * Increase FrameNum by 1
  */
 void incrementFrame();

 /**
  * Reset FrameNum to 0
  */
  void resetFrameNum();

 /**
  * Checks if the simulation is paused
  * @return a boolean that says if the simulation is paused or not
  */
  boolean isPaused();

 /**
  * is a boolean to see if the simulation should be stepped or not
  * @return
  */
  boolean isStep();

 /**
  * Sets the step boolean to be false for the boolean flag inside of GameLoopManager
  */
 void setStepFalse();

 /**
  * Sets the boolean of newFile to be based on the input state
  * @param state What the caller desires the newFile boolean to be
  */
  void setNewFile(boolean state);

 /**
  * Returns the value of newFIle boolean
  * @return newFile
  */
  boolean isNewFile();
}
