package com.poly.ps08445.repositories;

import com.poly.ps08445.entities.Record;
import com.poly.ps08445.entities.Staff;

import java.util.List;

public interface RecordRepository extends GenericRepository<Record> {

    Record findOneById(Integer id);

    List<Record> findAll();

    List<Record> findByStaffFullNameAndDepart(Integer departId, String fullName, Integer[] limitResult);

    Long countAll(String fullName);

    Long countAll(Integer departId, String fullName);

    Integer insertRecord(Record record);

    boolean updateRecord(Record record);

    boolean deleteRecord(Record record);

}
