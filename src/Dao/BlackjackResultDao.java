package Dao;

import java.sql.SQLException;

import exception.loginException;

public class BlackjackResultDao extends BaseDao {

    public BlackjackResultDao() throws loginException {
        super();
    }

    //ユーザーの結果を更新するメソッドupdateBlackjackResult
    public void updateBlackjackResult(String string, boolean isWin, boolean isDraw) throws loginException {

    	//スコアテーブルから指定したuser_idのレコードを取得するクエリ
        String selectQuery = "SELECT * FROM scores WHERE user_id = ?";
        //既存のレコードを更新するクエリ
        String updateQuery = "UPDATE scores SET matches = ?, wins = ?, losses = ?, draws = ? WHERE user_id = ?";
        //新しいレコードを挿入するクエリ
        String insertQuery = "INSERT INTO scores (user_id, matches, wins, losses, draws) VALUES (?, ?, ?, ?, ?)";

        try {
            // SELECTクエリの実行
            ps = con.prepareStatement(selectQuery);
            ps.setString(1, string);
            rs = ps.executeQuery();

            if (rs.next()) {
                // データが存在する場合はUPDATEクエリの実行
            	//現在の試合数に１を足し、それぞれ結果次第で１か０を足す
                int matches = rs.getInt("matches") + 1;
                int wins = rs.getInt("wins") + (isWin ? 1 : 0);
                int losses = rs.getInt("losses") + (!isWin && !isDraw ? 1 : 0);
                int draws = rs.getInt("draws") + (isDraw ? 1 : 0);

                //スコアを更新
                ps = con.prepareStatement(updateQuery);
                ps.setInt(1, matches);
                ps.setInt(2, wins);
                ps.setInt(3, losses);
                ps.setInt(4, draws);
                ps.setString(5, string);
                ps.executeUpdate();

            } else {
                // データが存在しない場合はINSERTクエリの実行
            	//試合数は１,他は結果次第
                ps = con.prepareStatement(insertQuery);
                ps.setString(1, string);
                ps.setInt(2, 1);
                ps.setInt(3, isWin ? 1 : 0);
                ps.setInt(4, !isWin && !isDraw ? 1 : 0);
                ps.setInt(5, isDraw ? 1 : 0);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new loginException("SQL実行中に例外が発生しました");
        } finally {
            // リソースのクローズ
            try {
                close();
            } catch (loginException e) {
                e.printStackTrace();
                throw new loginException("リソースのクローズ中に例外が発生しました");
            }
        }
    }
}

