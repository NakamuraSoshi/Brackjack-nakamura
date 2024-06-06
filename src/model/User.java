package model;


//ユーザー情報クラス

public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String role;

//コンストラクタ
	public User(String loginId, String userName, String userPassword, String role) {
		this.userId = loginId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.role =role;

	}

// ユーザーIDを返します

	public String getUserId() {
		return userId;
	}
//ユーザーIDをセットします

	public void setUserId(String userId) {
		this.userId = userId;
	}
// ユーザー名を返します

	public String getUserName() {
		return userName;
	}
// ユーザー名をセットします

	public void setUserName(String userName) {
		this.userName = userName;
	}
// ログインパスワードを返します

	public String getUserPassword() {
		return userPassword;
	}

//ログインパスワードをセットします

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}