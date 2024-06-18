<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<%@ page import="model.Card" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack Split</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="blackjack-container">

        <div class="hand-container">

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
            <h2>Hand 1</h2>
            <ul class="hand">
                <%
                    List<Card> hand1 = (List<Card>) request.getAttribute("hand1");
                    if (hand1 != null) {
                        for (Card card : hand1) {
                %>
                <li class="card">
                    <img src="<%= card.getImagePath() %>" alt="<%= card.toString() %>">
                </li>
                <%
                        }
                    }
                %>
            </ul>
            <p>Total Value: <%= request.getAttribute("hand1Value") %></p>

            <div class="button-container">
                <form action="BlackjackSplitServlet" method="post">
                    <input type="hidden" name="action" value="hit1">
                    <button type="submit">Hit</button>
                </form>
                <form action="BlackjackSplitServlet" method="post">
                    <input type="hidden" name="action" value="stand1">
                    <button type="submit">Stand</button>
                </form>
            </div>

            <%
                String message1 = (String) request.getAttribute("message1");
                if (message1 != null) {
            %>
            <p><%= message1 %></p>
            <%
                }
            %>
        </div>

        <div class="hand-container">

            <h2>Dealer's Hand:</h2>
            <ul>
                <%
                    if (dealer.getHand().size() > 0) {
                        String imagePath = dealer.getHand().get(0).getImagePath();
                %>
                <li><img src="<%= imagePath %>" alt="<%= dealer.getHand().get(0).toString() %>"></li>
                <%
                    } else {
                %>
                <li><img src="Cards/back_of_card.png" alt="Hidden Card"></li>
                <%
                    }
                %>
            </ul>
            <p>Total Value:</p>
            <h2>Hand 2</h2>
            <ul class="hand">
                <%
                    List<Card> hand2 = (List<Card>) request.getAttribute("hand2");
                    if (hand2 != null) {
                        for (Card card : hand2) {
                %>
                <li class="card">
                    <img src="<%= card.getImagePath() %>" alt="<%= card.toString() %>">
                </li>
                <%
                        }
                    }
                %>
            </ul>
            <p>Total Value: <%= request.getAttribute("hand2Value") %></p>

            <div class="button-container">
                <form action="BlackjackSplitServlet" method="post">
                    <input type="hidden" name="action" value="hit2">
                    <button type="submit">Hit</button>
                </form>
                <form action="BlackjackSplitServlet" method="post">
                    <input type="hidden" name="action" value="stand2">
                    <button type="submit">Stand</button>
                </form>
            </div>

            <%
                String message2 = (String) request.getAttribute("message2");
                if (message2 != null) {
            %>
            <p><%= message2 %></p>
            <%
                }
            %>
        </div>

        <div class="button-container">
            <form action="mainMenu.jsp" method="post">
                <button type="submit" class="button">メインメニューに戻る</button>
            </form>
        </div>

    </div>

</body>
</html>
