package com.mythovac.demo.dao;

import com.mythovac.demo.entity.Users;

import java.util.ArrayList;

public interface UsersDao extends Dao{
    public boolean insert(Users users) throws DaoException;
    public Users findByUsername(String username) throws DaoException;
    public ArrayList<Users> findAll() throws DaoException;
}
