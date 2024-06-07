package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChipDao;
import exception.loginException;
import model.Chip;
import model.User;

//ユーザーがチップを選択する際に自分のチップ情報を取得するサーブレット
@WebServlet("/ChipSelectionServlet")
public class ChipSelectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	//現在のセッションを取得し、そのセッションからuserオブジェクトを取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //userがnullならログイン画面へ
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // ChipDaoでユーザーのチップ情報をデータベースから取得する
        try {
            ChipDao chipDao = new ChipDao();
            Chip chip = chipDao.getChipsByUserId(user.getUserId());

            //取得したチップ情報をセッションに設定
            session.setAttribute("chip", chip);
            request.getRequestDispatcher("chipSelection.jsp").forward(request, response);

        } catch (loginException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "チップの取得に失敗しました。");
            request.setAttribute("error", "true");
            request.getRequestDispatcher("mainMenu.jsp").forward(request, response);
        }
    }
}