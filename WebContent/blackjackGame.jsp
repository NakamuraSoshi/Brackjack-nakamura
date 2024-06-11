<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="model.Card" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack Game</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Blackjack Game</h1>

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
            for (Card card : player.getHand()) {
                String imagePath = card.getImagePath();
                out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
            }
        %>
    </ul>
    <p>Total Value: <%= player.getHandValue() %></p>

    <form method="post" action="BlackjackServlet">
        <button type="submit" name="action" value="hit">Hit</button>
        <button type="submit" name="action" value="stand">Stand</button>
    </form>
    <form action="mainMenu.jsp" method="post">
        <input type="submit" value="メインメニューに戻る" class="button"><br>
    </form>
</body>
</html>
