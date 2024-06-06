package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//ログアウトのサーブレット
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	//ログアウト
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			// セッションの情報を破棄
			HttpSession session = request.getSession(false);
			session.invalidate();

			// login.jspに表示するメッセージをセット
			request.setAttribute("message", "ログアウトしました");

			// login.jspを表示
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
	}
