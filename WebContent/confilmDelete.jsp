<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除確認</title>
<style>
body {
    background-color: #f2f2f2;
    color: #f1f1f1;
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
    padding: 30px 40px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    max-width: 400px;
    width: 100%;
}

h2 {
    font-size: 1.8em;
    margin-bottom: 25px;
    color: #e74c3c;
}

form {
    margin: 15px 0;
}

.btn {
    padding: 12px 25px;
    font-size: 1em;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin: 5px;
    width: 100px;
    transition: background-color 0.3s;
}

.yes {
    background-color: #e74c3c;
    color: #fff;
}

.no {
    background-color: #3498db;
    color: #fff;
}

.yes:hover {
    background-color: #c0392b;
}

.no:hover {
    background-color: #2980b9;
}

</style>
</head>
<body>
	<div class="container">
    <h2>本当にユーザーを削除しますか？</h2>
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
