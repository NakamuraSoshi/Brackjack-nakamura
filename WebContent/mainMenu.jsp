<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<style>
body {
    background-color: #2b2b2b;
    color: #fff;
    font-family: 'Arial', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    text-align: center;
    background-color: #1e1e1e;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
}

h1 {
    font-size: 2em;
    margin-bottom: 20px;
    color: #e74c3c; /* トランプの赤 */
}

.menu {
    list-style-type: none;
    padding: 0;
}

.menu li {
    margin: 10px 0;
}

.menu a {
    display: block;
    padding: 10px 20px;
    background-color: #34495e;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.menu a:hover {
    background-color: #e74c3c; /* トランプの赤 */
}

.menu a:active {
    background-color: #c0392b;
}
</style>
</head>
<body>
<%
    // セッションからユーザー情報を取得

    model.User user = (model.User) session.getAttribute("user");

%>

	<div class="container">
    <h1>ようこそ、<%= user.getUserName() %> さん！</h1>
    <ul class="menu">
        <li><a href="confilmDelete.jsp">ユーザーの削除</a></li>
        <li><a href="BlackjackServlet">ブラックジャックのゲーム</a></li>
        <li><a href="ScoreDisplayServlet">成績表示</a></li>
        <li><a href="LogoutServlet">ログアウト</a></li>
    </ul>
    </div>
</body>
</html>
