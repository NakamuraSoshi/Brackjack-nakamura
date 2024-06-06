package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.loginException;
import model.UserScore;

public class ScoreDao extends BaseDao {
    public ScoreDao() throws loginException {
        super();
    }

    // ユーザーのスコア情報を取得するgetUserScoreメソッド
    public UserScore getUserScore(String userId) throws SQLException {
        UserScore userScore = null;
        String sql = "SELECT s.user_id, s.matches, s.wins, s.losses, s.draws, u.user_name " +
                     "FROM scores s " +
                     "JOIN user u ON s.user_id = u.user_id " +
                     "WHERE s.user_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int matches = rs.getInt("matches");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                String userName = rs.getString("user_name");

                userScore = new UserScore(userId, userName, matches, wins, losses, draws);
            }
        }

        return userScore;
    }

    // 上位5名のユーザーのスコア情報を取得するgetTopUsersメソッド
    public List<UserScore> getTopUsers() throws SQLException {
        List<UserScore> topUsers = new ArrayList<>();
        String sql = "SELECT s.user_id, s.matches, s.wins, s.losses, s.draws, u.user_name " +
                     "FROM scores s " +
                     "JOIN user u ON s.user_id = u.user_id " +
                     "ORDER BY s.wins DESC LIMIT 5";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                int matches = rs.getInt("matches");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                String userName = rs.getString("user_name");

                UserScore userScore = new UserScore(userId, userName, matches, wins, losses, draws);
                topUsers.add(userScore);
            }
        }

        return topUsers;
    }
}
