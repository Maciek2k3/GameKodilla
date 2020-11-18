import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RankListController {
    @FXML
    private TextArea RankArea;
    @FXML
    Main main = new Main();

    @FXML
    private void Exit() {
        Platform.exit();
    }

    public void initialize() throws IOException {
        try {
            Scanner s = new Scanner(new File("src/main/resources/files/rankSort.txt"));//.useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) {
                    RankArea.appendText(s.nextInt() + " " + "");
                } else {
                    RankArea.appendText(s.next() + "      " + "\n");
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void Reset() throws Exception {
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }


}

