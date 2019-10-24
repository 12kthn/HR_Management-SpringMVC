package com.poly.ps08445.mapper.impl;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartMapper implements EntityMapper<Depart, DepartDTO> {
    
    @Override
    public Depart mapEntity(DepartDTO departDTO) {
        Depart depart = new Depart();
        if (departDTO.getId() != null){
            depart.setId(departDTO.getId());
        }
        if (departDTO.getCode() != null){
            depart.setCode(departDTO.getCode());
        }
        if (departDTO.getName() != null){
            depart.setName(departDTO.getName());
        }
        return depart;
    }
    
}
