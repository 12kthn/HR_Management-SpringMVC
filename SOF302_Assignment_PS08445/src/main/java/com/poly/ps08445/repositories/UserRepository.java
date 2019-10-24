package com.poly.ps08445.repositories;

import com.poly.ps08445.entities.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {

    public List<User> findAll();

    public List<User> findByUserName(String userName);

    public List<User> findByUserNamePassword(String userName, String password);

    public boolean insertUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(User user);
}
