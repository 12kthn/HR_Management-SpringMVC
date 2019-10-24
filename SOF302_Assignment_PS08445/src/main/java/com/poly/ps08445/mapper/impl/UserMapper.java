package com.poly.ps08445.mapper.impl;

import com.poly.ps08445.dto.UserDTO;
import com.poly.ps08445.entities.User;
import com.poly.ps08445.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<User, UserDTO> {
    @Override
    public User mapEntity(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        if (userDTO.getUserName() != null) {
            user.setUserName(userDTO.getUserName());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        return user;
    }
}
