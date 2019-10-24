package com.poly.ps08445.services;

import com.poly.ps08445.dto.DepartScoreDTO;
import com.poly.ps08445.dto.StaffScoreDTO;

import java.util.List;

public interface StoredProcedureService {

    List<DepartScoreDTO> getDepartTotalScore();

    List<StaffScoreDTO> getStaffTotalScore(StaffScoreDTO staffScoreDTO);

}
