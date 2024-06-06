package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.BlackjackResultDao;
import exception.loginException;
import model.Dealer;
import model.Deck;
import model.Player;
import model.User;

@WebServlet("/BlackjackServlet")
public class BlackjackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 初期状態を作る
		Deck deck = new Deck();
		Player player = new Player();
		Dealer dealer = new Dealer();

		// ゲーム開始 カード2枚引かせる
		player.drawInitialCards(deck);
		dealer.drawInitialCards(deck);

		//セッションにデッキ、プレイヤー、ディーラを保存させてゲーム画面に移動
		HttpSession session = request.getSession();
		session.setAttribute("deck", deck);
		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);

		request.getRequestDispatcher("blackjackGame.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// セッションから取得
			HttpSession session = request.getSession();
			Deck deck = (Deck) session.getAttribute("deck");
			Player player = (Player) session.getAttribute("player");
			Dealer dealer = (Dealer) session.getAttribute("dealer");
			User user =(User) session.getAttribute("user"); // セッションからユーザーIDを取得


			// ユーザーのターン ヒットかスタンドか jspでアクション
			String action = request.getParameter("action");

			// ifでヒットの内容 バーストしたらdealer勝利メッセと結果ページへ
			if ("hit".equals(action)) {
				player.drawCard(deck);
				if (player.isBust()) {
					updateBlackjackResult(user.getUserId(), false, false);
					request.setAttribute("message", "You bust! Dealer wins!");
					request.getRequestDispatcher("blackjackResult.jsp").forward(request, response);
					return; // バーストした場合は処理を終了
				}
			} else if ("stand".equals(action)) {
				// elseでユーザーがスタンド ディーラが一枚引く
				dealer.drawCards(deck); // 17以上になるまで引くように修正

				// プレイヤーの勝利
				String result;
				boolean isWin = false;
				boolean isDraw = false;
				if (dealer.isBust() || player.getHandValue() > dealer.getHandValue()) {
					result = "You win!";
					isWin = true;
				} else if (player.getHandValue() < dealer.getHandValue()) {
					// ディーラの勝利
					result = "Dealer wins!";
				} else {
					// 引き分け メッセと結果ページへ
					result = "It's a draw!";
					isDraw = true;
				}
				updateBlackjackResult(user.getUserId(), isWin, isDraw);
				request.setAttribute("message", result);
				request.getRequestDispatcher("blackjackResult.jsp").forward(request, response);
				return; // 処理を終了
			}

			// バーストしていないなら継続
			request.getRequestDispatcher("blackjackGame.jsp").forward(request, response);
		} catch (Exception e) {
			handleException(request, response, e);
		}
	}

	//ブラックジャックの結果をデータベースに登録
	private void updateBlackjackResult(String string, boolean isWin, boolean isDraw) throws ServletException {
		try {
			BlackjackResultDao resultDao = new BlackjackResultDao();
			resultDao.updateBlackjackResult(string, isWin, isDraw);
		} catch (loginException e) {
			throw new ServletException("Failed to update blackjack result", e);
		}
	}

	//例外処理
	private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {

		e.printStackTrace();
		request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
}
