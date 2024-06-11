<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Card" %>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blackjack Result</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Blackjack Game Result</h1>

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
            for (Card card : player.getHand()) {
                String imagePath = card.getImagePath();
                out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
            }
        %>
    </ul>
    <p>Total Value: <%= player.getHandValue() %></p>

    <p><%= request.getAttribute("message") %></p>

    <form method="post" action="RetryServlet">
        <button type="submit" name="action" value="newgame">もう一度</button>
    </form>
    <form action="mainMenu.jsp" method="post">
        <input type="submit" value="メインメニューに戻る" class="button"><br>
    </form>
</body>
</html>
