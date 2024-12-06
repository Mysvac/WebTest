package com.mythovac.demo.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Set;

@WebListener
public class UserListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String username = (String)(session.getAttribute("username"));
        if(username != null) {
            Set<String> usernameSet = (Set<String>)(session.getServletContext().getAttribute("usernameSet"));
            usernameSet.remove(username);
        }

        HttpSessionListener.super.sessionDestroyed(se);
    }
}
