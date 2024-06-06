package Dao;

import java.sql.SQLException;

import exception.loginException;
import model.User;

//ログイン機能を提供
public class UserDao extends BaseDao {

    public UserDao() throws loginException {
        super();
    }

    //ユーザーＩＤとパスワードを認証して、認証された情報を返すdoLoginUserメソッド
    public User doLogin(String userId, String userPassword) throws loginException {
        User loginUser = null;
        try {
            String sql = "SELECT * FROM user WHERE user_id = ? AND user_password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, userPassword);

            //SQL実行
            rs = ps.executeQuery();
            while(rs.next()) {
                String id = rs.getString("user_id");
                String name = rs.getString("user_name");
                String password = rs.getString("user_password");
                String role = rs.getString("role");
                loginUser = new User(id, name, password, role);
            }
            if (loginUser == null) {
                throw new loginException("ログインできませんでした");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new loginException("SQL実行中に例外が発生しましたUser");
        }
        return loginUser;
    }
}
