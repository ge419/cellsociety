package cellsociety;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
    // kind of data files to look for
    public static final String DATA_FILE_EXTENSION = "*.xml";
    // default to start in the data folder to make it easy on the user to find
    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers
    // where user left it last
    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    // internal configuration file
    public static final String INTERNAL_CONFIGURATION = "cellsociety.Version";

    /**
     * @see Application#start(Stage)
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println(String.format("Version: %s", getVersion()));
        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        if (dataFile != null) {
            int numBlocks = calculateNumBlocks(dataFile);
            showMessage(AlertType.INFORMATION, String.format("Number of Blocks = %d", numBlocks));
        }
    }

    /**
     * Start the program, give complete control to JavaFX.
     *
     * Default version of main() is actually included within JavaFX, so this is not technically
     * necessary!
     */
    public static void main(String[] args) {
        launch(args);
    }
}
