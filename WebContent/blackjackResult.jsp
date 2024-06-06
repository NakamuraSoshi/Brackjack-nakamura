<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Card" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Player" %>
<%@ page import="model.Dealer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blackjack Result</title>
</head>
<body>
    <h1>Blackjack Game Result</h1>

    <h2>Your Hand:</h2>
    <ul>
        <%
            // セッションからプレイヤーオブジェクトを取得
            Player player = (Player) session.getAttribute("player");
            // プレイヤーの手札を表示
            for (Card card : player.getHand()) {
                out.println("<li>" + card.toString() + "</li>");
            }
        %>
    </ul>
    <p>Total Value: <%= player.getHandValue() %></p> <!-- プレイヤーの手札の合計値を表示 -->

    <h2>Dealer's Hand:</h2>
    <ul>
        <%
            // セッションからディーラーオブジェクトを取得
            Dealer dealer = (Dealer) session.getAttribute("dealer");
            // ディーラーの手札を表示
            for (Card card : dealer.getHand()) {
                out.println("<li>" + card.toString() + "</li>");
            }
        %>
    </ul>
    <p>Total Value: <%= dealer.getHandValue() %></p> <!-- ディーラーの手札の合計値を表示 -->

    <h2>Game Result:</h2>
    <p><%= request.getAttribute("message") %></p> <!-- サーブレットから渡された結果メッセージを表示 -->

    <form method="post" action="RetryServlet">
        <button type="submit" name="action" value="newgame">もう一度</button> <!-- 新しいゲームを開始するボタン -->
    </form>
    <form action="mainMenu.jsp" method="post">
    	<input type="submit" value="メインメニューに戻る" class="button"><br>
    </form>
</body>
</html>