package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChipDao;
import Dao.ScoreDao;
import exception.loginException;
import model.Chip;
import model.User;
import model.UserScore;

//ユーザーのスコアと上位５名のスコアを表示するためのサーブレット
@WebServlet("/ScoreDisplayServlet")
public class ScoreDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

    	//セッションの取得とユーザーオブジェクトの取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //ログインしていないユーザを排除
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {

        	//インスタンス化
            ScoreDao scoreDao = new ScoreDao();

            //getUserScoreメソッドを呼び出し、ログインしているスコアを取得
            UserScore userScore = scoreDao.getUserScore(user.getUserId());

            ChipDao chipDao = new ChipDao();
            Chip chip = chipDao.getChipsByUserId(user.getUserId());

            //getTopUsersメソッドで上位５名のスコアを取得
            List<UserScore> topUsers = scoreDao.getTopUsers();

            //取得した情報をリクエスト属性に設定し、転送
            request.setAttribute("userScore", userScore);
            request.setAttribute("chip", chip);
            request.setAttribute("topUsers", topUsers);

            request.getRequestDispatcher("scoreDisplay.jsp").forward(request, response);

        } catch (loginException | SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

