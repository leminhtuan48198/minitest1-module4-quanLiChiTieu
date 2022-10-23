package com.codegym.cms.repository;

import com.codegym.cms.model.Spending;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SpendingRepository implements ISpendingRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Spending> findAll() {
        TypedQuery<Spending> query = em.createQuery("select c from Spending c", Spending.class);
        return query.getResultList();
    }

    @Override
    public Spending findById(Long id) {
        TypedQuery<Spending> query = em.createQuery("select c from Spending c where  c.id=:id", Spending.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Spending spending) {
        if (spending.getId() != null) {
            em.merge(spending);
        } else {
            em.persist(spending);
        }
    }

    @Override
    public void remove(Long id) {
        Spending spending = findById(id);
        if (spending != null) {
            em.remove(spending);
        }
    }
}