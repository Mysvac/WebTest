package com.mythovac.demo.impl;

import com.mythovac.demo.dao.DaoException;
import com.mythovac.demo.dao.UsersDao;
import com.mythovac.demo.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 实现方法 用户表
 * */
public class UsersImpl implements UsersDao {
    /**
     * 插入新用户
     * */
    @Override
    public boolean insert(Users users) throws DaoException {
        String sql = "insert into users (username, password) values(?,?)";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 查询指定用户
     * */
    @Override
    public Users findByUsername(String username) throws DaoException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Users users = new Users();
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    users.setId(rs.getInt("id"));
                    users.setUsername(rs.getString("username"));
                    users.setPassword(rs.getString("password"));
                    return users;
                }
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询全部用户
     * */
    @Override
    public ArrayList<Users> findAll() throws DaoException {
        String sql = "SELECT * FROM users";
        ArrayList<Users> usersList = new ArrayList<>();

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Users users = new Users();
                users.setId(rs.getInt("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                usersList.add(users);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<Users>();
        }
        return usersList;
    }
}
