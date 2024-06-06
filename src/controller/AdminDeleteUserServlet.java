package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DeleteUserDao;
import exception.loginException;
import model.User;


@WebServlet("/AdminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//confirmを取得する
		String confirm = request.getParameter("confirm");
		if (confirm == null || !confirm.equals("true")) {

			response.sendRedirect("manageUser.jsp");
			return;
		}

		//ユーザーIDを取得
		String userId = request.getParameter("userId");

		try {
			
			//インスタンスを作成し、userIdと空のパスワードたちでUserを作成
			DeleteUserDao deleteUserDao = new DeleteUserDao();
			User userToDelete = new User(userId, "", "", "");

			//deleteUserメソッドで削除
			deleteUserDao.deleteUser(userToDelete);

			response.sendRedirect("ManageUserServlet");

		} catch (loginException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "ユーザーの削除に失敗しました。");
            request.setAttribute("error", "true");
            request.getRequestDispatcher("manageUser.jsp").forward(request, response);
        }
    }
}