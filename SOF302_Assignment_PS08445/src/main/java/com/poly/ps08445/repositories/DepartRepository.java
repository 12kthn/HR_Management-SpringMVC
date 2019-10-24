package com.poly.ps08445.repositories;

import com.poly.ps08445.entities.Depart;

import java.util.List;

public interface DepartRepository extends GenericRepository<Depart>{

    public List<Depart> findAll();

    public Depart findOneById(Integer id);

    public List<Depart> findByCode(String code);

    public boolean insertDepart(Depart depart);

    public boolean updateDepart(Depart depart);

    public boolean deleteDepart(Depart depart);

}
