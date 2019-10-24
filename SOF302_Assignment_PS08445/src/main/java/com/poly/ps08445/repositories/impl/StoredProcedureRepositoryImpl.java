package com.poly.ps08445.repositories.impl;

import com.poly.ps08445.dto.StaffScoreDTO;
import com.poly.ps08445.repositories.StoredProcedureRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StoredProcedureRepositoryImpl extends AbstracRepository<Object> implements StoredProcedureRepository {

    @Transactional
    @Override
    public List<Object[]> getDepartTotalScore() {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL SP_DepartTotalScore()");
        return query.list();
    }

    @Transactional
    @Override
    public List<Object[]> getStaffTotalScore(StaffScoreDTO staffScoreDTO, Integer[] limitResult) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL SP_StaffTotalScore(:departId, :fullName, :firstResult, :maxResults)")
                .setParameter("departId", staffScoreDTO.getDepartId())
                .setParameter("fullName", staffScoreDTO.getStaffFullName())
                .setParameter("firstResult", limitResult[0])
                .setParameter("maxResults", limitResult[1]);
        return query.list();
    }

}
