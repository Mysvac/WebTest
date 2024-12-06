package com.mythovac.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * 用户登入的过滤器
 * */
@WebFilter(filterName = "logFilter", urlPatterns = { "/*" })
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求的 URL
        String requestURI = httpRequest.getRequestURI();

        // 排除特定的路径和 .css 文件
        if (requestURI.startsWith("/demo_war_exploded/main-page") || requestURI.equals("/demo_war_exploded/") || requestURI.startsWith("/demo_war_exploded/sign-in") || requestURI.endsWith(".css")) {
            // 这些路径不需要过滤，直接继续
            chain.doFilter(request, response);
        } else {
            // 其他路径需要检查 session
            HttpSession session = httpRequest.getSession(false); // false 表示如果没有 session 则返回 null
            if (session == null || session.getAttribute("username") == null) {
                // 如果 session 不存在或 session 中没有 "user" 属性，重定向到 sign-in
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/sign-in");
            } else {
                // session 存在，继续处理请求
                chain.doFilter(request, response);
            }
        }
    }


}
