<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="Dao.UserListDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet" type="text/css" href="css/manageUser.css">
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
        	//requestオブジェからuserList属性を取り出して、List<User>にキャスト
            List<User> userList = (List<User>) request.getAttribute("userList");

        	//ユーザーがいるか確認
            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {
        %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUserName() %></td>
            <td><%= user.getUserPassword() %></td>
            <td><%= user.getRole() %></td>
            <td>

            <%
            if (!"admin".equals(user.getRole())) {

            %>
                <form action="adminConfirmDelete.jsp" method="post">
                    <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                    <input type="hidden" name="confirm" value="true">
                    <input type="submit" value="削除">
                </form>
                <%
                    }
                %>
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

