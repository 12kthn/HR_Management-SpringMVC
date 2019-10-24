package com.poly.ps08445.services.impl;

import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.repositories.DepartRepository;
import com.poly.ps08445.repositories.StaffRepository;
import com.poly.ps08445.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    DepartRepository departRepository;

    @Autowired
    EntityMapper<Staff, StaffDTO> staffMapper;

    @Override
    public StaffDTO findOneById(Integer id) {
        return new StaffDTO(staffRepository.findOneById(id));
    }

    @Override
    public List<StaffDTO> findAll() {
        return new StaffDTO().getList(staffRepository.findAll());
    }

    @Override
    public List<StaffDTO> findByFullNameAndDepart(Integer departId, String fullName) {
        if (departId == -1){
            return new StaffDTO().getList(staffRepository.findByFullName(fullName));
        } else {
            return new StaffDTO().getList(staffRepository.findByFullNameAndDepart(fullName, departRepository.findOneById(departId)));
        }
    }

    @Override
    public List<StaffDTO> findByFullNameAndDepart(StaffDTO staffDTO) {
        Integer[] limitResult = new Integer[2];
        limitResult[0] = (staffDTO.getPage()-1)*staffDTO.getMaxResults();
        limitResult[1] = staffDTO.getMaxResults();
        if (staffDTO.getDepartId() == -1){
            return new StaffDTO().getList(staffRepository.findByFullName(staffDTO.getFullName(), limitResult));
        } else {
            return new StaffDTO().getList(staffRepository.findByFullNameAndDepart(staffMapper.mapEntity(staffDTO), limitResult));
        }
    }

    @Override
    public int getMaxPage(int numberRowsPerPage, StaffDTO staffDTO) {
        double numberRows = 0;
        if (staffDTO.getDepartId() == -1){
            numberRows = staffRepository.countAll(staffDTO.getFullName());
        } else {
            numberRows = staffRepository.countAll(staffDTO.getFullName(), departRepository.findOneById(staffDTO.getDepartId()));
        }
        return (int) Math.ceil(numberRows/numberRowsPerPage);
    }

    @Override
    public Integer insertStaff(StaffDTO staffModel) {
        return staffRepository.insertStaff(staffMapper.mapEntity(staffModel));
    }

    @Override
    public boolean updateStaff(StaffDTO staffModel) {
        return staffRepository.updateStaff(staffMapper.mapEntity(staffModel));
    }

    @Override
    public boolean deleteStaff(StaffDTO staffModel) {
        return staffRepository.deleteStaff(staffMapper.mapEntity(staffModel));
    }
}
