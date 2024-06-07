package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dealer;
import model.Deck;
import model.Player;

/**
 * Servlet implementation class BlackjackStart
 */
@WebServlet("/BlackjackStartServlet")
public class BlackjackStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
}