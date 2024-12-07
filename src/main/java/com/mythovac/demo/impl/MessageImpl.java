package com.mythovac.demo.impl;

import com.mythovac.demo.dao.DaoException;
import com.mythovac.demo.dao.MessageDao;
import com.mythovac.demo.entity.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * MessageDao的实现
 * */
public class MessageImpl implements MessageDao {
    /**
     * 插入新消息
     * */
    @Override
    public boolean insert(Message message) throws DaoException {
        String sql = "insert into message (username, title, content, time) values(?,?,?,?)";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            System.out.println("title: "+message.getTitle());
            ps.setString(1, message.getUsername());
            ps.setString(2, message.getTitle());
            ps.setString(3, message.getContent());

            // 转换为 Timestamp 对象
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(message.getTime(), formatter);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            // 设置 time 字段为 Timestamp 对象
            ps.setTimestamp(4, timestamp);

            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据序号查询消息
     * */
    @Override
    public Message findById(int id) throws DaoException {
        String sql = "select * from message where id = ?";
        Message message = new Message();
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    message.setId(rs.getInt(1));
                    message.setUsername(rs.getString(2));
                    message.setTitle(rs.getString(3));
                    message.setContent(rs.getString(4));
                    message.setTime(rs.getString(5));
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return message;
    }

    /**
     * 查询全部消息
     * */
    @Override
    public ArrayList<Message> findAll() throws DaoException {
        String sql = "select * from message  ORDER BY time DESC";
        ArrayList<Message> messageList = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())
        {
            while(rs.next()){
                Message message = new Message();
                message.setId(rs.getInt(1));
                message.setUsername(rs.getString(2));
                message.setTitle(rs.getString(3));
                message.setContent(rs.getString(4));
                message.setTime(rs.getString(5));
                messageList.add(message);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<Message>();
        }
        return messageList;
    }
}
