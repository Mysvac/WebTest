package com.mythovac.demo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 脏话过滤器
 * */
@WebFilter(filterName = "dirtyFilter", urlPatterns = { "/*" })
public class DirtyFilter extends HttpFilter {

    private static final String[] PROFANITY_LIST = {
            "nmd", "cnm", "傻逼","shit","fuck","狗屎","nmsl","弱智","脑瘫"  // 示例：这里可以添加实际需要过滤的脏话词汇
    };

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request.getRequestURI().startsWith("/demo_war_exploded/main-page") && "POST".equalsIgnoreCase(request.getMethod())) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            title = filterProfanity(title);
            content = filterProfanity(content);
            request.setAttribute("title", title);
            request.setAttribute("content", content);

            chain.doFilter(request, response);
        }
        else {
            chain.doFilter(request, response);
        }
    }

    // 脏话过滤方法
    private String filterProfanity(String input) {
        if (input == null) return null;
        for (String profanity : PROFANITY_LIST) {
            // 用星号替换脏话
            input = input.replaceAll("(?i)" + profanity, "口口");
        }
        return input;
    }
}
