package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import exception.loginException;
import model.User;


//ログイン画面のサーブレットクラス

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String loginId = request.getParameter("loginId");
		String userPassword = request.getParameter("loginPassword");

		try {
			// ログイン処理（DBにIDとパスワードの組み合わせがあるかチェック）
			// ログインできない場合は例外を発生させます
			UserDao userDao = new UserDao();
			User user = userDao.doLogin(loginId, userPassword);

			// ログインできている場合はログインしたユーザーの情報をセッションにセット
			// ログイン状態とみなします
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			//ロールがadminならadminMainMenu.jspへ遷移 その他はmainMenu.jsp
			if ("admin".equals(user.getRole())) {
				response.sendRedirect("adminMainMenu.jsp");
			}else {

				response.sendRedirect("mainMenu.jsp");
			}

		} catch (loginException e) {
			// ログインできなかった場合はメッセージをセットしてlogin.jspに表示
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			request.getRequestDispatcher("login.jsp").
			forward(request, response);
		}

	}
}