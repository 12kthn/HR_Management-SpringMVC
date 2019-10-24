package com.poly.ps08445.services.impl;

import com.poly.ps08445.dto.DepartScoreDTO;
import com.poly.ps08445.dto.StaffScoreDTO;
import com.poly.ps08445.repositories.DepartRepository;
import com.poly.ps08445.repositories.StaffRepository;
import com.poly.ps08445.repositories.StoredProcedureRepository;
import com.poly.ps08445.services.StoredProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoredProcedureServiceImpl implements StoredProcedureService {

    @Autowired
    StoredProcedureRepository stored;

    @Autowired
    DepartRepository departRepository;

    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<DepartScoreDTO> getDepartTotalScore() {
        List<Object[]> arrays = stored.getDepartTotalScore();
        List<DepartScoreDTO> list = new ArrayList<>();
        for (Object[] array : arrays){
            list.add(new DepartScoreDTO(departRepository.findOneById(Integer.valueOf(array[0].toString())),
                    Integer.valueOf(array[1]!=null?array[1].toString():"0"),
                    Integer.valueOf(array[2]!=null?array[2].toString():"0")));
        }
        return list;
    }

    @Override
    public List<StaffScoreDTO> getStaffTotalScore(StaffScoreDTO staffScoreDTO) {
        Integer[] limitResult = new Integer[2];
        limitResult[0] = (staffScoreDTO.getPage()-1)*staffScoreDTO.getMaxResults();
        limitResult[1] = staffScoreDTO.getMaxResults();
        List<Object[]> arrays = stored.getStaffTotalScore(staffScoreDTO, limitResult);
        List<StaffScoreDTO> list = new ArrayList<>();
        for (Object[] array : arrays){
            list.add(new StaffScoreDTO(array[0].toString(), array[1].toString(), array[2].toString(),
                    Integer.valueOf(array[3].toString()),
                    Integer.valueOf(array[4].toString()),
                    Integer.valueOf(array[5].toString())));
        }
        return list;
    }

}
