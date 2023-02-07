package cellsociety;

import cellsociety.GUI.GUIContainer;
import cellsociety.GUI.Grids.RectangleGrid;
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
//    // kind of data files to look for
//    public static final String DATA_FILE_EXTENSION = "*.xml";
//    // default to start in the data folder to make it easy on the user to find
//    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
//    // NOTE: make ONE chooser since generally accepted behavior is that it remembers
//    // where user left it last
//    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
//    // internal configuration file
//    public static final String INTERNAL_CONFIGURATION = "cellsociety.Version";

  GUIContainer container;
  Config config;
  int frameNum;

  double multiplier;

  public boolean pause;

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) {
    frameNum = 1;
    multiplier = 1;
    pause = false;
    String english = "english";
    container = new GUIContainer(primaryStage, english);

    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double secondDelay) {
    timer(multiplier, pause);
    container.asyncUpdate();
    animationSpeedUpdate();
    setUpActionButtons();

    //NOT SURE IF NEED THIS
//    if(container.isFileUploaded()){
//      File inputFile = container.getFile();
//      config.readFile(inputFile);
//    }

    //xml
  }

  private void animationSpeedUpdate() {
    if(container.getSpeedChanged()){
      multiplier = container.getAnimationSpeed();
      if(multiplier != 0){
        multiplier = 1/multiplier;
      }
      else{
        multiplier = Integer.MAX_VALUE;
      }
    }
  }

  private void setUpActionButtons() {
    if(container.isRequestChanged()){
      String request = container.getRequest();
      System.out.println(request);
      if(request.equals("Go/Pause")){
        pause = !pause;
      }
      if(request.equals("Step")){
        frameNum += FRAMES_PER_SECOND * multiplier;
      }
      if(request.equals("Reset")){
        //TODO tell XML Config to reload file
      }
      if(request.equals("Clear")){
        //TODO tell engine to clear
      }
      if(request.equals("Random")){
        //TODO tell engine to random
      }
      if(request.equals("DropButton")){
        //TODO, return selection of file chosen and then take the button out
      }
    }
  }

  private void timer(double multiplier, boolean pause) {
    if (frameNum >= FRAMES_PER_SECOND * multiplier) {
      frameNum = 0;
      //Grid update inside here
      //Game logic
    }
    frameNum++;
    if (pause){
      frameNum = 0;
    }
  }

  /**
   * Start the program, give complete control to JavaFX.
   * <p>
   * Default version of main() is actually included within JavaFX, so this is not technically
   * necessary!
   */
  public static void main(String[] args) {
    launch(args);
  }
}
