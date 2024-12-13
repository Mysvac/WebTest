package com.mythovac.demo.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * 更新
     * */
    public default void update(String sql,Object ... params) {
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            int size = params.length;
            for(int i = 1; i <= size; i++) {
                ps.setObject(i, params[i-1]);
            }
            ps.executeUpdate();
        }
        catch(SQLException | DaoException e){
            e.printStackTrace();
        }
    }
    /**
     * 更新
     * */
    public default List<Map<Integer, Object>> query(String sql, Object ... params) {
        List<Map<Integer, Object>> results = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            int size = params.length;
            for(int i = 1; i <= size; i++) {
                ps.setObject(i, params[i-1]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                int columnCount = rs.getMetaData().getColumnCount();
                // 读取结果集
                while (rs.next()) {
                    Map<Integer, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(i, rs.getObject(i));
                    }
                    results.add(row);
                }
            }
            return results;
        }
        catch(SQLException | DaoException e){
            e.printStackTrace();
            return null;
        }
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
