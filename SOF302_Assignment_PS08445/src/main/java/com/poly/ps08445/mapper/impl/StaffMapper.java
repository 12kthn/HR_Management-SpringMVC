package com.poly.ps08445.mapper.impl;

import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.dto.StaffDTO;
import com.poly.ps08445.mapper.EntityMapper;
import com.poly.ps08445.repositories.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StaffMapper implements EntityMapper<Staff, StaffDTO> {

    @Autowired
    DepartRepository departRepository;

    @Override
    public Staff mapEntity(StaffDTO staffModel) {
        Staff staff = new Staff();

        if (staffModel.getId() != null)
            staff.setId(staffModel.getId());

        if (staffModel.getFullName() != null)
            staff.setFullName(staffModel.getFullName());

        if (staffModel.getGender() != null)
            staff.setGender(staffModel.getGender());

        if (staffModel.getPhone() != null)
            staff.setPhone(staffModel.getPhone());

        if (staffModel.getPhoto() != null)
            staff.setPhoto(staffModel.getPhoto());

        if (staffModel.getNotes() != null)
            staff.setNotes(staffModel.getNotes());

        staff.setSalary(staffModel.getSalary());

        if (staffModel.getEmail() != null)
            staff.setEmail(staffModel.getEmail());

        if (staffModel.getDepartId() != null) {
            staff.setDepart(departRepository.findOneById(staffModel.getDepartId()));
        }

        if (staffModel.getBirthday() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                staff.setBirthday(dateFormat.parse(staffModel.getBirthday()));
            } catch (ParseException e) {
                staff.setBirthday(new Date());
            }
        }

        return staff;
    }
}
