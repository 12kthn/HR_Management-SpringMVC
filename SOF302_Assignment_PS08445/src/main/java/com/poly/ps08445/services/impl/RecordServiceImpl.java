package com.poly.ps08445.services.impl;

import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.entities.Record;
import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.dto.RecordDTO;
import com.poly.ps08445.repositories.RecordRepository;
import com.poly.ps08445.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    EntityMapper<Record, RecordDTO> recordMapper;

    @Autowired
    EntityMapper<Staff, StaffDTO> staffMapper;

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public List<RecordDTO> findByStaffFullNameAndDepart(RecordDTO recordDTO) {
        Integer[] limitResult = new Integer[2];
        limitResult[0] = (recordDTO.getPage()-1)*recordDTO.getMaxResults();
        limitResult[1] = recordDTO.getMaxResults();
        return new RecordDTO().getList(recordRepository.findByStaffFullNameAndDepart(recordDTO.getDepartId(), recordDTO.getStaffFullName(), limitResult));
    }

    @Override
    public int getMaxPage(RecordDTO recordDTO) {
        double numberRows = 0;
        if (recordDTO.getDepartId() == -1){
            numberRows = recordRepository.countAll(recordDTO.getStaffFullName());
        } else {
            numberRows = recordRepository.countAll(recordDTO.getDepartId(), recordDTO.getStaffFullName());
        }
        return (int) Math.ceil(numberRows/recordDTO.getMaxResults());
    }

    @Override
    public Integer insertRecord(RecordDTO recordModel) {
        return recordRepository.insertRecord(recordMapper.mapEntity(recordModel));
    }

    @Override
    public boolean updateRecord(RecordDTO recordModel) {
        return recordRepository.updateRecord(recordMapper.mapEntity(recordModel));
    }

    @Override
    public boolean deleteRecord(RecordDTO recordModel) {
        return recordRepository.deleteRecord(recordMapper.mapEntity(recordModel));
    }

}
