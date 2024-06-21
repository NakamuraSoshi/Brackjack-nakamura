<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Chip" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掛け金選択</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<%
	Chip chip = (Chip) session.getAttribute("chip");
	int chips = chip.getChipCount();
%>

<div class="container">
    <h1>賭け金を選択してください</h1>
    <h2>現在のチップ数: <%= chips %></h2>
    <form action="ChipLessServlet" method="post">
        <input type="hidden" name="action" value="bet">
        <select name="betAmount">
            <% for (int i = 1; i <= 10; i++) { %>
                <option value="<%= i %>" <%= (i == 10) ? "selected" : "" %>><%= i %> 枚</option>
            <% } %>
        </select>
        <button type="submit" class="button">ベット</button>
    </form>
    <form action="mainMenu.jsp" method="get">
        <button type="submit" class="button main-menu-button">メインメニューに戻る</button>
    </form>
</div>
</body>
</html>
