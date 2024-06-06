package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.loginException;
import model.User;

//データベースに新しいユーザーを登録する
public class SignupDao extends BaseDao {

    public SignupDao() throws loginException {
        super();
    }

    //新しいユーザーをDBに登録するregisterUserメソッド
    public void registerUser(User user) throws SQLException {
        PreparedStatement ps = null;

        try {
            // SQL文の準備
            String sql = "INSERT INTO user (user_id, user_name, user_password, role) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            // パラメータの設定
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPassword());
            ps.setString(4, user.getRole());

            // SQL文の実行
            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("新しいユーザーが追加されました。");
            } else {
                System.out.println("ユーザーの追加に失敗しました。");
            }
       } finally {
       }

    }
}


