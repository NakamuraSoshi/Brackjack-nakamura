<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Card" %>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack Result</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="result-container">
       

        <h2>Dealer's Hand:</h2>
        <ul>
            <%
                Dealer dealer = (Dealer) session.getAttribute("dealer");
                for (Card card : dealer.getHand()) {
                    String imagePath = card.getImagePath();
                    out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
                }
            %>
        </ul>
        <p>Total Value: <%= dealer.getHandValue() %></p>

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
                    List<Card> hand2 = player.getHand2();
                    for (Card card : hand2) {
                        String imagePath = card.getImagePath();
                        out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
                    }
                %>
            </ul>
            <p>Total Value: <%= player.getHandValue() %></p>
        <% } %>

        <p><%= request.getAttribute("message") %></p>
        <p>Bets: <%= session.getAttribute("betAmount") %> Chips</p>
        <p>Payout: <%= request.getAttribute("payout") %> Chips</p>

        <form method="post" action="RetryServlet">
            <button type="submit" name="action" value="newgame" class="button">もう一度</button>
        </form>
        <form action="mainMenu.jsp" method="post">
            <button type="submit" class="button">メインメニューに戻る</button>
        </form>
    </div>
</body>
</html>

