package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Deck;
import model.Player;

/**
 * Servlet implementation class BlackjackStart
 */
@WebServlet("/BlackjackSplitStartServlet")
public class BlackjackSplitStartSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Player player = (Player)session.getAttribute("player");
		Deck deck = (Deck)session.getAttribute("deck");

		// スプリットの初期状態を作る
		player.split();

		player.drawCard1(deck);
		player.drawCard2(deck);

		//手札計算
		int handValue = player.getHandValue();
		int hand2Value = player.getHand2Value();

		// セッションにデッキとプレイヤー情報をセット
		session.setAttribute("deck", deck);
		session.setAttribute("player", player);

		// リクエストに手札と合計値をセット
		request.setAttribute("hand1", player.getHand());
		request.setAttribute("hand2", player.getHand2());
		request.setAttribute("hand1Value", handValue);
		request.setAttribute("hand2Value", hand2Value);

		// JSPにフォワード
		request.getRequestDispatcher("splitBlackjackGame.jsp").forward(request, response);
	}
}
