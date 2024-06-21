<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <div class="container">
        <h2>ブラックジャック</h2>
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

        <form action="LoginServlet" method="post">
            <input type="text" id="id" name="loginId" placeholder="ID" class="input-field" required><br>
            <input type="password" id="password" name="loginPassword" placeholder="Password" class="input-field" required><br>
            <input type="submit" value="ログイン" class="button"><br>
        </form>

        <a href="signup.jsp" class="link-button">新規登録</a>
    </div>
</body>
</html>
