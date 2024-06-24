<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<link rel="stylesheet" type="text/css" href="css/menu.css">
</head>
<body>
<%
    // セッションからユーザー情報を取得
    model.User user = (model.User) session.getAttribute("user");
%>

<div class="container">
    <h1>ようこそ、<%= user.getUserName() %> さん！</h1>
    <ul class="menu">
        <li><a href="ChipSelectionServlet">ブラックジャックのゲーム</a></li>
        <li><a href="ScoreDisplayServlet">成績表示</a></li>
        <li><a href="confilmDelete.jsp">ユーザーの削除</a></li>
        <li><a href="LogoutServlet">ログアウト</a></li>
    </ul>
</div>
</body>
</html>
