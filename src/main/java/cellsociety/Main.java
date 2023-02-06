package cellsociety;

import cellsociety.GUI.GUIContainer;
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
  int frameNum;

  double multiplier;

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  /**
   * @see Application#start(Stage)
   */
  @Override
  public void start(Stage primaryStage) {
    frameNum = 0;
    multiplier = 1;
    String english = "english";
    container = new GUIContainer(primaryStage, english);
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    animation.play();
  }

  private void step(double secondDelay) {
    timer(frameNum, multiplier);
    container.asyncUpdate();
    if(container.getSpeedChanged()){
      multiplier = container.getAnimationSpeed();
      System.out.println(multiplier);
    }
    if(container.isRequestChanged()){
      String request = container.getRequest();
      System.out.println(request);
    }
    //set conditionals for buttons actions here
    //xml
  }


  private void timer(int frameNum, double multiplier) {
    if (frameNum >= FRAMES_PER_SECOND * multiplier) {
      frameNum = 0;
      //Grid update inside here
      //Game logic
    }
    frameNum++;
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
