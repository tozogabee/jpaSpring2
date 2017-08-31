package com.jpa.repository;

import com.jpa.repository.model.Function;
import com.jpa.repository.model.Inventor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by java on 2017.08.31..
 */

@Repository
@Transactional
public class FunctionRepositoryEM {

    @PersistenceContext
    EntityManager entityManager;

    public Function save(Function function)
    {
        entityManager.persist(function);
        return function;
    }

    public Function findById(long id)
    {
        return entityManager.find(Function.class,id);
    }

    public Function update(Function function)
    {
        entityManager.merge(function);
        return function;
    }

    public List<Function> findByFormula(String formula)
    {
        return entityManager
                .createQuery("select f from Function f where f.formula like :formula",Function.class)
                .setParameter("formula",formula)
                .getResultList();
    }

    public List<Function> listAllInventor()
    {
        return entityManager
                .createQuery("select f.inventor from Function f")
                .getResultList();
    }








}
