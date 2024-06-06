package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RetryServlet")
public class RetryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 現在のセッションを無効にして、新しいセッションを開始
//        HttpSession session = request.getSession();
//        session.invalidate();
//
//        // 新しいセッションを開始して、BlackjackServletにリダイレクト
//        session = request.getSession(true);
        response.sendRedirect("BlackjackServlet?action=newgame");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}