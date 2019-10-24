package com.poly.ps08445.repositories;

import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.entities.Staff;

import java.util.List;

public interface StaffRepository extends GenericRepository<Staff> {

    Staff findOneById(Integer id);

    List<Staff> findAll();

    List<Staff> findByFullName(String fullName);

    List<Staff> findByFullNameAndDepart(String fullName, Depart depart);

    List<Staff> findByFullName(String fullName, Integer[] limitResult);

    List<Staff> findByFullNameAndDepart(Staff staff, Integer[] limitResult);

    Long countAll(String fullName);

    Long countAll(String fullName, Depart depart);

    Integer insertStaff(Staff staff);

    boolean updateStaff(Staff staff);

    boolean deleteStaff(Staff staff);

}
