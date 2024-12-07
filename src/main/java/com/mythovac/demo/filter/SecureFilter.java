package com.mythovac.demo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 数据解密器
 * */
@WebFilter(filterName = "secureFilter", urlPatterns = { "/*" })
public class SecureFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(request.getRequestURI().startsWith("/demo_war_exploded/sign-in") && "POST".equalsIgnoreCase(request.getMethod())) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username != null && password != null) {
                username = xorEncryptDecrypt(username,10086);
                password = xorEncryptDecrypt(password,10086);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
            }
            chain.doFilter(request, response);
        }
        else{
            chain.doFilter(request, response);
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
