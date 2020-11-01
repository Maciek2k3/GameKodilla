import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

import java.util.*;



public class GameController {
    @FXML
    private Label lbUser;

    @FXML
    private Label lbPlay;

    @FXML
    private TextField interfLb;
    @FXML
    private TextField computerChooseLb;

    @FXML
    private TextField userResultLb;

    @FXML
    private TextField computerResultLb;

    @FXML
    private TextField finalWiner;

    @FXML
    private Label currentResult;

    private GameParams gameParams;
    @FXML
    private Options userChoose = null;

    boolean validate = true;
    Main main = new Main();
    List<Integer> userCurrentResult = new ArrayList<>();
    List<Integer> computerCurrentResult = new ArrayList<>();

    public void setGameParams(GameParams gameParams) {
        this.gameParams = gameParams;
    }


    public void prepareGameScreen() {
        System.out.println("Start");
        System.out.println(this.gameParams);
        System.out.println(gameParams.getUsername());
        //costam poustawiac na podstawie danych z poprzedniego ekranu
        lbUser.setText(gameParams.getUsername().toUpperCase());
        lbPlay.setText(String.valueOf(gameParams.getGameRounds()));
    }

    public void playerPlayScizors() {
        interfLb.setText("Scizorrs");
        userChoose = Options.scissor;
    }

    public void playerPlayPaper() {
        interfLb.setText("Paper");
        userChoose = Options.paper;
    }

    public void playerPlayRock() {
        interfLb.setText("Rock");
        userChoose = Options.rock;
    }

    public void playerPlayBlizard() {
        interfLb.setText("Blizard");
        userChoose = Options.blizard;
    }

    public void playerPlaySpock() {
        interfLb.setText("Spock");
        userChoose = Options.spock;
    }
    Options comResul;

    public void computerPlay() {
        Options[] values = Options.values();
        int length = values.length;
        int randInd = new Random().nextInt(length);
        System.out.println(values);
        System.out.println(randInd);
        comResul= values[randInd];
    }

    public void reset() {
        userCurrentResult.clear();
        computerCurrentResult.clear();
        userResultLb.setText("0");
        computerResultLb.setText("0");
    }

    @FXML
    public void endGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EndGame.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    FirstWindowController firstWindowController = new FirstWindowController();

    @FXML
    private void newRound() throws Exception {
        Stage primaryStage = new Stage();
        main.start(primaryStage);

    }

    public void setComputerPlay() {
        if (validate) {
            //Options computerChoose =
            computerPlay();
            computerChooseLb.setText(String.valueOf(comResul));
            if (userChoose == Options.scissor) {
                if (comResul == Options.scissor) {
                    userCurrentResult.add(1);
                    computerCurrentResult.add(1);
                    currentResult.setText("Draw");
                    currentResult.setTextFill(Color.BLUE);
                } else if (comResul == Options.paper || comResul == Options.blizard) {
                    userCurrentResult.add(1);
                    currentResult.setText(gameParams.getUsername()+ " Win");
                    currentResult.setTextFill(Color.GREEN);
                } else {
                    computerCurrentResult.add(1);
                    currentResult.setText("Computer Win");
                    currentResult.setTextFill(Color.RED);
                }
            } else if (userChoose == Options.paper) {
                if (comResul == Options.paper) {
                    userCurrentResult.add(1);
                    computerCurrentResult.add(1);
                    currentResult.setText("Draw");
                    currentResult.setTextFill(Color.BLUE);
                } else if (comResul == Options.rock || comResul == Options.spock) {
                    userCurrentResult.add(1);
                    currentResult.setText(gameParams.getUsername()+ " Win");
                    currentResult.setTextFill(Color.GREEN);
                } else {
                    computerCurrentResult.add(1);
                    currentResult.setText("Computer Win");
                    currentResult.setTextFill(Color.RED);
                }

            } else if (userChoose == Options.rock) {
                if (comResul == Options.rock) {
                    userCurrentResult.add(1);
                    computerCurrentResult.add(1);
                    currentResult.setText("Draw");
                    currentResult.setTextFill(Color.BLUE);
                } else if (comResul == Options.scissor || comResul == Options.blizard) {
                    userCurrentResult.add(1);
                    currentResult.setText(gameParams.getUsername()+ " Win");
                    currentResult.setTextFill(Color.GREEN);
                } else {
                    computerCurrentResult.add(1);
                    currentResult.setText("Computer Win");
                    currentResult.setTextFill(Color.RED);
                }
            } else if (userChoose == Options.blizard) {
                if (comResul == Options.blizard) {
                    userCurrentResult.add(1);
                    computerCurrentResult.add(1);
                    currentResult.setText("Draw");
                    currentResult.setTextFill(Color.BLUE);
                } else if (comResul == Options.paper || comResul == Options.scissor) {
                    userCurrentResult.add(1);
                    currentResult.setText(gameParams.getUsername()+ " Win");
                    currentResult.setTextFill(Color.GREEN);
                } else {
                    computerCurrentResult.add(1);
                    currentResult.setText("Computer Win");
                    currentResult.setTextFill(Color.RED);
                }
            } else if (userChoose == Options.spock) {
                if (comResul == Options.spock) {
                    computerCurrentResult.add(1);
                    userCurrentResult.add(1);
                    currentResult.setText("Draw");
                    currentResult.setTextFill(Color.BLUE);
                } else if (comResul == Options.scissor || comResul == Options.rock) {
                    userCurrentResult.add(1);
                    currentResult.setText(gameParams.getUsername()+ " Win");
                    currentResult.setTextFill(Color.GREEN);
                } else {
                    computerCurrentResult.add(1);
                    currentResult.setText("Computer Win");
                    currentResult.setTextFill(Color.RED);
                }
            }

            System.out.println(userCurrentResult.size());
            System.out.println(computerCurrentResult.size());

            userResultLb.setText(String.valueOf(userCurrentResult.size()));
            computerResultLb.setText(String.valueOf(computerCurrentResult.size()));

            if (userCurrentResult.size() == gameParams.getGameRounds()) {
                finalWiner.setText(gameParams.getUsername() + " WIN");
                validate = false;
            } else if (computerCurrentResult.size() == gameParams.getGameRounds()) {
                finalWiner.setText("Computer Wins");
                validate = false;
            } else if (computerCurrentResult.size() == gameParams.getGameRounds() && userCurrentResult.size() == gameParams.getGameRounds())
                finalWiner.setText("Semi");
        }
    }
}


