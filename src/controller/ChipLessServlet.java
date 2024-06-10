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


@WebServlet("/ChipLessServlet")
public class ChipLessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションからuserとchipオブジェクトを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Chip chip = (Chip) session.getAttribute("chip");

		//userがnullでログインページへ
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		//リクエストからアクションパラメータを取得し、betなら、betAmountをリクエストから取得し、整数変換
		String action = request.getParameter("action");
		if ("bet".equals(action)) {
			int betAmount = Integer.parseInt(request.getParameter("betAmount"));
			int currentChips = chip.getChipCount();

			// チップ残高をマイナスまで許容する
			currentChips -= betAmount;
			chip.setChipCount(currentChips);

			try {

				//ChipDaoでDBのチップ情報を更新
				ChipDao chipDao = new ChipDao();
				chipDao.updateChips(chip);

			} catch (loginException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", "チップの更新に失敗しました。");
				request.setAttribute("error", "true");
				request.getRequestDispatcher("chipSelection.jsp").forward(request, response);
				return;
			}

			//更新されたチップ情報をセッションに設定、賭け金の額をセッションに設定
			session.setAttribute("chip", chip);
			session.setAttribute("betAmount", betAmount);
			request.getRequestDispatcher("BlackjackStartServlet").forward(request, response);
		}
	}
}