<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メインメニュー</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        text-align: center;
        width: 300px;
    }
    h1 {
        color: #333;
    }
    h2 {
        color: #555;
        font-size: 16px;
    }
    .menu {
        list-style: none;
        padding: 0;
        margin-top: 20px;
    }
    .menu li {
        margin-bottom: 10px;
    }
    .menu a {
        display: block;
        text-decoration: none;
        color: #fff;
        background-color: #007bff;
        padding: 10px;
        border-radius: 5px;
        transition: background-color 0.3s;
    }
    .menu a:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
%>
<div class="container">
    <h1>管理者用メニュー</h1>
    <h2>ID: <%= user.getUserId() %> でログイン中</h2>

    <ul class="menu">
        <li><a href="ManageUserServlet">ユーザー管理</a></li>
        <li><a href="LogoutServlet">ログアウト</a></li>
    </ul>
</div>
</body>
</html>
