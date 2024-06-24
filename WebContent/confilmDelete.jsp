<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除確認</title>
<link rel="stylesheet" type="text/css" href="css/delete.css">
</head>
<body>
	<div class="container">
    <h2>ユーザーを削除しますか？</h2>
    <form action="DeleteUserServlet" method="post">
        <input type="hidden" name="confirm" value="true">
        <input type="submit" class="btn yes" value="はい">
    </form>
    <form action="mainMenu.jsp" method="get">
        <input type="submit" class="btn no" value="いいえ">
    </form>
    </div>
</body>
</html>
