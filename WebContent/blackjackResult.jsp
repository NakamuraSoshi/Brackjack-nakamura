<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Card" %>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="java.util.List"%>
<%@ page import="model.Chip" %>
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

        <%
            Player player = (Player) session.getAttribute("player");
        %>

        <div class="hand-container <% if (player.getHand2() != null && !player.getHand2().isEmpty()) { %> has-hand2 <% } else { %> single-hand <% } %>">
            <div class="hands">
                <div class="hand">
                    <h2>Your Hand:</h2>
                    <ul>
                        <%
                            for (Card card : player.getHand()) {
                                String imagePath = card.getImagePath();
                                out.println("<li><img src='" + imagePath + "' alt='" + card.toString() + "'></li>");
                            }
                        %>
                    </ul>
                    <p><%= request.getAttribute("message") %></p>
                </div>
                <% if (player.getHand2() != null && !player.getHand2().isEmpty()) { %>
                <div class="hand">
                    <h2>Your Hand 2:</h2>
                    <ul>
                        <%
                            for (Card card : player.getHand2()) {
                                String imagePath = card.getImagePath();
                        %>
                        <li><img src="<%= imagePath %>" alt="<%= card.toString() %>"></li>
                        <% } %>
                    </ul>
                    <p><%= request.getAttribute("message2") %></p>
                </div>
                <% } %>
            </div>
        </div>

        <p>Total chips: <%= ((Chip)session.getAttribute("chip")).getChipCount() %></p>
        <p>Payout: <%= request.getAttribute("payout") %> </p>


            <form action="ChipLessServlet" method="post">
        <input type="hidden" name="action" value="bet">
        <select name="betAmount">
            <% for (int i = 1; i <= 10; i++) { %>
                <option value="<%= i %>" <%= (i == 10) ? "selected" : "" %>><%= i %> 枚</option>
            <% } %>
        </select>
        <button type="submit" class="button">Bet</button>
    </form>
            <form action="mainMenu.jsp" method="post">
                <button type="submit" class="button">メインメニューに戻る</button>
            </form>

    </div>
</body>
</html>