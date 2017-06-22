package com.example.demoMessage;

import javax.persistence.EntityManager;

/**
 * Created by Tanmoy Mandal on 1/1/2017.
 */
public interface EntityManagerAwareValidator {
    void setEntityManager(EntityManager entityManager);
}
