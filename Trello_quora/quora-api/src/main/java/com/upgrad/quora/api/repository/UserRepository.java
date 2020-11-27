package com.upgrad.quora.api.repository;

import com.upgrad.quora.api.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "quora")
    private EntityManagerFactory emf;

    public User getUserByUsername() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT i from User i", User.class);
        return query.getSingleResult();
    }

}
