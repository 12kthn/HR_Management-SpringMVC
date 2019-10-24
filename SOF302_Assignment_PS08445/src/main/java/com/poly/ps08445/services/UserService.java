package com.poly.ps08445.services;

import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.entities.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public boolean findByUserName(UserDTO userDTO);

    public User findByUserNamePassword(UserDTO userDTO);

    public boolean insertUser(UserDTO userDTO);

    public boolean updateUser(UserDTO userDTO);

    public boolean deleteUser(UserDTO userDTO);

}
