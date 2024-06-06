<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="Dao.UserListDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        height: 100vh;
    }
    h1 {
        color: #333;
    }
    table {
        width: 80%;
        border-collapse: collapse;
        margin: 20px 0;
    }
    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .no-users {
        text-align: center;
        color: #888;
    }
    form {
        display: inline;
    }
    input[type="submit"] {
        background-color: #ff4c4c;
        color: white;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
        border-radius: 5px;
    }
    input[type="submit"]:hover {
        background-color: #ff1a1a;
    }
    .back-link {
        margin-top: 20px;
        color: #007bff;
        text-decoration: none;
    }
    .back-link:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <h1>ユーザー一覧</h1>
    <table>
        <tr>
            <th>ユーザーID</th>
            <th>ユーザー名</th>
            <th>パスワード</th>
            <th>ロール</th>
            <th>操作</th>
        </tr>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");

            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {
        %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUserName() %></td>
            <td><%= user.getUserPassword() %></td>
            <td><%= user.getRole() %></td>
            <td>
                <form action="AdminDeleteUserServlet" method="post">
                    <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                    <input type="hidden" name="confirm" value="true">
                    <input type="submit" value="削除">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="no-users">ユーザーはいません。</td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="adminMainMenu.jsp" class="back-link">メインメニューに戻る</a>
</body>
</html>

