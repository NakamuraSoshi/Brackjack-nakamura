package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.loginException;


//DAOクラスの共通処理をまとめたスーパークラス

public abstract class BaseDao {

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

//コンストラクタ
//初期処理としてDBに接続します

	public BaseDao() throws loginException {
		// DBに接続
		getConnection();
	}


//DB接続処理

	private void getConnection() throws loginException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
//データベースはloginuser
				String url = "jdbc:mysql://localhost/loginuser?useUnicode=true&characterEncoding=UTF-8";

				String user = "root";
				String password = "";
				// DB接続
				con = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new loginException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new loginException("SQL実行中に例外が発生しましたBase");
		}
	}


// DBとの接続を解除します

	protected void close() throws loginException {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new loginException("close処理中に例外が発生しました");
		}
	}
}
