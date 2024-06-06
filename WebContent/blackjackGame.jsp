<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="model.Card" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack Game</title>
</head>
<body>
    <h1>Blackjack Game</h1>

    <h2>Your Hand:</h2>

<%--セッションから手札取得 forループで手札を反復処理 --%>
    <ul>
        <%
            Player player = (Player) session.getAttribute("player");
            for (Card card : player.getHand()) {
                out.println("<li>" + card.toString() + "</li>");
            }
        %>
    </ul>
    <p>Total Value: <%= player.getHandValue() %></p>

    <h2>Dealer's Hand:</h2>

<%--最初の手札を表示して、２枚目は伏せる --%>
    <ul>
        <%
            Dealer dealer = (Dealer) session.getAttribute("dealer");
            if (dealer.getHand().size() > 0) {
                out.println("<li>" + dealer.getHand().get(0).toString() + "</li>");
            }
        %>
        <li>Hidden</li>
    </ul>

    <form method="post" action="BlackjackServlet">
        <button type="submit" name="action" value="hit">Hit</button>
        <button type="submit" name="action" value="stand">Stand</button>
    </form>
    <form action="mainMenu.jsp" method="post">
    	<input type="submit" value="メインメニューに戻る" class="button"><br>
    </form>
</body>
</html>