package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.loginException;
import model.User;

public class UserListDao extends BaseDao {

    public UserListDao() throws loginException {
        super();
    }

    // データベースからすべてのユーザーを取得するメソッド
    public List<User> getAllUsers() throws SQLException, loginException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT user_id, user_name, user_password, role FROM user";
        try (
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String userName = rs.getString("user_name");
                String userPassword = rs.getString("user_password");
                String role = rs.getString("role");

                User user = new User(userId, userName, userPassword, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new SQLException("ユーザーリストの取得に失敗しました。", e);
        }

        return userList;
    }
}