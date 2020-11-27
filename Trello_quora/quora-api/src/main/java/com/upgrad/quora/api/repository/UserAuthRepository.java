package com.upgrad.quora.api.repository;

import com.upgrad.quora.api.model.UserAuth;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserAuthRepository {

    @PersistenceUnit(unitName = "quora")
    private EntityManagerFactory emf;

    public UserAuth save(UserAuth u) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(u);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return u;
    }
}
