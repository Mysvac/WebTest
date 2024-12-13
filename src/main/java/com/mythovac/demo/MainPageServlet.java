package com.mythovac.demo;

import com.mythovac.demo.entity.Message;
import com.mythovac.demo.service.AppService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 主界面
 * */
@MultipartConfig
@WebServlet(name = "mainPageServlet", value = "/main-page")
public class MainPageServlet extends HttpServlet {
    AppService appService = new AppService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
        try {
            request.setAttribute("messageList", appService.lookAllMessages());
            for(Message msg : appService.lookAllMessages()){
                System.out.println(msg.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("main-page doGet lookAllMessages error");
        }
        rd.forward(request, response);
    }

    /**
     * 提交留言
     * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 检验合法性
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("username") == null) {
            return;
        }
        String username = (String) session.getAttribute("username");
        String title = (String) request.getAttribute("title");
        String content = (String) request.getAttribute("content");
        if(title == null || title.isEmpty() || content == null || content.isEmpty()) {
            return;
        }
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        Message msg = new Message(username, title, content, formattedDate);
        try {
            appService.insertMessage(msg);
        }catch (Exception e) {
            System.out.println("insert message error");
        }
    }
}
