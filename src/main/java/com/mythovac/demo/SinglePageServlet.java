package com.mythovac.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 谋篇具体的留言页面
 * */
@WebServlet(name = "singlePageServlet", value = "/single-page")
public class SinglePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 未登入者跳转到登入界面
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("username") == null) {
            response.sendRedirect("sign-in");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
