<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.UserScore" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表示</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        height: 100vh;
    }
    h1, h2 {
        color: #333;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        width: 80%;
        max-width: 800px;
        text-align: center;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: center;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .no-data {
        color: #888;
    }
    .back-link {
        display: inline-block;
        margin-top: 20px;
        color: #007bff;
        text-decoration: none;
        padding: 10px 20px;
        border: 1px solid #007bff;
        border-radius: 5px;
        transition: background-color 0.3s, color 0.3s;
    }
    .back-link:hover {
        background-color: #007bff;
        color: #fff;
    }
</style>
</head>
<body>
    <div class="container">


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
            <p>チップ数: <%= userScore.getChipCount() %></p> <!-- チップ数の表示を追加 -->
        <%
            } else {
        %>
            <p class="no-data">成績が見つかりませんでした。</p>
        <%
            }
        %>

        <h2>チップ数上位5名のユーザー</h2>
        <table>
            <tr>
                <th>順位</th>
                <th>ユーザー名</th>
                <th>勝率</th>
                <th>チップ数</th> <!-- チップ数の列を追加 -->
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
                <td><%= score.getChipCount() %></td> <!-- チップ数の表示を追加 -->
            </tr>
        <%
                    rank++;
                }
            } else {
        %>
            <tr>
                <td colspan="4" class="no-data">上位のユーザーが見つかりませんでした。</td>
            </tr>
        <%
            }
        %>
        </table>

        <a href="mainMenu.jsp" class="back-link">メインメニューに戻る</a>
    </div>
</body>
</html>
