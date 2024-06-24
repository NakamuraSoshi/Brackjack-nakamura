package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.BlackjackResultDao;
import Dao.ChipDao;
import exception.loginException;
import model.Chip;
import model.Dealer;
import model.Deck;
import model.Player;
import model.User;


@WebServlet("/BlackjackServlet")
public class BlackjackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
            // セッションから取得
            HttpSession session = request.getSession();
            Deck deck = (Deck) session.getAttribute("deck");
            Player player = (Player) session.getAttribute("player");
            Dealer dealer = (Dealer) session.getAttribute("dealer");
            User user =(User) session.getAttribute("user");
            Chip chip =(Chip)session.getAttribute("chip");

            double multiplier = 0;

            // ユーザーのターン ヒットかスタンドか jspでアクション
            String action = request.getParameter("action");

            // ifでヒットの内容 バーストしたらdealer勝利メッセと結果ページへ
            if ("hit".equals(action)) {
                player.drawCard(deck);
                if (player.isBust()) {
                    updateBlackjackResult(user.getUserId(), false, false);
                    request.setAttribute("message", "You bust! Dealer wins! 0x");
                    request.setAttribute("payout", 0);
                    request.getRequestDispatcher("blackjackResult.jsp").forward(request, response);
                    return; // バーストした場合は処理を終了
                }
            } else if ("stand".equals(action)) {
                // elseでユーザーがスタンド ディーラが一枚引く
                dealer.drawCards(deck);

                // プレイヤーの勝利
                String result;
                boolean isWin = false;
                boolean isDraw = false;

                // 21でプレイヤーが勝利
                if (dealer.isBust() || player.getHandValue() > dealer.getHandValue()) {
                    if (player.getHandValue() == 21) {
                        result = "Blackjack! You win! 2.5x";
                        isWin = true;
                        multiplier = 2.5;
                    } else {
                        result = "You win! 2x";
                        isWin = true;
                        multiplier = 2;
                    }
                } else if (player.getHandValue() < dealer.getHandValue()) {
                    // ディーラの勝利
                    result = "Dealer wins! 0x";
                    multiplier = 0;
                } else {
                    // 引き分け メッセと結果ページへ
                    result = "It's a draw! 1x";
                    isDraw = true;
                    multiplier = 1;
                }

                // 金額の計算
                int betAmount = (int) session.getAttribute("betAmount");
                int payout = (int) Math.floor(betAmount * multiplier);
                chip.setChipCount(chip.getChipCount() + payout);

                updateBlackjackResult(user.getUserId(), isWin, isDraw);

                // DB更新
                ChipDao chipDao = new ChipDao();
                chipDao.updateChips(chip);

                request.setAttribute("message", result);
                request.setAttribute("payout", payout);
                request.getRequestDispatcher("blackjackResult.jsp").forward(request, response);
                return; // 処理を終了


            }

            // 賭け金と合計チップ数をJSPに渡す
            request.setAttribute("betAmount", session.getAttribute("betAmount"));
            request.setAttribute("payout", 0);

            // バーストしていないなら継続
            request.getRequestDispatcher("blackjackGame.jsp").forward(request, response);
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    // ブラックジャックの結果をデータベースに登録
    private void updateBlackjackResult(String userId, boolean isWin, boolean isDraw) throws ServletException {
        try {
            BlackjackResultDao resultDao = new BlackjackResultDao();
            resultDao.updateBlackjackResult(userId, isWin, isDraw);
        } catch (loginException e) {
            throw new ServletException("Failed to update blackjack result", e);
        }
    }

    // 例外処理
    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}