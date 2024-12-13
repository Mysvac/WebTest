package com.mythovac.demo.impl;

import com.mythovac.demo.dao.DaoException;
import com.mythovac.demo.dao.MessageDao;
import com.mythovac.demo.entity.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        try{
            // 转换为 Timestamp 对象
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(message.getTime(), formatter);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            update(sql,message.getUsername(),message.getTitle(),message.getContent(),timestamp);
            return true;
        }
        catch (Exception e){
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
        Message message = null;

        List<Map<Integer, Object>> rs = query(sql,id);
        if(!rs.isEmpty()){
            message = new Message();
            for(Map<Integer, Object> m : rs){
                message.setId((int) m.get(1));
                message.setUsername((String) m.get(2));
                message.setTitle((String) m.get(3));
                message.setContent((String) m.get(4));
                message.setTime(((LocalDateTime) m.get(5)).toString());
            }
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

        List<Map<Integer, Object>> rs = query(sql);
        if (!rs.isEmpty()) {
            for(Map<Integer, Object> m : rs){
                Message message = new Message();
                message.setId((int) m.get(1));
                message.setUsername((String) m.get(2));
                message.setTitle((String) m.get(3));
                message.setContent((String) m.get(4));
                message.setTime(((LocalDateTime) m.get(5)).toString());
                messageList.add(message);
            }
        }

        return messageList;
    }
}
