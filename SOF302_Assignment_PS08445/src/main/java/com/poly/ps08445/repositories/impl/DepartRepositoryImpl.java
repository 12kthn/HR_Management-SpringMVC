package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.entities.Depart;
import com.poly.ps08445.repositories.DepartRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartRepositoryImpl extends AbstracRepository<Depart> implements DepartRepository {

    @Transactional
    @Override
    public List<Depart> findAll() {
        String hql = "FROM Depart";
        return select(hql);
    }

    @Transactional
    @Override
    public Depart findOneById(Integer id) {
        Depart depart = sessionFactory.getCurrentSession().load(Depart.class, id);
        return depart;
    }

    @Transactional
    @Override
    public List<Depart> findByCode(String code) {
        String hql = "FROM Depart WHERE code = :code";
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return select(hql, map);
    }

    @Override
    public boolean insertDepart(Depart depart) {
        return insert(depart) != null;
    }

    @Override
    public boolean updateDepart(Depart depart) {
        return update(depart);
    }

    @Override
    public boolean deleteDepart(Depart depart) {
        return delete(depart);
    }
}
