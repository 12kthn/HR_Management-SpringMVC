package com.poly.ps08445.repositories;

import com.poly.ps08445.dto.StaffScoreDTO;

import java.util.List;

public interface StoredProcedureRepository {

    List<Object[]> getDepartTotalScore();

    List<Object[]> getStaffTotalScore(StaffScoreDTO staffScoreDTO, Integer[] limitResult);

}
