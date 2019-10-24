package com.poly.ps08445.services;

import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.entities.Record;
import com.poly.ps08445.dto.RecordDTO;

import java.util.List;

public interface RecordService {

    List<Record> findAll();

    List<RecordDTO> findByStaffFullNameAndDepart(RecordDTO recordDTO);

    int getMaxPage(RecordDTO recordDTO);

    Integer insertRecord(RecordDTO recordModel);

    boolean updateRecord(RecordDTO recordModel);

    boolean deleteRecord(RecordDTO recordModel);

}
