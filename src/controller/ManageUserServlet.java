package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserListDao;
import exception.loginException;
import model.User;

//ユーザー情報一覧を取得し、表示する
@WebServlet("/ManageUserServlet")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//UserListDaoをインスタンス化し、getAllUserメソッドでユーザーリストを取得
			UserListDao userListDao = new UserListDao();
			List<User> userList = userListDao.getAllUsers();

			//取得したリストをリクエスト属性に設定
			request.setAttribute("userList", userList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("manageUser.jsp");
			dispatcher.forward(request, response);


		} catch (loginException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "ログイン情報にエラーが発生しました。");

		}  catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "データベースのアクセスに失敗しました。後でもう一度お試しください");
}
}
}