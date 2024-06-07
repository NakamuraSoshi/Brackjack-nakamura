package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.loginException;
import model.Chip;
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
            // ユーザー情報をuserテーブルに挿入
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

             // ユーザーのチップ情報を chips テーブルに挿入
                Chip chip = new Chip(user.getUserId(), 100);
                addChips(chip);

            } else {
                System.out.println("ユーザーの追加に失敗しました。");
            }
       } finally {
       }

    }


//チップを chips テーブルに追加するメソッド
    public void addChips(Chip chip) throws SQLException {
    	PreparedStatement ps = null;

    	try {
    		String sql = "INSERT INTO chips (user_id, chip_count) VALUES (?, ?)";
    		ps = con.prepareStatement(sql);
    		ps.setString(1, chip.getUserId());
    		ps.setInt(2, chip.getChipCount());

    		ps.executeUpdate();
    	} finally {
    		if (ps != null) {
    			ps.close();
        }
    }
}
}

