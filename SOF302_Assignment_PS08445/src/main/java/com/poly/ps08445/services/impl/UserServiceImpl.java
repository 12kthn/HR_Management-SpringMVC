package com.poly.ps08445.services.impl;

import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.entities.User;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.repositories.UserRepository;
import com.poly.ps08445.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityMapper<User, UserDTO> userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean findByUserName(UserDTO userDTO) {
        return !userRepository.findByUserName(userDTO.getUserName()).isEmpty();
    }

    @Override
    public User findByUserNamePassword(UserDTO userDTO) {
        List<User> list = userRepository.findByUserNamePassword(userDTO.getUserName(), userDTO.getPassword());
        if (list.isEmpty()){
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public boolean insertUser(UserDTO userDTO) {
        return userRepository.insertUser(userMapper.mapEntity(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userRepository.updateUser(userMapper.mapEntity(userDTO));
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        return userRepository.deleteUser(userMapper.mapEntity(userDTO));
    }
}
