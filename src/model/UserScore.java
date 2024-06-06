package model;

public class UserScore {
    private String userId;
    private String userName;
    private int matches;
    private int wins;
    private int losses;
    private int draws;

    public UserScore(String userId, String userName, int matches, int wins, int losses, int draws) {
        this.userId = userId;
        this.userName = userName;
        this.matches = matches;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
    	return userName;
    }

    public int getMatches() {
        return matches;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

//matchesが０より大きい場合winsをmatchesで割る
    public double getWinRate() {
        return matches > 0 ? (double) wins / matches : 0;
    }
}

