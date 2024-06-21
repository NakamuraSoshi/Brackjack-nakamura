<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <div class="container">
        <h2>新規登録</h2>
        <%
        // 処理メッセージとエラー判定を取得
        String message = (String) request.getAttribute("message");
        String error = (String) request.getAttribute("error");

        // 正常終了した場合のメッセージを表示
        if (message != null && error == null) {
        %>
        <div class="message"><%= message %></div>
        <%
        // 異常終了した場合のメッセージを表示
        } else if (message != null && error != null) {
        %>
        <div class="error"><%= message %></div>
        <% } %>

        <form action="SignupServlet" method="post">
            <p>すべて入力してください</p>
            <input type="text" name="userName" placeholder="名前" class="input-field" required><br>
            <input type="text" name="userId" placeholder="ユーザーID" class="input-field" required><br>
            <input type="password" name="password" placeholder="パスワード" class="input-field" required><br>
            <input type="submit" value="登録" class="button"><br>
        </form>

        <a href="login.jsp" class="link-button">ログイン画面へ</a>
    </div>
</body>
</html>

