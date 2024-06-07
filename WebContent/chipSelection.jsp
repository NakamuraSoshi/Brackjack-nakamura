<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掛け金選択</title>
<style>
body {
    background-color: #f2f2f2;
    font-family: 'Arial', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}
.container {
    text-align: center;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}
h1 {
    font-size: 2em;
    margin-bottom: 20px;
}
button {
    background-color: #4CAF50;
    color: white;
    padding: 15px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    margin: 5px;
}
button:hover {
    background-color: #45a049;
}
.main-menu-button {
    background-color: #f39c12;
}
.main-menu-button:hover {
    background-color: #e67e22;
}
</style>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    int chips = user.getChips();
%>
<div class="container">
    <h1>掛け金を選択してください</h1>
    <p>現在のチップ数: <%= chips %></p>
    <form action="BlackjackServlet" method="post">
        <input type="hidden" name="action" value="bet">
        <select name="betAmount">
            <% for (int i = 1; i <= 10; i++) { %>
                <option value="<%= i %>"><%= i %> 枚</option>
            <% } %>
        </select>
        <button type="submit">ベット</button>
    </form>
    <form action="mainMenu.jsp" method="get">
        <button type="submit" class="main-menu-button">メインメニューに戻る</button>
    </form>
</div>
</body>
</html>