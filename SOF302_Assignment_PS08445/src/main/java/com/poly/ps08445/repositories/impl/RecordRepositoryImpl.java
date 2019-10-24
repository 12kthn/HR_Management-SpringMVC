package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.entities.Record;
import com.poly.ps08445.repositories.RecordRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RecordRepositoryImpl extends AbstracRepository<Record> implements RecordRepository {

    @Transactional
    @Override
    public Record findOneById(Integer id) {
        return sessionFactory.getCurrentSession().load(Record.class, id);
    }

    @Transactional
    @Override
    public List<Record> findAll() {
        String hql = "FROM Record";
        return select(hql);
    }

    @Transactional
    @Override
    public List<Record> findByStaffFullNameAndDepart(Integer departId, String fullName, Integer[] limitResult) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL SP_FindRecordByStaffAndDepart(:departId, :fullName, :firstResult, :maxResults)")
                .addEntity(Record.class)
                .setParameter("departId", departId)
                .setParameter("fullName", fullName)
                .setParameter("firstResult", limitResult[0])
                .setParameter("maxResults", limitResult[1]);
        return query.list();
    }

    @Transactional
    @Override
    public Long countAll(String fullName) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT COUNT(*) FROM records JOIN staffs ON records.staffId = staffs.id WHERE staffs.fullName LIKE CONCAT('%', :fullName,'%')")
                .setParameter("fullName", fullName);
        return Long.parseLong(query.uniqueResult() + "");
    }

    @Transactional
    @Override
    public Long countAll(Integer departId, String fullName) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT COUNT(*) FROM records JOIN staffs ON records.staffId = staffs.id WHERE staffs.departId = :departId AND staffs.fullName LIKE CONCAT('%', :fullName,'%')")
                .setParameter("departId", departId)
                .setParameter("fullName", fullName);
        return Long.parseLong(query.uniqueResult() + "");
    }

    @Override
    public Integer insertRecord(Record record) {
        return insert(record);
    }

    @Override
    public boolean updateRecord(Record record) {
        return update(record);
    }

    @Override
    public boolean deleteRecord(Record record) {
        return delete(record);
    }
}
