public class GameParams {

    private String username;
    private int gameRounds;

    public GameParams(String username, int gameRounds) {
        this.username = username;
        this.gameRounds = gameRounds;
    }

    public String getUsername() {
        return username;
    }

    public int getGameRounds() {
        return gameRounds;
    }

    @Override
    public String toString() {
        return "GameParams{" +
                "username='" + username + '\'' +
                ", gameRounds=" + gameRounds +
                '}';
    }
}
