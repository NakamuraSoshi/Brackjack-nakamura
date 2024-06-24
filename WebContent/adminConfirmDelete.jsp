<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ユーザー削除確認</title>
<link rel="stylesheet" type="text/css" href="css/delete.css">
</head>
<body>
	<div class="container">
    <h2>ユーザーID：<%= request.getParameter("userId")%><br>を削除しますか？</h2>
   <form action="AdminDeleteUserServlet" method="post">
            <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>">
            <input type="hidden" name="confirm" value="true">
            <input type="submit" class="btn yes" value="はい">
    </form>
    <form action="ManageUserServlet" method="get">
        <input type="submit" class="btn no" value="いいえ">
    </form>
    </div>
</body>
</html>
