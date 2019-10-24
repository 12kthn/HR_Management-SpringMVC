package com.poly.ps08445.services;

import com.poly.ps08445.dto.DepartDTO;
import com.poly.ps08445.entities.Depart;

import java.util.List;

public interface DepartService {

    List<DepartDTO> findAll();

    DepartDTO findOneById(Integer id);

    boolean duplicateCode(String code);

    boolean insertDepart(DepartDTO departDTO);

    boolean updateDepart(DepartDTO departDTO);

    boolean deleteDepart(DepartDTO departDTO);

}
