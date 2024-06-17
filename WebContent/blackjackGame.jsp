<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="model.Card" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack Game</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="blackjack-container">


        <h2>Dealer's Hand:</h2>
        <ul>
            <%
                Dealer dealer = (Dealer) session.getAttribute("dealer");
                if (dealer.getHand().size() > 0) {
                    String imagePath = dealer.getHand().get(0).getImagePath();
                    out.println("<li><img src='" + imagePath + "' alt='" + dealer.getHand().get(0).toString() + "'></li>");
                }
            %>
            <li><img src="Cards/back_of_card.png" alt="Hidden Card"></li>
        </ul>
        <p>Total Value:</p>

         <h2>Your Hand:</h2>
        <ul>
            <%
                Player player = (Player) session.getAttribute("player");
                List<Card> currentHand = player.getCurrentHand();
                for (Card card : currentHand) {
                    String imagePath = card.getImagePath();
                    out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
                }
            %>
        </ul>
        <p>Total Value: <%= player.getHandValue() %></p>

        <% if (player.hasSplit()) { %>
            <h2>Your Second Hand:</h2>
            <ul>
                <%
                    for (Card card : player.getHand2()) {
                        String imagePath = card.getImagePath();
                        out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
                    }
                %>
            </ul>
            <p>Total Value: <%= player.getHandValue() %></p>
        <% } %>

        <p>Bets: <%= session.getAttribute("betAmount") %> Chips</p>
        <p>Payout: 0 Chips</p>

        <form method="post" action="BlackjackServlet">
            <button type="submit" name="action" value="hit" class="button">Hit</button>
            <button type="submit" name="action" value="stand" class="button">Stand</button>
            <% if (player.getHand().size() == 2 && !player.hasSplit() &&
                  player.getHand().get(0).getRank().getValue() == player.getHand().get(1).getRank().getValue()) { %>
                <button type="submit" name="action" value="split" formaction="ChipLessServlet" class="button">Split</button>
            <% } %>
            <% if (player.hasSplit() && player.isPlayingHand1()) { %>
                <button type="submit" name="action" value="switch" class="button">Switch to Second Hand</button>
            <% } %>
            <% if (player.hasSplit() && !player.isPlayingHand1()) { %>
                <button type="submit" name="action" value="switch" class="button">Switch to First Hand</button>
            <% } %>
        </form>

        <form action="mainMenu.jsp" method="post">
            <button type="submit" class="button">メインメニューに戻る</button>
        </form>
    </div>
</body>
</html>


