package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.entities.User;
import com.poly.ps08445.repositories.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl extends AbstracRepository<User> implements UserRepository {

    @Transactional
    @Override
    public List<User> findAll() {
        return select("FROM User");
    }

    @Transactional
    @Override
    public List<User> findByUserName(String userName) {
        String hql = "FROM User WHERE username = :username";
        Map<String, Object> map = new HashMap<>();
        map.put("username", userName);
        return select(hql, map);
    }

    @Transactional
    @Override
    public List<User> findByUserNamePassword(String userName, String password) {
        String hql = "FROM User WHERE username = :username AND password = :password";
        Map<String, Object> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        return select(hql, map);
    }

    @Override
    public boolean insertUser(User user) {
        return insert(user) != null;
    }

    @Override
    public boolean updateUser(User user) {
        return update(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return delete(user);
    }
}
