<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Card" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Split Blackjack Game</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="blackjack-container">
        <h2>Hand 1:</h2>
        <ul>
            <% List<Card> hand1 = (List<Card>) request.getAttribute("hand1");
               for (Card card : hand1) { %>
                <li><img src="<%= card.getImagePath() %>" alt="<%= card.toString() %>"></li>
            <% } %>
        </ul>
        <p>Total Value: <%= calculateHandValue(hand1) %></p>

        <h2>Hand 2:</h2>
        <ul>
            <% List<Card> hand2 = (List<Card>) request.getAttribute("hand2");
               for (Card card : hand2) { %>
                <li><img src="<%= card.getImagePath() %>" alt="<%= card.toString() %>"></li>
            <% } %>
        </ul>
        <p>Total Value: <%= calculateHandValue(hand2) %></p>

        <p>Bets: <%= session.getAttribute("betAmount") %> Chips</p>
        <p>Payout: 0 Chips</p>

        <form method="post" action="BlackjackSplitServlet">
            <button type="submit" name="action" value="hit1" class="button">Hit Hand 1</button>
            <button type="submit" name="action" value="stand1" class="button">Stand Hand 1</button>
            <button type="submit" name="action" value="hit2" class="button">Hit Hand 2</button>
            <button type="submit" name="action" value="stand2" class="button">Stand Hand 2</button>
        </form>

        <form action="mainMenu.jsp" method="post">
            <button type="submit" class="button">Return to Main Menu</button>
        </form>
    </div>
</body>
</html>

<%!
    private int calculateHandValue(List<Card> hand) {
        int totalValue = 0;
        for (Card card : hand) {
            totalValue += card.getValue();
        }
        return totalValue;
    }
%>