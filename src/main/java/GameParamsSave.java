public class GameParamsSave {
    private int userScore;
    private int comScore;

    public GameParamsSave(int userScore, int comScore) {
        this.userScore = userScore;
        this.comScore = comScore;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getComScore() {
        return comScore;
    }
}
