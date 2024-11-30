package com.mythovac.demo.dao;

import com.mythovac.demo.entity.Message;

import java.util.ArrayList;

public interface MessageDao extends Dao{
    public boolean insert(Message message) throws DaoException;
    public Message findById(int id) throws DaoException;
    public ArrayList<Message> findAll() throws DaoException;
}
