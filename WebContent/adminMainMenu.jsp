<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メインメニュー</title>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");
%>
	<h1>管理者用メインメニュー</h1>
	<h2>ID:<%= user.getUserId() %>でログイン中</h2>

	<ul class="menu">
		<li><a href="ManageUserServlet">ユーザー管理</a>
		<li><a href="LogoutServlet">ログアウト</a>
	</ul>

</body>
</html>