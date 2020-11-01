import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private static AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("RPS GAME");
        showMainView();
    }

    public void showMainView() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("FirstWindow.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
