import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rankSort.SortScores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FirstWindowController {

    @FXML
    private TextField nameLb;
    @FXML
    private TextField roundLb;
    @FXML
    private Label warningLb;

    private String userNameReturn;
    private int userScrore;
    private int compScore;
    private int gameRounds;

    @FXML
    public void showNextWindow(ActionEvent actionEvent) throws IOException {
        boolean validate = true;

        int rounds = 0;
        try {
            rounds = Integer.parseInt(this.roundLb.getText());
        } catch (NumberFormatException e) {
            validate = false;
            warningLb.setText("Wrong game number ");
        }

        String username = nameLb.getText();

        if (username.isEmpty()) {
            validate = false;
            warningLb.setText("Give correct name");
        }


        if (validate) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Game.fxml"));
            AnchorPane pane = loader.load();
            GameController gameController = loader.getController();
            GameParams gameParams = new GameParams(username, rounds);
            gameController.setGameParams(gameParams);
            gameController.prepareGameScreen();
            Scene scene = new Scene(pane);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    public void showRankList(ActionEvent actionEvent) throws IOException {
        FXMLLoader loaderN = new FXMLLoader();
        loaderN.setLocation(getClass().getResource("RankList.fxml"));
        AnchorPane pane = loaderN.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void gameReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        List<String> read = new ArrayList<>();
        Scanner s = new Scanner(new File("src/main/resources/files/gameReturn.txt"));
        while (s.hasNextLine()) {
            read.add(s.nextLine());
        }
        s.close();
        gameRounds = Integer.parseInt(read.get(read.size() - 4));
        userNameReturn = read.get(read.size() - 3);
        userScrore = Integer.parseInt(read.get(read.size() - 2));
        compScore = Integer.parseInt(read.get(read.size() - 1));
        System.out.println(userNameReturn);
        System.out.println(userScrore);
        System.out.println(compScore);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Game.fxml"));
        AnchorPane pane = loader.load();
        GameController gameController = loader.getController();
        GameParams gameParams = new GameParams(userNameReturn, gameRounds);
        GameParamsSave gameParamsSave=new GameParamsSave(userScrore,compScore);
        gameController.setGameParams(gameParams);
        gameController.setGameParamsSave(gameParamsSave);
        gameController.prepareGameSaveScreen();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }


}





