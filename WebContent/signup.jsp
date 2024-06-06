<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .signup-container {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
    }
    h2 {
        margin-bottom: 20px;
    }
    .input-field {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        box-sizing: border-box;
    }
    .button {
        background-color: #4CAF50;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
    }
    .button:hover {
        background-color: #45a049;
    }
    .message {
        color: green;
        margin-bottom: 20px;
    }
    .error {
        color: red;
        margin-bottom: 20px;
    }
    .link-button {
        text-decoration: none;
        background-color: #2196F3;
        color: white;
        padding: 10px;
        border-radius: 5px;
        display: inline-block;
        margin-top: 10px;
    }
    .link-button:hover {
        background-color: #0b7dda;
    }
</style>
</head>
<body>
    <div class="signup-container">
        <h2>新規登録</h2>
        <%
// 処理メッセージとエラー判定を取得
		String message = (String)request.getAttribute("message");
		String error = (String)request.getAttribute("error");

// 正常終了した場合のメッセージを表示
		if(message != null && error == null) {%>
		 <%= message %>
		<%
// 異常終了した場合のメッセージを表示
		} else if(message != null && error != null) {%>
		<%=message %>
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

