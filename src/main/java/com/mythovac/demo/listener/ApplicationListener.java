package com.mythovac.demo.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;


/**
 * 监听器，在程序启动时创建数据表
 * 在程序结束时删除
 * */
@WebListener
public class ApplicationListener implements ServletContextListener {
    // 这里只是创建和删除数据表时用的连接
    // 其他操作，如实现Dao的方法的数据库操作，使用数据源的形式
    // 定义在 webapp/META-INF/context.xml 中
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_base?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "baseAdmin";
    private static final String DB_PASSWORD = "baseAdminPwd";

    private static Set<String> usernameSet = new TreeSet<String>();

    /**
     * 程序启动时
     * 创建数据库 users 和 message
     * */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().setAttribute("usernameSet", usernameSet);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Error: " + e);
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // 创建表的 SQL
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username CHAR(23) NOT NULL UNIQUE,
                    password CHAR(31) NOT NULL
                )
            """;
            String createTableMessage = """
                CREATE TABLE IF NOT EXISTS message (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username CHAR(23) NOT NULL,
                    title CHAR(31) NOT NULL,
                    content CHAR(255) NOT NULL,
                    time DATETIME NOT NULL
                )
            """;
            stmt.execute(createTableSQL);
            stmt.execute(createTableMessage);
            System.out.println("success : create table  users and message");

        } catch (Exception e) {
            System.out.println("error : create table：："+e);
        }
    }
    /**
     * 程序结束时
     * 删除数据库 users 和 message
     * */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // 删除表的 SQL
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            String dropTableMessage = "DROP TABLE IF EXISTS message";
            stmt.execute(dropTableSQL);
            stmt.execute(dropTableMessage);
            System.out.println("success : drop table  users and message");

        } catch (Exception e) {
            System.out.println("error : drop table："+e);
        }

    }
}
