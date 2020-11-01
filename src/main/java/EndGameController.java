import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class EndGameController {
    Main main=new Main();
    @FXML
    private void Reset() throws Exception {
        Stage primaryStage = new Stage();
        main.start(primaryStage);
    }
    @FXML
    private void endGame(){
        Platform.exit();
    }
}
