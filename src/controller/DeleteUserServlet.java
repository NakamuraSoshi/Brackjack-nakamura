package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DeleteUserDao;
import exception.loginException;
import model.User;

//ユーザーを削除するためのサーブレットクラス
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	//リクエストから削除確認用のconfirmパラメーターを取得
    	//nullまたはtrueでなければ削除しない
        String confirm = request.getParameter("confirm");
        if (confirm == null || !confirm.equals("true")) {
            response.sendRedirect("mainMenu.jsp");
            return;
        }

        //セッションからuserオブジェクト取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //ログインしていないユーザーの削除対策
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //インスタンス化とdeleteUserメソッドでuserとscoresテーブルからユーザー削除
        try {
            DeleteUserDao deleteUserDao = new DeleteUserDao();
            deleteUserDao.deleteUser(user);

            // セッションを無効化し、ログインページへリダイレクト
            session.invalidate();
            response.sendRedirect("login.jsp");

        } catch (loginException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "ユーザーの削除に失敗しました。");
            request.setAttribute("error", "true");
            request.getRequestDispatcher("mainMenu.jsp").forward(request, response);
        }
    }
}


