package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.repositories.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstracRepository<T> implements GenericRepository<T> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<T> select(String hql) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(hql).list();
    }

    @Override
    public List<T> select(String hql, Integer[] limitResult) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setFirstResult(limitResult[0]);
        query.setMaxResults(limitResult[1]);
        return query.list();
    }

    @Override
    public List<T> select(String hql, Map<String, Object> parameters) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        for (String key: parameters.keySet()){
            query.setParameter(key, parameters.get(key));
        }
        return query.list();
    }

    @Override
    public List<T> select(String hql, Map<String, Object> parameters, Integer[] limitResult) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        for (String key: parameters.keySet()){
            query.setParameter(key, parameters.get(key));
        }
        query.setFirstResult(limitResult[0]);
        query.setMaxResults(limitResult[1]);
        return query.list();
    }

    Object uniqueResult(String hql, Map<String, Object> parameters){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        for (String key: parameters.keySet()){
            query.setParameter(key, parameters.get(key));
        }
        return query.uniqueResult();
    }

    @Override
    public Integer insert(T model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = -1;
        try{
            id = (Integer) session.save(model);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            id = -1;
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public boolean update(T model) {
        boolean success;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(model);
            transaction.commit();
            success = true;
        } catch (Exception e){
            transaction.rollback();
            success = false;
        } finally {
            session.close();
        }
        return success;
    }

    @Override
    public boolean delete(T model) {
        boolean success;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(model);
            transaction.commit();
            success = true;
        } catch (Exception e){
            transaction.rollback();
            success = false;
        } finally {
            session.close();
        }
        return success;
    }

}
