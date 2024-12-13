package com.mythovac.demo.impl;

import com.mythovac.demo.dao.DaoException;
import com.mythovac.demo.dao.UsersDao;
import com.mythovac.demo.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        update(sql,users.getUsername(),users.getPassword());

        return true;
    }
    /**
     * 查询指定用户
     * */
    @Override
    public Users findByUsername(String username) throws DaoException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Users users = null;

        List<Map<Integer, Object>> rs = query(sql,username);
        if(!rs.isEmpty()){
            users = new Users();
            for(Map<Integer, Object> m : rs){
                users.setId((int) m.get(1));
                users.setUsername((String) m.get(2));
                users.setPassword((String) m.get(3));
            }
        }
        return users;
    }
    /**
     * 查询全部用户
     * */
    @Override
    public ArrayList<Users> findAll() throws DaoException {
        String sql = "SELECT * FROM users";
        ArrayList<Users> usersList = new ArrayList<>();

        List<Map<Integer, Object>> rs = query(sql);
        if(!rs.isEmpty()){
            for(Map<Integer, Object> m : rs){
                Users users = new Users();
                users.setId((int) m.get(1));
                users.setUsername((String) m.get(2));
                users.setPassword((String) m.get(3));
                usersList.add(users);
            }
        }
        return usersList;
    }
}
