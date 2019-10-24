package com.poly.ps08445.repositories;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    List<T> select(String hql);

    List<T> select(String hql, Integer[] limitResult);

    List<T> select(String hql, Map<String, Object> parameters);

    List<T> select(String hql, Map<String, Object> parameters, Integer[] limitResult);

    Integer insert(T model);

    boolean update(T model);

    boolean delete(T model);
}
