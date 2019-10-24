package com.poly.ps08445.mapper.impl;

import com.poly.ps08445.entities.Record;
import com.poly.ps08445.dto.RecordDTO;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.repositories.StaffRepository;
import com.poly.ps08445.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RecordMapper implements EntityMapper<Record, RecordDTO> {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Record mapEntity(RecordDTO recordModel) {
        Record record = new Record();

        if (recordModel.getId() != null){
            record.setId(recordModel.getId());
        }
        if (recordModel.getStaffId() != null){
            record.setStaff(staffRepository.findOneById(recordModel.getStaffId()));
        }
        if (recordModel.getType() != null){
            record.setType(recordModel.getType());
        }
        if (recordModel.getReason() != null) {
            record.setReason(recordModel.getReason());
        }
        if (recordModel.getDate() != null){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                record.setDate(dateFormat.parse(recordModel.getDate()));
            } catch (ParseException e) {
                record.setDate(new Date());
            }
        }
        return record;
    }

}
