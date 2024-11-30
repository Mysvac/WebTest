package com.mythovac.demo.service;

import com.mythovac.demo.entity.Message;
import com.mythovac.demo.entity.Users;
import com.mythovac.demo.impl.MessageImpl;
import com.mythovac.demo.impl.UsersImpl;

import java.util.ArrayList;

/**
 * 服务类，二次封装数据库操作
 * */
public class AppService {
    private UsersImpl usersImpl = new UsersImpl();
    private MessageImpl messageImpl = new MessageImpl();

    /**
     * 登入与自动注册
     * */
    public boolean login(String username, String password) throws Exception {

        Users users = usersImpl.findByUsername(username);
        // 不存在此用户时自动创建
        if(users == null){
            Users tmpUsers = new Users(username, password);
            usersImpl.insert(tmpUsers);
            return true;
        }

        // 存在此用户时比较密码
        return users.getPassword().equals(password);
    }

    /**
     * 插入留言记录
     * */
    public boolean insertMessage(Message message) throws Exception {
        return messageImpl.insert(message);
    }

    /**
     * 查询全部留言记录
     * */
    public ArrayList<Message> lookAllMessages() throws Exception {
        return messageImpl.findAll();
    }

    /**
     * 查询指定的留言
     * */
    public Message lookMessage(int messageId) throws Exception {
        return messageImpl.findById(messageId);
    }
}
