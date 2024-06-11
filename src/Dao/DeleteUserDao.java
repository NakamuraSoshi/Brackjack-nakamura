package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.loginException;
import model.User;

public class DeleteUserDao extends BaseDao {

    public DeleteUserDao() throws loginException {
        super();
    }

    //deleteUserメソッド宣言
    public void deleteUser(User user) throws SQLException,
    loginException {

        try {

        	//ユーザー情報を削除
        	deleteScore(user.getUserId());

        	deleteChips(user.getUserId());

        	//sqlでuserテーブルから特定のuser_idのレコード削除
        	//sql実行のためのpreparentStatementオブジェpsを作成し、executeUpdateで実行
            String sql = "DELETE FROM user WHERE user_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            int rowsDeleted = ps.executeUpdate();

            //削除した行数が０で失敗
            if (rowsDeleted == 0) {
                throw new loginException("ユーザーの削除に失敗しました");
            }
        } finally {
            close();
        }
    }

    //スコアを削除するメソッド
    private void deleteScore(String userId) throws SQLException,
    loginException{
    	String sql = "DELETE FROM scores WHERE user_id = ?";
    	try (PreparedStatement ps = con.prepareStatement(sql)){
    		ps.setString(1, userId);
    		ps.executeUpdate();
    	}
    }
    // チップを削除するメソッド
	private void deleteChips(String userId) throws SQLException, loginException {
	    String sql = "DELETE FROM chips WHERE user_id = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, userId);
	        ps.executeUpdate();
	    }
	}
}

