package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.loginException;
import model.Chip;

public class ChipDao extends BaseDao {

    public ChipDao() throws loginException {
        super();
    }

 // チップを更新するメソッド
    public void updateChips(Chip chip) throws SQLException {
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE chips SET chip_count = ? WHERE user_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, chip.getChipCount());
            ps.setString(2, chip.getUserId());

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // ユーザーのチップ情報を取得するメソッド
    public Chip getChipsByUserId(String userId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Chip chip = null;

        try {
            String sql = "SELECT user_id, chip_count FROM chips WHERE user_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            rs = ps.executeQuery();
            if (rs.next()) {
                String dbUserId = rs.getString("user_id");
                int chipCount = rs.getInt("chip_count");

                chip = new Chip(dbUserId, chipCount);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return chip;
    }

 // チップを挿入するメソッド
    public void insertChips(Chip chip) throws SQLException {
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

