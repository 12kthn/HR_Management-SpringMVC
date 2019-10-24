package com.poly.ps08445.services;

import com.poly.ps08445.dto.StaffDTO;

import java.util.List;

public interface StaffService {

    StaffDTO findOneById(Integer id);

    List<StaffDTO> findAll();

    List<StaffDTO> findByFullNameAndDepart(Integer departId, String fullName) ;

    List<StaffDTO> findByFullNameAndDepart(StaffDTO staffDTO);

    int getMaxPage(int numberRowsPerPage, StaffDTO staffDTO);

    Integer insertStaff(StaffDTO staffModel);

    boolean updateStaff(StaffDTO staffModel);

    boolean deleteStaff(StaffDTO staffModel);

}
