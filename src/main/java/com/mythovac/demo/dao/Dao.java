package com.mythovac.demo.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 基础的Dao接口 提供数据库连接
 * */
public interface Dao {
    /**
     * 获取数据源
     * */
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try{
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/MyLocalDS");
        }catch(NamingException e){
            System.out.println("异常：" + e);
        }
        return dataSource;
    }
    /**
     * 获取数据库连接
     * */
    public default Connection getConnection() throws DaoException {
        DataSource dataSource = getDataSource();
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("异常:" + e);
        }
        return conn;
    }

}
