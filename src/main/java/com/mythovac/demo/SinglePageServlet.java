package com.mythovac.demo;

import com.mythovac.demo.entity.Message;
import com.mythovac.demo.service.AppService;
import jakarta.servlet.RequestDispatcher;
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
    AppService appService = new AppService();
    /**
     * 单个留言的页面
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 未登入者跳转到登入界面
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("username") == null) {
            response.sendRedirect("sign-in");
            return;
        }
        // id错误
        if(request.getParameter("id")==null) {
            response.sendRedirect("main-page");
        }
        int id = Integer.parseInt(request.getParameter("id"));
        try{
             Message msg = appService.lookMessage(id);
             if(msg != null) {
                 request.setAttribute("msg", msg);
                 RequestDispatcher rd = request.getRequestDispatcher("single.jsp");
                 rd.forward(request, response);
             }
             else{
                 response.sendRedirect("main-page");
             }
        } catch (Exception e) {
            System.out.println("single-page findById error");
            response.sendRedirect("main-page");
        }

    }

}
