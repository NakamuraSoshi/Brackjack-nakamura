<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.UserScore" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表示</title>
</head>
<body>
<h1>成績表示</h1>

<%
    UserScore userScore = (UserScore) request.getAttribute("userScore");
    List<UserScore> topUsers = (List<UserScore>) request.getAttribute("topUsers");

    if (userScore != null) {
%>
    <h2>あなたの戦績</h2>
    <p>ユーザー名: <%= userScore.getUserName() %></p>
    <p>勝利数: <%= userScore.getWins() %></p>
    <p>敗北数: <%= userScore.getLosses() %></p>
    <p>引き分け数: <%= userScore.getDraws() %></p>
    <p>勝率: <%= String.format("%.2f%%", userScore.getWinRate() * 100) %></p>
<%
    } else {
%>
    <p>成績が見つかりませんでした。</p>
<%
    }
%>

<h2>上位5名のユーザー</h2>
<table border="1">
    <tr>
    	<th>順位</th>
        <th>ユーザー名</th>
        <th>勝率</th>
    </tr>
<%
    if (topUsers != null && !topUsers.isEmpty()) {
    	int rank = 1;
        for (UserScore score : topUsers) {
%>
    <tr>
    	<td><%= rank %></td>
        <td><%= score.getUserName() %></td>
        <td><%= String.format("%.2f%%", score.getWinRate() * 100) %></td>
    </tr>
<%
		rank++;
        }
    } else {
%>
    <tr>
        <td colspan="2">スコア情報がありません。</td>
    </tr>
<%
    }
%>
</table>

<br>
<a href="mainMenu.jsp">メインメニューに戻る</a>
</body>
</html>
