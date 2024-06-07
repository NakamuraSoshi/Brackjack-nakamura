package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SignupDao;
import exception.loginException;
import model.Chip;
import model.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        //ロールをuserに設定する
        if(role == null || role.isEmpty()) {
        	role = "user";
        }

        String nextPage = null;

        // 新規ユーザーの作成
        User newUser = new User(userId, userName, password, role);


        try {
            // ユーザー登録処理
            SignupDao signupDao = new SignupDao();
            signupDao.registerUser(newUser);

            // 登録成功時はセッションにユーザーオブジェクトを格納し、メインメニュー画面へ
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);

            //新規登録時にチップを１００枚付与
            Chip chip = new Chip(userId, 100);
            session.setAttribute("chip", chip);

            nextPage="mainMenu.jsp";


        } catch (loginException | SQLException e) {
            // 登録失敗時はエラーメッセージをリクエストに格納し、新規登録画面にフォワード
        	String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

            nextPage ="signup.jsp";
        }
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);

    }
}

