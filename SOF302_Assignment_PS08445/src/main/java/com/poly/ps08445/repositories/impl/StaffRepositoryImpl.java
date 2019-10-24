package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.entities.Staff;
import com.poly.ps08445.repositories.StaffRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StaffRepositoryImpl extends AbstracRepository<Staff> implements StaffRepository {

    @Transactional
    @Override
    public Staff findOneById(Integer id) {
        return sessionFactory.getCurrentSession().load(Staff.class, id);
    }

    @Transactional
    @Override
    public List<Staff> findAll() {
        String hql = "FROM Staff";
        return select(hql);
    }

    @Transactional
    @Override
    public List<Staff> findByFullName(String fullName) {
        String hql = "FROM Staff WHERE fullName like :fullName";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + fullName + "%");
        return select(hql, map);
    }

    @Transactional
    @Override
    public List<Staff> findByFullNameAndDepart(String fullName, Depart depart) {
        String hql = "FROM Staff WHERE fullName like :fullName AND depart = :depart";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + fullName + "%");
        map.put("depart", depart);
        return select(hql, map);
    }

    @Transactional
    @Override
    public List<Staff> findByFullName(String fullName, Integer[] limitResult) {
        String hql = "FROM Staff WHERE fullName like :fullName";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + fullName + "%");
        return select(hql, map, limitResult);
    }

    @Transactional
    @Override
    public List<Staff> findByFullNameAndDepart(Staff staff, Integer[] limitResult) {
        String hql = "FROM Staff WHERE fullName like :fullName AND depart = :depart";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + staff.getFullName() + "%");
        map.put("depart", staff.getDepart());
        return select(hql, map, limitResult);
    }

    @Transactional
    @Override
    public Long countAll(String fullName){
        String hql = "SELECT COUNT(*) FROM Staff WHERE fullName like :fullName";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + fullName + "%");
        return (Long) uniqueResult(hql, map);
    }

    @Transactional
    @Override
    public Long countAll(String fullName, Depart depart){
        String hql = "SELECT COUNT(*) FROM Staff WHERE fullName like :fullName AND depart = :depart";
        Map<String, Object> map = new HashMap<>();
        map.put("fullName", "%" + fullName + "%");
        map.put("depart", depart);
        return (Long) uniqueResult(hql, map);
    }

    @Override
    public Integer insertStaff(Staff staff) {
        return insert(staff);
    }

    @Override
    public boolean updateStaff(Staff staff) {
        return update(staff);
    }

    @Override
    public boolean deleteStaff(Staff staff) {
        return delete(staff);
    }
}
