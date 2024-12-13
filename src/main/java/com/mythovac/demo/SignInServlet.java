package com.mythovac.demo;

import com.mythovac.demo.service.AppService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

/**
 * 登入控制Servlet
 * */
@MultipartConfig
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

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 获取 Cookie 的名称和值
                String name = cookie.getName();
                String value = cookie.getValue();
                if(name.equals("password")){
                    request.setAttribute("password",value);
                }
                if(name.equals("username")){
                    request.setAttribute("username",value);
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 验证密码真确性
     * 以及新用户登入系统
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost 111111");

        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        String rememberMe = request.getParameter("rememberMe");
        System.out.println("username = " + username);
        System.out.println("password = " + password);


        // 非法输入，返回
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            response.sendRedirect("sign-in");
            return;
        }
        try {
            // 解密
//            username = xorEncryptDecrypt(username,10086);
//            password = xorEncryptDecrypt(password,10086);


            // 验证密码-创建账号
            if(appService.login(username,password)){

                System.out.println(" "+ rememberMe);

                // 保持账号信息（记住我选项）
                if ("on".equals(rememberMe)) {
                    // 将用户名和密码保存到 Cookie（存储一周）
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie passwordCookie = new Cookie("password", password);
                    usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 保存一周
                    passwordCookie.setMaxAge(7 * 24 * 60 * 60); // 保存一周
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                }

                // session登记
                request.getSession().setAttribute("username", username);

                ((Set<String>)(getServletContext().getAttribute("usernameSet"))).add(username);

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

    /**
     * 用户名和密码解密
     * */
    public static String xorEncryptDecrypt(String str, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            // XOR 运算
            result.append((char)(str.charAt(i) ^ key));
        }
        return result.toString();
    }
}
