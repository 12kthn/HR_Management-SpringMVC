package com.poly.ps08445.services.impl;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.repositories.DepartRepository;
import com.poly.ps08445.services.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    DepartRepository departRepository;

    @Autowired
    EntityMapper<Depart, DepartDTO> departMapper;

    @Override
    public List<DepartDTO> findAll() {
        return new DepartDTO().getList(departRepository.findAll());
    }

    @Override
    public DepartDTO findOneById(Integer id) {
        return new DepartDTO(departRepository.findOneById(id));
    }

    @Override
    public boolean duplicateCode(String code) {
        return !departRepository.findByCode(code).isEmpty();
    }

    @Override
    public boolean insertDepart(DepartDTO departDTO) {
        return departRepository.insertDepart(departMapper.mapEntity(departDTO));
    }

    @Override
    public boolean updateDepart(DepartDTO departDTO) {
        return departRepository.updateDepart(departMapper.mapEntity(departDTO));
    }

    @Override
    public boolean deleteDepart(DepartDTO departDTO) {
        return departRepository.deleteDepart(departMapper.mapEntity(departDTO));
    }
}
