package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.BlackjackResultDao;
import Dao.ChipDao;
import exception.loginException;
import model.BlackjackGameResult;
import model.Chip;
import model.Dealer;
import model.Deck;
import model.Player;
import model.User;


@WebServlet("/BlackjackSplitServlet")
public class BlackjackSplitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Deck deck = (Deck) session.getAttribute("deck");
            Player player = (Player) session.getAttribute("player");
            Dealer dealer = (Dealer) session.getAttribute("dealer");
            User user = (User) session.getAttribute("user");
            Chip chip = (Chip) session.getAttribute("chip");


            String action = request.getParameter("action");



            // 各手札に対するヒットまたはスタンドのアクション
            if ("hit1".equals(action)) {
                player.drawCard1(deck);
                int hand1Value = player.getHandValue();
                request.setAttribute("hand1Value", hand1Value);
                if (player.isBust() && player.getHandValue() > 21) {
                    request.setAttribute("message1", "Hand 1 bust! ");
                }

            } else if ("stand1".equals(action)) {
                player.standHand1();
                int hand1Value = player.getHandValue();
                request.setAttribute("hand1Value", hand1Value);

            } else if ("hit2".equals(action)) {
                player.drawCard2(deck);
                int hand2Value = player.getHand2Value();
                request.setAttribute("hand2Value", hand2Value);
                if (player.isBust() && player.getHand2Value() > 21) {
                    request.setAttribute("message2", "Hand 2 bust! ");
                }

            } else if ("stand2".equals(action)) {
                player.standHand2();
                int hand2Value = player.getHand2Value();
                request.setAttribute("hand2Value", hand2Value);

            }

            // 両方の手札がスタンドしたかバーストした場合、リザルト画面へ遷移
            if (player.isBothHandsStand() || (player.getHandValue() > 21 && player.getHand2Value() > 21)) {
                endGame(session, deck, player, dealer, user, chip, request, response);
            } else {
                // 継続プレイの場合、手札情報を更新してJSPへフォワード
                request.setAttribute("hand1", player.getHand());
                request.setAttribute("hand2", player.getHand2());
                session.setAttribute("deck", deck);
                session.setAttribute("player", player);
                session.setAttribute("dealer", dealer);
                session.setAttribute("user", user);
                session.setAttribute("chip", chip);

                int hand1Value = player.getHandValue();
                request.setAttribute("hand1Value", hand1Value);
                int hand2Value = player.getHand2Value();
                request.setAttribute("hand2Value", hand2Value);

                request.getRequestDispatcher("splitBlackjackGame.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    //ディーラーがカードを引き、結果判定
    private void endGame(HttpSession session,Deck deck,Player player, Dealer dealer, User user,Chip chip,
    		HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	dealer.drawCards(deck);

    	BlackjackGameResult gameResult = new BlackjackGameResult();

    	String result1 = gameResult.determineResult(player.getHand(), dealer.getHand());
        String result2 = gameResult.determineResult(player.getHand2(), dealer.getHand());

        updateBlackjackResult(user.getUserId(), result1.contains("win"), result1.contains("draw"));
        updateBlackjackResult(user.getUserId(), result2.contains("win"), result2.contains("draw"));

        int betAmount = (int) session.getAttribute("betAmount");
        int payout1 = gameResult.calculatePayout(result1, betAmount);
        int payout2 = gameResult.calculatePayout(result2, betAmount);

        chip.setChipCount(chip.getChipCount() + payout1 + payout2);

        try {
        	ChipDao chipDao = new ChipDao();
        	chipDao.updateChips(chip);

        }catch (SQLException  e) {
        	throw new ServletException("Failed to update chips", e);
        }catch (loginException e) {
        	throw new ServletException("Failed to update chips due to login isssue", e);
        }


        request.setAttribute("message", "Hand 1: " + result1 + " Hand 2: " + result2);
        request.setAttribute("payout", payout1 + payout2);
        request.getRequestDispatcher("blackjackResult.jsp").forward(request, response);
    }

    private void updateBlackjackResult(String userId, boolean isWin, boolean isDraw) throws ServletException {
        try {
            BlackjackResultDao resultDao = new BlackjackResultDao();
            resultDao.updateBlackjackResult(userId, isWin, isDraw);
        } catch (loginException  e) {
            throw new ServletException("Failed to update blackjack result", e);
        }
    }
}