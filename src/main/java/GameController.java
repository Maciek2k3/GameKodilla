import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.*;

import java.text.SimpleDateFormat;
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

    @FXML
    private CheckBox hardEasy;
    GameParamsSave gameParamsSave;


    boolean validate = true;
    Main main = new Main();
    List<Integer> userCurrentResult = new ArrayList<>();
    List<Integer> computerCurrentResult = new ArrayList<>();

    public void setGameParams(GameParams gameParams) {
        this.gameParams = gameParams;
    }
    public void setGameParamsSave(GameParamsSave gameParamsSave){
        this.gameParamsSave=gameParamsSave;
    }


    public void prepareGameScreen() {
        System.out.println("Start");
        System.out.println(this.gameParams);
        System.out.println(gameParams.getUsername());
        //costam poustawiac na podstawie danych z poprzedniego ekranu
        lbUser.setText(gameParams.getUsername().toUpperCase());
        lbPlay.setText(String.valueOf(gameParams.getGameRounds()));
    }
    public void prepareGameSaveScreen(){
        System.out.println("Comps score=" + gameParamsSave.getComScore());
        System.out.println("User Score=" + gameParamsSave.getUserScore());
        for (int i = 0; i < gameParamsSave.getComScore(); i++) {
            computerCurrentResult.add(1);
        }
        for (int i = 0; i < gameParamsSave.getUserScore(); i++) {
            userCurrentResult.add(1);}

           userResultLb.setText(String.valueOf(userCurrentResult.size()));
            computerResultLb.setText(String.valueOf(computerCurrentResult.size()));
        }
    public void playerPlayScizors() {
        interfLb.setText("scissors");
        userChoose = Options.scissor;
    }

    public void playerPlayPaper() {
        interfLb.setText("paper");
        userChoose = Options.paper;
    }

    public void playerPlayRock() {
        interfLb.setText("rock");
        userChoose = Options.rock;
    }

    public void playerPlayBlizard() {
        interfLb.setText("blizzard");
        userChoose = Options.blizard;
    }

    public void playerPlaySpock() {
        interfLb.setText("spock");
        userChoose = Options.spock;
    }

    Options comResul;

    public void computerPlay() {
        if (hardEasy.isSelected()) {
            easyComputerPlay();
        } else {
            hardComputerPlay();
        }
    }

    public void easyComputerPlay() {
        Options[] values = Options.values();
        int length = values.length;
        int randInd = new Random().nextInt(length);
        //System.out.println(values);
        //System.out.println(randInd);
        comResul = values[randInd];
    }

    public void hardComputerPlay() {
        if (userChoose == Options.scissor) {
            comResul = Options.paper;
        } else if (userChoose == Options.paper) {
            comResul = Options.rock;
        } else if (userChoose == Options.rock) {
            comResul = Options.scissor;
        } else if (userChoose == Options.spock || userChoose == Options.blizard) {
            easyComputerPlay();
        }
    }
        public void gameReturn() throws IOException {
            Writer output = new BufferedWriter(new FileWriter("src/main/resources/files/gameReturn.txt", true));
            output.append(String.valueOf(gameParams.getGameRounds()));
            output.append(System.getProperty("line.separator"));
            output.append(gameParams.getUsername());
            output.append(System.getProperty("line.separator"));
            output.append(String.valueOf(userCurrentResult.size()));
            output.append(System.getProperty("line.separator"));
            output.append(String.valueOf(computerCurrentResult.size()));
            output.close();
            Platform.exit();
        }



    public void gameStatus() throws IOException {
        Writer status = new BufferedWriter(new FileWriter("src/main/resources/files/gameStatus.txt", true));
        status.append(gameParams.getUsername() + ",score:" + userCurrentResult.size() + " VS " + "Computer, score: " + computerCurrentResult.size() + "\r");
        status.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game Status");
        try {
            Scanner scan = new Scanner(new File("src/main/resources/files/gameStatus.txt"));//.useDelimiter("\\s+");
            while (scan.hasNext()) {
                // if (scan.hasNextInt()) {
                //   alert.setContentText(scan.nextInt() + " " + "");
                // } else {
                alert.setContentText(scan.nextLine() + "      " + "\n");
                //}
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        alert.show();
    }

    public void reset() {
        userCurrentResult.clear();
        computerCurrentResult.clear();
        userResultLb.setText(String.valueOf(userCurrentResult.size()));
        computerResultLb.setText(String.valueOf(computerCurrentResult.size()));
        // userResultLb.setText("0");
        // computerResultLb.setText("0");
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


    @FXML
    private void newRound() throws Exception {
        Stage primaryStage = new Stage();
        main.start(primaryStage);

    }

    public void setComputerPlay() throws Exception {
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
                    currentResult.setText(gameParams.getUsername() + " Win");
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
                    currentResult.setText(gameParams.getUsername() + " Win");
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
                    currentResult.setText(gameParams.getUsername() + " Win");
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
                    currentResult.setText(gameParams.getUsername() + " Win");
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
                    currentResult.setText(gameParams.getUsername() + " Win");
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
                rankList();

                validate = false;
            } else if (computerCurrentResult.size() == gameParams.getGameRounds()) {
                finalWiner.setText("Computer Wins");
                validate = false;
            } else if (computerCurrentResult.size() == gameParams.getGameRounds() && userCurrentResult.size() == gameParams.getGameRounds())
                finalWiner.setText("Semi");
        }
    }

    public void rankList() throws IOException {
        Writer output = new BufferedWriter(new FileWriter("src/main/resources/files/rank.txt", true));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        output.append(gameParams.getUsername() + "," + "Date:" + formatter.format(date) + "," + "Score->" + userCurrentResult.size() + "\r");
        output.close();
    }
    }





