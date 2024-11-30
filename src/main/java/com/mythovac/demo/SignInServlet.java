package com.mythovac.demo;

import com.mythovac.demo.service.AppService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 登入控制Servlet
 * */
@WebServlet(name = "signInServlet", value = "/sign-in")
public class SignInServlet extends HttpServlet {
    AppService appService = new AppService();
    /**
     * 前往登入界面
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 先无效化session，在转发到登入界面
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 验证密码真确性
     * 以及新用户登入系统
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 非法输入，返回
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            response.sendRedirect("sign-in");
            return;
        }
        try {
            // 验证密码-创建账号
            if(appService.login(username,password)){
                // session登记
                request.getSession().setAttribute("username", username);
                response.sendRedirect("main-page");
                return;
            }
            // 密码错误
            response.sendRedirect("sign-in");
        } catch (Exception e) {
            // 未知异常
            System.out.println("error in SignInServlet Post");
            response.sendRedirect("sign-in");
        }
    }
}
